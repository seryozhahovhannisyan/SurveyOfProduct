package am.pm.dataaccess.sevice;

import am.pm.dataaccess.exception.EntityNotFoundException;
import am.pm.dataaccess.exception.InternalErrorException;
import am.pm.dataaccess.model.ProductType;

import java.util.List;
import java.util.Map;

/**
 * Created by Artur on 4/2/2016.
 */
public interface IProductTypeManager {

    public void add(ProductType productType) throws InternalErrorException;

    public List<ProductType> getByParams(Map<String, Object> params) throws InternalErrorException;

    public void delete(int id) throws InternalErrorException, EntityNotFoundException;

}
