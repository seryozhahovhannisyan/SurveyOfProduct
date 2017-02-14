package am.pm.dataaccess.dao;

import am.pm.dataaccess.exception.DatabaseException;
import am.pm.dataaccess.exception.EntityNotFoundException;
import am.pm.dataaccess.model.Product;

import java.util.List;
import java.util.Map;

/**
 * Created by Artur on 4/2/2016.
 */
public interface IProductDao {

    public void add(Product product) throws DatabaseException;

    public List<Product> getByParams(Map<String, Object> params) throws DatabaseException;
    public Product getById(int id) throws DatabaseException;


    public void delete(int id) throws DatabaseException, EntityNotFoundException;

}
