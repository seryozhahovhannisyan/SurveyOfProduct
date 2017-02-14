package am.pm.dataaccess.dao;

import am.pm.dataaccess.exception.DatabaseException;
import am.pm.dataaccess.exception.EntityNotFoundException;
import am.pm.dataaccess.model.ProductDetail;

import java.util.List;
import java.util.Map;

/**
 * Created by Artur on 4/2/2016.
 */
public interface IProductDetailDao {

    public void add(ProductDetail detail) throws DatabaseException;

    public List<ProductDetail> getByParams(Map<String, Object> params) throws DatabaseException;
    public List<ProductDetail> getQuestions(int surveyId) throws DatabaseException;
    public ProductDetail getQuestionDetail(int questionId) throws DatabaseException;
    public void delete(int id) throws DatabaseException, EntityNotFoundException;

}
