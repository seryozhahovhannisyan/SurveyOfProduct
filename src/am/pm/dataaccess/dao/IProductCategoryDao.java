package am.pm.dataaccess.dao;

import am.pm.dataaccess.exception.DatabaseException;
import am.pm.dataaccess.exception.EntityNotFoundException;
import am.pm.dataaccess.model.ProductCategory;

import java.util.List;
import java.util.Map;

/**
 * Created by Artur on 4/2/2016.
 */
public interface IProductCategoryDao {

    public void add(ProductCategory category) throws DatabaseException;

    public List<ProductCategory> getByParams(Map<String, Object> params) throws DatabaseException;
    public ProductCategory getById(int id) throws DatabaseException;

    public void delete(int id) throws DatabaseException, EntityNotFoundException;

}
