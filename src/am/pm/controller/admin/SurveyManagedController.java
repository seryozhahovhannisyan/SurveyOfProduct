package am.pm.controller.admin;

import am.pm.dataaccess.exception.InternalErrorException;
import am.pm.dataaccess.model.Survey;
import am.pm.dataaccess.model.User;
import am.pm.dataaccess.sevice.ISurveyManager;
import am.pm.dataaccess.sevice.IUserManager;
import am.pm.dataaccess.sevice.impl.SurveyManagerImpl;
import am.pm.dataaccess.sevice.impl.UserManagerImpl;
import am.pm.util.Constant;
import am.pm.util.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Artur on 4/2/2016.
 */
public class SurveyManagedController extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute(Constant.SESSION_USER);
        String pId = request.getParameter("id");
        String pUid = request.getParameter("userId");

        int memberId = 0;

        if (Utils.isEmpty(pId)) {
            forward(request, response );
            return;
        }
        ISurveyManager surveyManager = new SurveyManagerImpl();
        IUserManager userManager = new UserManagerImpl();

        Map<String,Object> params = new HashMap<>();
        params.put("userId",user.getId());

        try {

            Integer id = Integer.parseInt(pId);
            params.put("surveyId", id);

            List<Survey>  surveys = surveyManager.getByParams(params);
            params.clear();
            params.put("surveyId", id);
            List<User> members = userManager.getMembers(user.getId(),id);
            if(!Utils.isEmpty(pUid)){
                memberId = Integer.parseInt(pUid);
            } else {
                memberId = members.get(0).getId();
            }
            params.put("memberId",memberId );

            Survey survey =  surveyManager.getById(id);

            Survey answeredSurvey = surveyManager.getAnsweredById(params);
            if(answeredSurvey != null){
                survey.setSurveyAnswers(answeredSurvey.getSurveyAnswers());
            }

            request.setAttribute("surveys", surveys );
            request.setAttribute("survey", survey );
            request.setAttribute("members", members );

            String nextJSP = "/WEB-INF/pages/admin/survey-maneged.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request, response);

        } catch (InternalErrorException e) {
            forward(request,response);
        }
        return;

    }

    private synchronized void forward(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        request.getSession().setAttribute(Constant.MSG_ERROR, Constant.MSG_ERROR_INTERNAL);
        String nextJSP = "/WEB-INF/pages/user/home.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request, response);
    }
}
