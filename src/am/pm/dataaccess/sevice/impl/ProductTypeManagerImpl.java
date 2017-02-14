package am.pm.dataaccess.sevice.impl;

import am.pm.dataaccess.dao.IProductTypeDao;
import am.pm.dataaccess.dao.impl.ProductTypeDaoImpl;
import am.pm.dataaccess.exception.DatabaseException;
import am.pm.dataaccess.exception.EntityNotFoundException;
import am.pm.dataaccess.exception.InternalErrorException;
import am.pm.dataaccess.model.ProductType;
import am.pm.dataaccess.sevice.IProductTypeManager;

import java.util.List;
import java.util.Map;

/**
 * Created by Artur on 4/2/2016.
 */
public class ProductTypeManagerImpl implements IProductTypeManager {

    private IProductTypeDao productTypeDao;

    public ProductTypeManagerImpl(){
        productTypeDao = new ProductTypeDaoImpl();
    }

    @Override
    public void add(ProductType productType) throws InternalErrorException {
        try {
            productTypeDao.add(productType);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<ProductType> getByParams(Map<String, Object> params) throws InternalErrorException {
        try {
            return productTypeDao.getByParams(params);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public void delete(int id) throws InternalErrorException, EntityNotFoundException {
        try {
            productTypeDao.delete(id);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }
}