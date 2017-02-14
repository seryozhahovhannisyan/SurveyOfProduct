package am.pm.dataaccess.dao.impl;

import am.pm.dataaccess.dao.IUserDao;
import am.pm.dataaccess.exception.DatabaseException;
import am.pm.dataaccess.exception.EntityNotFoundException;
import am.pm.dataaccess.model.User;
import am.pm.dataaccess.model.lcp.UserRole;
import am.pm.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artur on 4/2/2016.
 */
public class UserDaoImpl implements IUserDao {


    @Override
    public void add(User user) throws DatabaseException {

        String sql = "INSERT INTO `user`(`name`,`surname`, `email`,`password`, `role` ) VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            connection = ConnectionPool.connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, user.getRole().getId());

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
    public User getByEmailAndPassword(String email, String password) throws DatabaseException, EntityNotFoundException {
        String sql = " SELECT * FROM `user` WHERE email = ? AND password = ? ";

        User user = new User();
        int count = 0;

        Connection connection = null;
        PreparedStatement pstm = null;

        try {
            connection = ConnectionPool.connect();

            pstm = connection.prepareStatement(sql);
            pstm.setString(1, email);
            pstm.setString(2, password);

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                count++;
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setEmail(rs.getString("email"));
                user.setRole(UserRole.valueOf(rs.getInt("role")));
            }

            if (count != 1) {
                throw new EntityNotFoundException(String.format("Could not found user email = [%s], password = [%s]", email, password));
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

        return user;
    }

    @Override
    public List<User> getMembers(int exceptUserId, int surveyId) throws DatabaseException {
        String sql = "SELECT u.*," +
                "CASE " +
                "    WHEN EXISTS( SELECT m.id  " +
                "                     FROM  survey_members m " +
                "                     WHERE m.member_id = u.id and m.survey_id = ? ) " +
                "    THEN 1 " +
                "    ELSE 0 " +
                " END isMember       " +
                " FROM `user` u " +
                " WHERE id not in(?)";

        List<User> users = new ArrayList<>();

        Connection connection = null;
        PreparedStatement pstm = null;

        try {
            connection = ConnectionPool.connect();

            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, surveyId);
            pstm.setInt(2, exceptUserId);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setMember(rs.getBoolean("isMember"));

                users.add(user);
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

        return users;
    }
}
