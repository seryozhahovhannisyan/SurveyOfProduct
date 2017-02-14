package am.pm.controller.user;

import am.pm.dataaccess.exception.InternalErrorException;
import am.pm.dataaccess.model.Survey;
import am.pm.dataaccess.model.User;
import am.pm.dataaccess.sevice.ISurveyManager;
import am.pm.dataaccess.sevice.impl.SurveyManagerImpl;
import am.pm.util.Constant;

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
public class SurveyController extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ISurveyManager surveyManager = new SurveyManagerImpl();
        User user = (User) request.getSession().getAttribute(Constant.SESSION_USER);

        Map<String, Object> params = new HashMap<>();
        params.put("memberId", user.getId());
        String nextJSP = "";
        try {

            List<Survey> surveys = surveyManager.getByParams(params);
            request.setAttribute("surveys", surveys);
            nextJSP = "/WEB-INF/pages/user/surveys.jsp";
        } catch (InternalErrorException e) {
            request.getSession().setAttribute(Constant.MSG_ERROR, Constant.MSG_ERROR_INTERNAL);
            nextJSP = "/WEB-INF/pages/user/home.jsp";
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request, response);
        return;
    }


}
