package am.pm.dataaccess.sevice;

import am.pm.dataaccess.exception.EntityNotFoundException;
import am.pm.dataaccess.exception.InternalErrorException;
import am.pm.dataaccess.model.ProductDetail;

import java.util.List;
import java.util.Map;

/**
 * Created by Artur on 4/2/2016.
 */
public interface IProductDetailManager {

    public void add(ProductDetail detail) throws InternalErrorException;

    public List<ProductDetail> getByParams(Map<String, Object> params) throws InternalErrorException;
    public List<ProductDetail> getQuestions(int surveyId) throws InternalErrorException;
    public ProductDetail getQuestionDetail(int questionId) throws InternalErrorException;

    public void delete(int id) throws InternalErrorException, EntityNotFoundException;

}
