package am.pm.controller.admin;

import am.pm.dataaccess.exception.InternalErrorException;
import am.pm.dataaccess.model.Survey;
import am.pm.dataaccess.model.User;
import am.pm.dataaccess.sevice.ISurveyManager;
import am.pm.dataaccess.sevice.impl.SurveyManagerImpl;
import am.pm.util.Constant;
import am.pm.util.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Artur on 4/2/2016.
 */
public class SurveyAddController extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String pStartAt = request.getParameter("startAt");
        String pDeadline = request.getParameter("deadline");

        Date startAt = Utils.stringToDate(pStartAt);
        Date deadline = Utils.stringToDate(pDeadline);
        Date now = new Date(System.currentTimeMillis());
        User user = (User) request.getSession().getAttribute(Constant.SESSION_USER);

        if (Utils.isEmpty(name)) {
            forward(request, response, "Invalid name");
            return;
        }
        if (Utils.isEmpty(description)) {
            forward(request, response, "Invalid description");
            return;
        }
        if (startAt == null) {
            forward(request, response, "Invalid start at");
            return;
        }
        if (deadline == null) {
            forward(request, response, "Invalid deadline");
            return;
        }

        Survey survey = new Survey();
        survey.setName(name);
        survey.setDescription(description);
        survey.setStartAt(startAt);
        survey.setDeadline(deadline);
        survey.setCreatedAt(now);
        survey.setCreatedBy(user);


        ISurveyManager surveyManager = new SurveyManagerImpl();
        try {
            surveyManager.add(survey);
            response.sendRedirect("/admin/surveys/view");
        } catch (InternalErrorException e) {
            forward(request, response, Constant.MSG_ERROR_INTERNAL);
            return;
        }
    }

    private synchronized void forward(HttpServletRequest request, HttpServletResponse response, String msg) throws ServletException, IOException {
        request.getSession().setAttribute(Constant.MSG_ERROR, msg);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/pages/admin/surveys.jsp");
        dispatcher.forward(request, response);
    }

}
