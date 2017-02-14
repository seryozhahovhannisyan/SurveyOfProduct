package am.pm.controller.admin;

import am.pm.dataaccess.exception.EntityNotFoundException;
import am.pm.dataaccess.exception.InternalErrorException;
import am.pm.dataaccess.sevice.IProductDetailManager;
import am.pm.dataaccess.sevice.impl.ProductDetailManagerImpl;
import am.pm.util.Constant;
import am.pm.util.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Artur on 4/2/2016.
 */
public class ProductDetailDeleteController extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (Utils.isEmpty(id)) {
            forward(request, response, "Invalid id");
            return;
        }
        try {
            Integer productId = Integer.parseInt(id);
            IProductDetailManager productDetailManager = new ProductDetailManagerImpl();
            productDetailManager.delete(productId);
            response.sendRedirect("/product-details/view");
        } catch (NumberFormatException e) {
            forward(request, response, "Invalid id");
            return;
        } catch (EntityNotFoundException e) {
            forward(request, response, Constant.MSG_ERROR_INTERNAL);
        } catch (InternalErrorException e) {
            forward(request, response, Constant.MSG_ERROR_INTERNAL);
        }
    }

    private synchronized void forward(HttpServletRequest request, HttpServletResponse response, String msg) throws ServletException, IOException {
        request.getSession().setAttribute(Constant.MSG_ERROR, msg);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/pages/admin/product-details.jsp");
        dispatcher.forward(request, response);
    }
}
