package am.pm.controller.admin;

import am.pm.dataaccess.exception.InternalErrorException;
import am.pm.dataaccess.model.ProductCategory;
import am.pm.dataaccess.sevice.IProductCategoryManager;
import am.pm.dataaccess.sevice.impl.ProductCategoryManagerImpl;
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
public class ProductCategoryController extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IProductCategoryManager productCategoryManager = new ProductCategoryManagerImpl();
        String nextJSP = "";
        try {
            List<ProductCategory> productCategories = productCategoryManager.getByParams(null);
            request.setAttribute("productCategories", productCategories );
            nextJSP = "/WEB-INF/pages/admin/product-categories.jsp";
        } catch (InternalErrorException e) {
            request.getSession().setAttribute(Constant.MSG_ERROR, Constant.MSG_ERROR_INTERNAL);
            nextJSP = "/WEB-INF/pages/admin/home.jsp";

        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request, response);
        return;
    }
}
