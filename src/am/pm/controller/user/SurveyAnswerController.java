package am.pm.controller.user;

import am.pm.dataaccess.model.ProductDetail;
import am.pm.dataaccess.model.SurveyAnswer;
import am.pm.dataaccess.model.User;
import am.pm.dataaccess.sevice.IProductDetailManager;
import am.pm.dataaccess.sevice.ISurveyManager;
import am.pm.dataaccess.sevice.impl.ProductDetailManagerImpl;
import am.pm.dataaccess.sevice.impl.SurveyManagerImpl;
import am.pm.util.Constant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Artur on 4/2/2016.
 */
public class SurveyAnswerController extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pSurveyId = request.getParameter("surveyId");

        User user = (User) request.getSession().getAttribute(Constant.SESSION_USER);
        ISurveyManager surveyManager = new SurveyManagerImpl();
        IProductDetailManager detailManager = new ProductDetailManagerImpl();
        List<SurveyAnswer> surveyAnswers = new ArrayList<>();

        try {

            int surveyId = Integer.parseInt(pSurveyId);
            List<ProductDetail> questions = detailManager.getQuestions(surveyId);

            for(ProductDetail detail : questions){
                if(detail.isQuestion()){
                    String pRating = request.getParameter("surveyAnswers[" + detail.getId() + "].rating");
                    String answer = request.getParameter("surveyAnswers[" + detail.getId() + "].answer");
                    int rating = Integer.parseInt(pRating);

                    SurveyAnswer surveyAnswer =  new SurveyAnswer();
                    surveyAnswer.setAnsweredBy(user.getId());
                    surveyAnswer.setAnsweredAt(new Date(System.currentTimeMillis()));
                    surveyAnswer.setRating(rating);
                    surveyAnswer.setSurveyQuestionId(detail.getSurveyQuestionId());
                    surveyAnswer.setProductDetailId(detail.getId());

                    if(rating == 0){
                        surveyAnswer.setAnswer(answer);
                    }
                    surveyAnswers.add(surveyAnswer);
                }
            }
            surveyManager.answer(surveyId, surveyAnswers);
            response.sendRedirect("/user/surveys/view");
            return;
        }catch ( Exception e){
            request.getSession().setAttribute(Constant.MSG_ERROR, Constant.MSG_ERROR_INTERNAL);
            String  nextJSP = "/WEB-INF/pages/user/home.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request, response);
            return;
        }
    }
}
