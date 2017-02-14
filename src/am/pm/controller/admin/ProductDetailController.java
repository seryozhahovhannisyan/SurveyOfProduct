package am.pm.controller.admin;

import am.pm.dataaccess.exception.InternalErrorException;
import am.pm.dataaccess.model.Product;
import am.pm.dataaccess.model.ProductCategory;
import am.pm.dataaccess.model.ProductDetail;
import am.pm.dataaccess.model.ProductType;
import am.pm.dataaccess.sevice.IProductCategoryManager;
import am.pm.dataaccess.sevice.IProductDetailManager;
import am.pm.dataaccess.sevice.IProductManager;
import am.pm.dataaccess.sevice.IProductTypeManager;
import am.pm.dataaccess.sevice.impl.ProductCategoryManagerImpl;
import am.pm.dataaccess.sevice.impl.ProductDetailManagerImpl;
import am.pm.dataaccess.sevice.impl.ProductManagerImpl;
import am.pm.dataaccess.sevice.impl.ProductTypeManagerImpl;
import am.pm.util.Constant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Artur on 4/2/2016.
 */
public class ProductDetailController extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        IProductDetailManager detailManager = new ProductDetailManagerImpl();
        IProductManager productManager = new ProductManagerImpl();
        IProductCategoryManager productCategoryManager = new ProductCategoryManagerImpl();
        IProductTypeManager productTypeManager  = new ProductTypeManagerImpl();

        String nextJSP = "";
        try {

            List<ProductDetail> details = detailManager.getByParams(null);

            List<Product> products = productManager.getByParams(null);
            List<ProductCategory> productCategories = productCategoryManager.getByParams(null);
            List<ProductType> productTypes = productTypeManager.getByParams(null);

            request.setAttribute("details", details);

            request.setAttribute("products", products);
            request.setAttribute("productCategories", productCategories );
            request.setAttribute("productTypes", productTypes );

            nextJSP = "/WEB-INF/pages/admin/product-details.jsp";
        } catch (InternalErrorException e) {
            request.getSession().setAttribute(Constant.MSG_ERROR, Constant.MSG_ERROR_INTERNAL);
            nextJSP = "/WEB-INF/pages/admin/home.jsp";

        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request, response);
        return;
    }
}
