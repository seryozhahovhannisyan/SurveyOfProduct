package am.pm.dataaccess.dao.impl;

import am.pm.dataaccess.dao.IProductDetailDao;
import am.pm.dataaccess.exception.DatabaseException;
import am.pm.dataaccess.exception.EntityNotFoundException;
import am.pm.dataaccess.model.ProductDetail;
import am.pm.dataaccess.model.lcp.Priority;
import am.pm.util.ConnectionPool;
import am.pm.util.CurrencyType;
import am.pm.util.ISO_3166_CountryCode;

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
public class ProductDetailDaoImpl implements IProductDetailDao {


    @Override
    public void add(ProductDetail ProductDetail) throws DatabaseException {

        String sql = "INSERT INTO `product_detail`(" +
                "priority," +
                "product," +
                "type," +
                "category," +
                "price_min," +
                "price_max," +
                "currency," +
                "country" +

                ") VALUES (?,?,?,?,?,?,?,?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            connection = ConnectionPool.connect();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, ProductDetail.getPriorityId());
            preparedStatement.setInt(2, ProductDetail.getProductId());
            preparedStatement.setInt(3, ProductDetail.getTypeId());
            preparedStatement.setInt(4, ProductDetail.getCategoryId());
            preparedStatement.setDouble(5, ProductDetail.getPriceMin());
            preparedStatement.setDouble(6, ProductDetail.getPriceMax());
            preparedStatement.setInt(7, ProductDetail.getCurrency().getId());
            preparedStatement.setString(8, ProductDetail.getCountry().getCountryCodeNM());

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
    public List<ProductDetail> getByParams(Map<String, Object> params) throws DatabaseException {
        String sql = " SELECT * FROM `product_detail`";

        List<ProductDetail> details = new ArrayList<>();

        Connection connection = null;
        PreparedStatement pstm = null;

        try {
            connection = ConnectionPool.connect();

            pstm = connection.prepareStatement(sql);


            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                ProductDetail detail = new ProductDetail();

                detail.setId(rs.getInt("id"));
                detail.setPriority(Priority.valueOf(rs.getInt("priority")));
                detail.setProductId(rs.getInt("product"));
                detail.setTypeId(rs.getInt("type"));
                detail.setCategoryId(rs.getInt("category"));
                detail.setPriceMin(rs.getDouble("price_min"));
                detail.setPriceMax(rs.getDouble("price_max"));
                detail.setCurrency(CurrencyType.valueOf(rs.getInt("currency")));
                detail.setCountry(ISO_3166_CountryCode.valueOfNMCode(rs.getString("country")));

                details.add(detail);
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

        return details;
    }

    @Override
    public List<ProductDetail> getQuestions(int surveyId) throws DatabaseException {
        String sql = "select d.*, if(q.id is null,0,1) isQuestion, q.id  surveyQuestionId " +
                " from product_detail d " +
                " left join survey_question q " +
                " on d.id = q.product_detail and q.survey = ? ";

        List<ProductDetail> details = new ArrayList<>();

        Connection connection = null;
        PreparedStatement pstm = null;

        try {
            connection = ConnectionPool.connect();

            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, surveyId);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                ProductDetail detail = new ProductDetail();

                detail.setId(rs.getInt("id"));
                detail.setPriority(Priority.valueOf(rs.getInt("priority")));
                detail.setProductId(rs.getInt("product"));
                detail.setTypeId(rs.getInt("type"));
                detail.setCategoryId(rs.getInt("category"));
                detail.setPriceMin(rs.getDouble("price_min"));
                detail.setPriceMax(rs.getDouble("price_max"));
                detail.setCurrency(CurrencyType.valueOf(rs.getInt("currency")));
                detail.setCountry(ISO_3166_CountryCode.valueOfNMCode(rs.getString("country")));

                detail.setQuestion(rs.getBoolean("isQuestion"));
                detail.setSurveyQuestionId(rs.getInt("surveyQuestionId"));

                details.add(detail);
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

        return details;
    }

    @Override
    public ProductDetail getQuestionDetail(int questionId) throws DatabaseException {
        String sql = " SELECT d.* from product_detail d, survey_question  q WHERE d.id = q.product_detail and q.id = ? ";

        ProductDetail detail = null;

        Connection connection = null;
        PreparedStatement pstm = null;

        try {

            connection = ConnectionPool.connect();
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, questionId);

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                detail = new ProductDetail();

                detail.setId(rs.getInt("id"));
                detail.setPriority(Priority.valueOf(rs.getInt("priority")));
                detail.setProductId(rs.getInt("product"));
                detail.setTypeId(rs.getInt("type"));
                detail.setCategoryId(rs.getInt("category"));
                detail.setPriceMin(rs.getDouble("price_min"));
                detail.setPriceMax(rs.getDouble("price_max"));
                detail.setCurrency(CurrencyType.valueOf(rs.getInt("currency")));
                detail.setCountry(ISO_3166_CountryCode.valueOfNMCode(rs.getString("country")));
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

        return detail;
    }


    @Override
    public void delete(int id) throws DatabaseException, EntityNotFoundException {
        String sql = "DELETE FROM `product_detail` WHERE id = ?";
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
