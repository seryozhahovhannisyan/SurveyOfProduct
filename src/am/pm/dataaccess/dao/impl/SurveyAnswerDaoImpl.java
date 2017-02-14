package am.pm.dataaccess.dao.impl;

import am.pm.dataaccess.dao.ISurveyAnswerDao;
import am.pm.dataaccess.exception.DatabaseException;
import am.pm.dataaccess.exception.EntityNotFoundException;
import am.pm.dataaccess.exception.InternalErrorException;
import am.pm.dataaccess.model.Survey;
import am.pm.dataaccess.model.SurveyAnswer;
import am.pm.util.ConnectionPool;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by Artur on 4/2/2016.
 */
public class SurveyAnswerDaoImpl implements ISurveyAnswerDao {


    @Override
    public void add(int surveyId, List<SurveyAnswer> surveyAnswers) throws DatabaseException {

        Connection dbConnection = null;

        PreparedStatement preparedStatementInsert  = null;
        String insertSql = "insert into survey_answers (id, survey_id, survey_question_id, answered_by, rating, answer, answered_at) " +
                "values ( NULL,?,?,?,?,?,?);";

        try {

            dbConnection = ConnectionPool.connect();
            dbConnection.setAutoCommit(false);

            for(SurveyAnswer answer : surveyAnswers){
                preparedStatementInsert = dbConnection.prepareStatement(insertSql);
                preparedStatementInsert.setInt(1, surveyId);
                preparedStatementInsert.setInt(2, answer.getSurveyQuestionId());
                preparedStatementInsert.setInt(3, answer.getAnsweredBy());
                preparedStatementInsert.setInt(4, answer.getRating());
                preparedStatementInsert.setString(5, answer.getAnswer());
                preparedStatementInsert.setDate(6, new Date(answer.getAnsweredAt().getTime()));
                preparedStatementInsert.executeUpdate();
            }

            dbConnection.commit();

        } catch (Exception e) {

            System.out.println(e.getMessage());
            try {
                dbConnection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            throw new DatabaseException(e);
        } finally {
            try {
                if (preparedStatementInsert != null) {
                    preparedStatementInsert.close();
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
    public List<SurveyAnswer> getByParams(Map<String, Object> params) throws DatabaseException {

        String sql = " SELECT * from survey_answers where survey_id = ? and answered_by = ?";

        List<SurveyAnswer> surveyAnswers = new ArrayList<SurveyAnswer>();

        Connection connection = null;
        PreparedStatement pstm = null;

        try {

            int surveyId = (Integer) params.get("surveyId");
            int memberId = (Integer) params.get("memberId");

            connection = ConnectionPool.connect();
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1,surveyId);
            pstm.setInt(2,memberId);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                SurveyAnswer answer = new SurveyAnswer();
                answer.setId(rs.getInt("id"));
                answer.setSurveyQuestionId(rs.getInt("survey_question_id"));
                answer.setRating(rs.getInt("rating"));
                answer.setAnswer(rs.getString("answer"));
                answer.setAnsweredAt(new java.util.Date(rs.getDate("answered_at").getTime()));
                surveyAnswers.add(answer);
            }

            rs.close();
        } catch (Exception ex) {
            throw new DatabaseException(ex);
        } finally {
            try {
                if (pstm != null)
                    pstm.close();
            } catch (SQLException se) {
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return surveyAnswers;
    }

    @Override
    public SurveyAnswer getById(int id) throws DatabaseException {
        String sql = " SELECT * FROM `product` where id = ?";

        Connection connection = null;
        PreparedStatement pstm = null;
        Survey product  = null;

        try {
            connection = ConnectionPool.connect();

            pstm = connection.prepareStatement(sql);
            pstm.setInt(1,id);


            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                product = new Survey();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
            }

            rs.close();
        } catch (SQLException ex) {
            throw new DatabaseException(ex);
        } finally {
            try {
                if (pstm != null)
                    pstm.close();
            } catch (SQLException se) {
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

}
