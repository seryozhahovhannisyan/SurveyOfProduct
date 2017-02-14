package am.pm.dataaccess.sevice.impl;

import am.pm.dataaccess.dao.IProductDao;
import am.pm.dataaccess.dao.impl.ProductDaoImpl;
import am.pm.dataaccess.exception.DatabaseException;
import am.pm.dataaccess.exception.EntityNotFoundException;
import am.pm.dataaccess.exception.InternalErrorException;
import am.pm.dataaccess.model.Product;
import am.pm.dataaccess.sevice.IProductManager;

import java.util.List;
import java.util.Map;

/**
 * Created by Artur on 4/2/2016.
 */
public class ProductManagerImpl implements IProductManager {

    private IProductDao productDao;

    public ProductManagerImpl(){
        productDao = new ProductDaoImpl();
    }

    @Override
    public void add(Product product) throws InternalErrorException {
        try {
            productDao.add(product);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<Product> getByParams(Map<String, Object> params) throws InternalErrorException {
        try {
            return productDao.getByParams(params);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public void delete(int id) throws InternalErrorException, EntityNotFoundException {
        try {
            productDao.delete(id);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }
}