package am.pm.dataaccess.dao.impl;

import am.pm.dataaccess.dao.IProductDao;
import am.pm.dataaccess.exception.DatabaseException;
import am.pm.dataaccess.exception.EntityNotFoundException;
import am.pm.dataaccess.model.Product;
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
public class ProductDaoImpl implements IProductDao {


    @Override
    public void add(Product product) throws DatabaseException {

        String sql = "INSERT INTO `product`(`name`) VALUES (?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            connection =  ConnectionPool.connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getName());
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
    public List<Product> getByParams(Map<String, Object> params) throws DatabaseException {
        String sql = " SELECT * FROM `product`";

        List<Product> products = new ArrayList<>();

        Connection connection = null;
        PreparedStatement pstm = null;

        try {
            connection = ConnectionPool.connect();

            pstm = connection.prepareStatement(sql);


            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Product product = new Product();

                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                products.add(product);
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

        return products;
    }

    @Override
    public Product getById(int id) throws DatabaseException {
        String sql = " SELECT * FROM `product` where id = ?";

        Connection connection = null;
        PreparedStatement pstm = null;
        Product product  = null;

        try {
            connection = ConnectionPool.connect();

            pstm = connection.prepareStatement(sql);
            pstm.setInt(1,id);


            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                product = new Product();
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

        return product;
    }

    @Override
    public void delete(int id) throws DatabaseException, EntityNotFoundException {
        String sql = "DELETE FROM `product` WHERE id = ?";
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
