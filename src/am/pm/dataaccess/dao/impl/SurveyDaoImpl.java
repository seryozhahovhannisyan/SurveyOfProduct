package am.pm.dataaccess.dao.impl;

import am.pm.dataaccess.dao.ISurveyDao;
import am.pm.dataaccess.exception.DatabaseException;
import am.pm.dataaccess.exception.EntityNotFoundException;
import am.pm.dataaccess.exception.PermissionDeniedException;
import am.pm.dataaccess.model.Survey;
import am.pm.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Artur on 4/2/2016.
 */
public class SurveyDaoImpl implements ISurveyDao {


    @Override
    public void add(Survey survey) throws DatabaseException {

        String sql = "INSERT INTO `survey`(name,description,created_at,created_by,start_at,deadline) VALUES (?,?,?,?,?,?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            connection = ConnectionPool.connect();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, survey.getName());
            preparedStatement.setString(2, survey.getDescription());
            preparedStatement.setDate(3, new java.sql.Date(survey.getCreatedAt().getTime()));
            preparedStatement.setInt(4, survey.getCreatedBy().getId());
            preparedStatement.setDate(5, new java.sql.Date(survey.getStartAt().getTime()));
            preparedStatement.setDate(6, new java.sql.Date(survey.getDeadline().getTime()));

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw new DatabaseException(e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Survey> getByParams(Map<String, Object> params) throws DatabaseException, PermissionDeniedException {

        Integer userId = (Integer) params.get("userId");
        Integer memberId = (Integer) params.get("memberId");
        int pId = 0;
        String sql;

        if (userId != null) {
            sql = " SELECT s.*,0 isAnswered  FROM `survey` s   where created_by = ?";
            pId = userId;
        } else if (memberId != null) {
            sql =  "select s.* , if (count(a.id),1,0) isAnswered " +
                    " from survey_members m , survey s " +
                    " left join survey_answers a on a.survey_id = s.id " +
                    " where m.survey_id = s.id and m.member_id = ?  group by s.id ";
            pId = memberId;
        } else {
            throw new PermissionDeniedException("The incorrect passed parameter");
        }

        List<Survey> surveys = new ArrayList<>();

        Connection connection = null;
        PreparedStatement pstm = null;

        try {

            connection = ConnectionPool.connect();
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, pId);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Survey survey = new Survey();

                survey.setId(rs.getInt("id"));
                survey.setName(rs.getString("name"));
                survey.setDescription(rs.getString("description"));

                survey.setCreatedAt(new Date(rs.getDate("created_at").getTime()));
                survey.setStartAt(new Date(rs.getDate("start_at").getTime()));
                survey.setDeadline(new Date(rs.getDate("deadline").getTime()));
                survey.setCreatedById(rs.getInt("created_by"));
                survey.setAnswered(rs.getBoolean("isAnswered"));

                surveys.add(survey);
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

        return surveys;
    }

    @Override
    public Survey getById(int id) throws DatabaseException {
        String sql = " SELECT * FROM `survey` where id = ?";

        Connection connection = null;
        PreparedStatement pstm = null;
        Survey survey = null;

        try {
            connection = ConnectionPool.connect();

            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                survey = new Survey();
                survey.setId(rs.getInt("id"));
                survey.setName(rs.getString("name"));
                survey.setDescription(rs.getString("description"));

                survey.setCreatedAt(new Date(rs.getDate("created_at").getTime()));
                survey.setStartAt(new Date(rs.getDate("start_at").getTime()));
                survey.setDeadline(new Date(rs.getDate("deadline").getTime()));
                survey.setCreatedById(rs.getInt("created_by"));
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

        return survey;
    }

    @Override
    public void delete(int id) throws DatabaseException, EntityNotFoundException {
        String sql = "DELETE FROM `survey` WHERE id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            connection = ConnectionPool.connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw new DatabaseException(e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
