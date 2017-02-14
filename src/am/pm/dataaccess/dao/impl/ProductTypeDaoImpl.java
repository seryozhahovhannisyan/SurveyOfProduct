package am.pm.dataaccess.dao.impl;

import am.pm.dataaccess.dao.IProductTypeDao;
import am.pm.dataaccess.exception.DatabaseException;
import am.pm.dataaccess.exception.EntityNotFoundException;
import am.pm.dataaccess.model.ProductType;
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
public class ProductTypeDaoImpl implements IProductTypeDao {


    @Override
    public void add(ProductType productType) throws DatabaseException {

        String sql = "INSERT INTO `product_type`(`type`) VALUES (?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            connection =  ConnectionPool.connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productType.getType());
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
    public List<ProductType> getByParams(Map<String, Object> params) throws DatabaseException {
        String sql = " SELECT * FROM `product_type` ";

        List<ProductType> productTypes = new ArrayList<>();

        Connection connection = null;
        PreparedStatement pstm = null;

        try {
            connection = ConnectionPool.connect();

            pstm = connection.prepareStatement(sql);


            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                ProductType productType = new ProductType();

                productType.setId(rs.getInt("id"));
                productType.setType(rs.getString("type"));
                productTypes.add(productType);
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

        return productTypes;
    }

    @Override
    public ProductType getById(int id) throws DatabaseException {
        String sql = " SELECT * FROM `product_type` where id = ?";

        ProductType productType = null;

        Connection connection = null;
        PreparedStatement pstm = null;

        try {
            connection = ConnectionPool.connect();

            pstm = connection.prepareStatement(sql);
            pstm.setInt(1,id);

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                productType = new ProductType();

                productType.setId(rs.getInt("id"));
                productType.setType(rs.getString("type"));
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

        return productType;
    }

    @Override
    public void delete(int id) throws DatabaseException, EntityNotFoundException {
        String sql = "DELETE FROM `product_type` WHERE id = ?";
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
