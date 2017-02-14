package am.pm.dataaccess.dao;

import am.pm.dataaccess.exception.DatabaseException;
import am.pm.dataaccess.exception.EntityNotFoundException;
import am.pm.dataaccess.model.ProductType;

import java.util.List;
import java.util.Map;

/**
 * Created by Artur on 4/2/2016.
 */
public interface IProductTypeDao {

    public void add(ProductType productType) throws DatabaseException;

    public List<ProductType> getByParams(Map<String, Object> params) throws DatabaseException;
    public ProductType getById(int id) throws DatabaseException;

    public void delete(int id) throws DatabaseException, EntityNotFoundException;

}
