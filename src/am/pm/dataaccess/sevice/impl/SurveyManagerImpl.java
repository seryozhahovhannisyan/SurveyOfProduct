package am.pm.dataaccess.sevice.impl;

import am.pm.dataaccess.dao.ISurveyAnswerDao;
import am.pm.dataaccess.dao.ISurveyDao;
import am.pm.dataaccess.dao.impl.SurveyAnswerDaoImpl;
import am.pm.dataaccess.dao.impl.SurveyDaoImpl;
import am.pm.dataaccess.exception.DatabaseException;
import am.pm.dataaccess.exception.EntityNotFoundException;
import am.pm.dataaccess.exception.InternalErrorException;
import am.pm.dataaccess.exception.PermissionDeniedException;
import am.pm.dataaccess.model.ProductDetail;
import am.pm.dataaccess.model.Survey;
import am.pm.dataaccess.model.SurveyAnswer;
import am.pm.dataaccess.sevice.IProductDetailManager;
import am.pm.dataaccess.sevice.ISurveyManager;
import am.pm.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Artur on 4/9/2016.
 */
public class SurveyManagerImpl implements ISurveyManager {

    private ISurveyDao surveyDao;
    private ISurveyAnswerDao surveyAnswerDao;
    private IProductDetailManager detailManager;

    public SurveyManagerImpl() {
        surveyDao = new SurveyDaoImpl();
        surveyAnswerDao = new SurveyAnswerDaoImpl();
        detailManager = new ProductDetailManagerImpl();
    }

    @Override
    public void add(Survey survey) throws InternalErrorException {
        try {
            surveyDao.add(survey);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<Survey> getByParams(Map<String, Object> params) throws InternalErrorException {
        try {
            List<Survey> surveys = surveyDao.getByParams(params);
            return surveys;
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        } catch (PermissionDeniedException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public Survey getById(int id) throws InternalErrorException {
        try {
            return surveyDao.getById(id);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public Survey getAnsweredById(Map<String,Object> params) throws InternalErrorException {
        Survey survey = new Survey();
        try {
            List<SurveyAnswer>  answers = surveyAnswerDao.getByParams(params);
            for(SurveyAnswer answer : answers){
                int questionId = answer.getSurveyQuestionId();
                ProductDetail productDetail = detailManager.getQuestionDetail(questionId);
                answer.setProductDetail(productDetail);
            }
            survey.setSurveyAnswers(answers);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
        return survey;
    }

    @Override
    public void updateMembersAndQuestions(int surveyId, int[] memberIdes, int[] questionIdes) throws InternalErrorException {

        Connection dbConnection = null;

        PreparedStatement preparedStatementDeleteMembers = null;
        PreparedStatement preparedStatementDeleteQuestions = null;

        PreparedStatement preparedStatementInsertMembers = null;
        PreparedStatement preparedStatementInsertQuestions = null;

        String deleteMembersSql = "DELETE FROM `survey_members` WHERE survey_id = ?";
        String deleteQuestionsSql = "DELETE FROM `survey_question` WHERE survey = ?";

        String insertMembersSql = "insert into `survey_members`(`id`, `survey_id`, `member_id`) values ( NULL,?,?);";
        String insertQuestionsSql = "insert into `survey_question`(`id`, `survey`, `product_detail`) values ( NULL,?,?);";

        try {

            dbConnection = ConnectionPool.connect();
            dbConnection.setAutoCommit(false);

            preparedStatementDeleteMembers = dbConnection.prepareStatement(deleteMembersSql);
            preparedStatementDeleteMembers.setInt(1, surveyId);
            preparedStatementDeleteMembers.executeUpdate();

            preparedStatementDeleteQuestions = dbConnection.prepareStatement(deleteQuestionsSql);
            preparedStatementDeleteQuestions.setInt(1, surveyId);
            preparedStatementDeleteQuestions.executeUpdate();

            for(Integer memberId : memberIdes){
                preparedStatementInsertMembers = dbConnection.prepareStatement(insertMembersSql);
                preparedStatementInsertMembers.setInt(1, surveyId);
                preparedStatementInsertMembers.setInt(2, memberId);
                preparedStatementInsertMembers.executeUpdate();
            }

            for(Integer questionId : questionIdes){
                preparedStatementInsertQuestions = dbConnection.prepareStatement(insertQuestionsSql);
                preparedStatementInsertQuestions.setInt(1, surveyId);
                preparedStatementInsertQuestions.setInt(2, questionId);
                preparedStatementInsertQuestions.executeUpdate();
            }

            dbConnection.commit();

        } catch (Exception e) {

            System.out.println(e.getMessage());
            try {
                dbConnection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            throw new InternalErrorException(e);
        } finally {
            try {
                if (preparedStatementDeleteMembers != null) {
                    preparedStatementDeleteMembers.close();
                }

                if (preparedStatementDeleteQuestions != null) {
                    preparedStatementDeleteQuestions.close();
                }

                if (preparedStatementInsertMembers != null) {
                    preparedStatementInsertMembers.close();
                }

                if (preparedStatementInsertQuestions != null) {
                    preparedStatementInsertQuestions.close();
                }

                if (dbConnection != null) {
                    dbConnection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void answer(int surveyId, List<SurveyAnswer> surveyAnswers) throws InternalErrorException {
        try {
            surveyAnswerDao.add(surveyId, surveyAnswers);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public void delete(int id) throws InternalErrorException, EntityNotFoundException {
        try {
            surveyDao.delete(id);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }
}
