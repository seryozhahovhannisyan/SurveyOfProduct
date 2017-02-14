package am.pm.dataaccess.sevice;

import am.pm.dataaccess.exception.EntityNotFoundException;
import am.pm.dataaccess.exception.InternalErrorException;
import am.pm.dataaccess.model.ProductCategory;

import java.util.List;
import java.util.Map;

/**
 * Created by Artur on 4/2/2016.
 */
public interface IProductCategoryManager {

    public void add(ProductCategory category) throws InternalErrorException;

    public List<ProductCategory> getByParams(Map<String, Object> params) throws InternalErrorException;

    public void delete(int id) throws InternalErrorException, EntityNotFoundException;

}
