package am.pm.dataaccess.sevice.impl;

import am.pm.dataaccess.dao.IProductCategoryDao;
import am.pm.dataaccess.dao.IProductDao;
import am.pm.dataaccess.dao.IProductDetailDao;
import am.pm.dataaccess.dao.IProductTypeDao;
import am.pm.dataaccess.dao.impl.ProductCategoryDaoImpl;
import am.pm.dataaccess.dao.impl.ProductDaoImpl;
import am.pm.dataaccess.dao.impl.ProductDetailDaoImpl;
import am.pm.dataaccess.dao.impl.ProductTypeDaoImpl;
import am.pm.dataaccess.exception.DatabaseException;
import am.pm.dataaccess.exception.EntityNotFoundException;
import am.pm.dataaccess.exception.InternalErrorException;
import am.pm.dataaccess.model.Product;
import am.pm.dataaccess.model.ProductCategory;
import am.pm.dataaccess.model.ProductDetail;
import am.pm.dataaccess.model.ProductType;
import am.pm.dataaccess.sevice.IProductDetailManager;

import java.util.List;
import java.util.Map;

/**
 * Created by Artur on 4/2/2016.
 */
public class ProductDetailManagerImpl implements IProductDetailManager {

    private IProductDetailDao detailDao;

    private IProductDao productDao;
    private IProductTypeDao productTypeDao;
    private IProductCategoryDao categoryDao;

    public ProductDetailManagerImpl() {
        detailDao = new ProductDetailDaoImpl();

        productDao = new ProductDaoImpl();
        productTypeDao = new ProductTypeDaoImpl();
        categoryDao = new ProductCategoryDaoImpl();

    }

    @Override
    public void add(ProductDetail detail) throws InternalErrorException {
        try {
            detailDao.add(detail);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<ProductDetail> getByParams(Map<String, Object> params) throws InternalErrorException {
        try {
            List<ProductDetail> details = detailDao.getByParams(params);
            for (ProductDetail detail : details) {
                int productId = detail.getProductId();
                int typeId = detail.getTypeId();
                int categoryId = detail.getCategoryId();

                Product product = productDao.getById(productId);
                ProductType productType = productTypeDao.getById(typeId);
                ProductCategory productCategory = categoryDao.getById(categoryId);

                detail.setProduct(product);
                detail.setType(productType);
                detail.setCategory(productCategory);

            }
            return details;
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<ProductDetail> getQuestions(int surveyId) throws InternalErrorException {
        try {
            List<ProductDetail> details = detailDao.getQuestions(surveyId);
            for (ProductDetail detail : details) {
                int productId = detail.getProductId();
                int typeId = detail.getTypeId();
                int categoryId = detail.getCategoryId();

                Product product = productDao.getById(productId);
                ProductType productType = productTypeDao.getById(typeId);
                ProductCategory productCategory = categoryDao.getById(categoryId);

                detail.setProduct(product);
                detail.setType(productType);
                detail.setCategory(productCategory);

            }
            return details;
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public ProductDetail getQuestionDetail(int questionId) throws InternalErrorException {
        try {
            ProductDetail detail = detailDao.getQuestionDetail(questionId);
            int productId = detail.getProductId();
            int typeId = detail.getTypeId();
            int categoryId = detail.getCategoryId();

            Product product = productDao.getById(productId);
            ProductType productType = productTypeDao.getById(typeId);
            ProductCategory productCategory = categoryDao.getById(categoryId);

            detail.setProduct(product);
            detail.setType(productType);
            detail.setCategory(productCategory);
            return detail;
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public void delete(int id) throws InternalErrorException, EntityNotFoundException {
        try {
            detailDao.delete(id);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }
}