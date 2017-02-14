package am.pm.controller.admin;

import am.pm.dataaccess.sevice.ISurveyManager;
import am.pm.dataaccess.sevice.impl.SurveyManagerImpl;
import am.pm.util.Constant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Artur on 4/2/2016.
 */
public class SurveyUpdateController extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pSurveyId = request.getParameter("surveyId");
        String[] members = request.getParameterValues("members");
        String[] questions = request.getParameterValues("questions");

        ISurveyManager surveyManager = new SurveyManagerImpl();

        try {

            int surveyId = Integer.parseInt(pSurveyId);

            int[] memberIdes = new int[members.length];
            int[] questionIdes = new int[questions.length];

            for(int i = 0; i < members.length ; i++){
                String member = members[i];
                memberIdes[i] = Integer.parseInt(member);
            }

            for(int i = 0; i < questions.length ; i++){
                String question = questions[i];
                questionIdes[i] = Integer.parseInt(question);
            }

            surveyManager.updateMembersAndQuestions(surveyId, memberIdes, questionIdes);
            response.sendRedirect("/admin/survey/manage?id="+surveyId);
            return;
        }catch ( Exception e){
            request.getSession().setAttribute(Constant.MSG_ERROR, Constant.MSG_ERROR_INTERNAL);
            String  nextJSP = "/WEB-INF/pages/admin/home.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request, response);
            return;
        }
    }
}
