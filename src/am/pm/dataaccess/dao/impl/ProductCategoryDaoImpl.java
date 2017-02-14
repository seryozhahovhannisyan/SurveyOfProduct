package am.pm.dataaccess.dao.impl;

import am.pm.dataaccess.dao.IProductCategoryDao;
import am.pm.dataaccess.exception.DatabaseException;
import am.pm.dataaccess.exception.EntityNotFoundException;
import am.pm.dataaccess.model.ProductCategory;
import am.pm.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Artur on 4/2/2016.
 */
public class ProductCategoryDaoImpl implements IProductCategoryDao {


    @Override
    public void add(ProductCategory category) throws DatabaseException {

        String sql = "INSERT INTO `category`(`name`) VALUES (?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            connection =  ConnectionPool.connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, category.getName());
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
    public List<ProductCategory> getByParams(Map<String, Object> params) throws DatabaseException {
        String sql = " SELECT * FROM `category` ";

        List<ProductCategory> categories = new ArrayList<>();

        Connection connection = null;
        PreparedStatement pstm = null;

        try {
            connection = ConnectionPool.connect();

            pstm = connection.prepareStatement(sql);


            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                ProductCategory category = new ProductCategory();

                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                categories.add(category);
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

        return categories;
    }

    @Override
    public ProductCategory getById(int id) throws DatabaseException {
        String sql = " SELECT * FROM `category` where id = ?";

        ProductCategory category = null;

        Connection connection = null;
        PreparedStatement pstm = null;

        try {
            connection = ConnectionPool.connect();

            pstm = connection.prepareStatement(sql);
            pstm.setInt(1,id);

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                category = new ProductCategory();

                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
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

        return category;
    }

    @Override
    public void delete(int id) throws DatabaseException, EntityNotFoundException {
        String sql = "DELETE FROM `category` WHERE id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            connection =  ConnectionPool.connect();
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
