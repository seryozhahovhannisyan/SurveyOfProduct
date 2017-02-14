package am.pm.dataaccess.sevice;

import am.pm.dataaccess.exception.EntityNotFoundException;
import am.pm.dataaccess.exception.InternalErrorException;
import am.pm.dataaccess.model.Product;

import java.util.List;
import java.util.Map;

/**
 * Created by Artur on 4/2/2016.
 */
public interface IProductManager {

    public void add(Product product) throws InternalErrorException;

    public List<Product> getByParams(Map<String, Object> params) throws InternalErrorException;

    public void delete(int id) throws InternalErrorException, EntityNotFoundException;

}
