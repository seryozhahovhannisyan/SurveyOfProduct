package am.pm.dataaccess.sevice.impl;

import am.pm.dataaccess.dao.IProductCategoryDao;
import am.pm.dataaccess.dao.impl.ProductCategoryDaoImpl;
import am.pm.dataaccess.exception.DatabaseException;
import am.pm.dataaccess.exception.EntityNotFoundException;
import am.pm.dataaccess.exception.InternalErrorException;
import am.pm.dataaccess.model.ProductCategory;
import am.pm.dataaccess.sevice.IProductCategoryManager;

import java.util.List;
import java.util.Map;

/**
 * Created by Artur on 4/2/2016.
 */
public class ProductCategoryManagerImpl implements IProductCategoryManager {

    private IProductCategoryDao productCategoryDao;

    public ProductCategoryManagerImpl(){
        productCategoryDao = new ProductCategoryDaoImpl();
    }

    @Override
    public void add(ProductCategory category) throws InternalErrorException {
        try {
            productCategoryDao.add(category);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<ProductCategory> getByParams(Map<String, Object> params) throws InternalErrorException {
        try {
            return productCategoryDao.getByParams(params);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public void delete(int id) throws InternalErrorException, EntityNotFoundException {
        try {
            productCategoryDao.delete(id);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }
}
