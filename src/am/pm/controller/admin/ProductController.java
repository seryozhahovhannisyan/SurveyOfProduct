package am.pm.controller.admin;

import am.pm.dataaccess.exception.InternalErrorException;
import am.pm.dataaccess.model.Product;
import am.pm.dataaccess.sevice.IProductManager;
import am.pm.dataaccess.sevice.impl.ProductManagerImpl;
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
public class ProductController extends HttpServlet {

    private List<Product> products;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IProductManager productManager = new ProductManagerImpl();
        String nextJSP = "";
        try {
            products = productManager.getByParams(null);
            request.setAttribute("products", products );
            nextJSP = "/WEB-INF/pages/admin/products.jsp";
        } catch (InternalErrorException e) {
            request.getSession().setAttribute(Constant.MSG_ERROR, Constant.MSG_ERROR_INTERNAL);
            nextJSP = "/WEB-INF/pages/admin/home.jsp";

        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request, response);
        return;
    }

    public List<Product> getProducts() {
        return products;
    }
}
