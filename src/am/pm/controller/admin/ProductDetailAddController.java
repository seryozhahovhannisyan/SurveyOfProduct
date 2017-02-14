package am.pm.controller.admin;

import am.pm.dataaccess.exception.InternalErrorException;
import am.pm.dataaccess.model.ProductDetail;
import am.pm.dataaccess.sevice.IProductDetailManager;
import am.pm.dataaccess.sevice.impl.ProductDetailManagerImpl;
import am.pm.util.Constant;
import am.pm.util.CurrencyType;
import am.pm.util.ISO_3166_CountryCode;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Artur on 4/2/2016.
 */
public class ProductDetailAddController extends HttpServlet {


    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String priority = request.getParameter("priorityId");
        String product = request.getParameter("productId");
        String type = request.getParameter("typeId");
        String category = request.getParameter("categoryId");

        String pMin = request.getParameter("priceMin");
        String pMax = request.getParameter("priceMax");

        String currencyI = request.getParameter("currency");
        String pCountry = request.getParameter("country");
        ISO_3166_CountryCode country = ISO_3166_CountryCode.valueOfNMCode(pCountry);

        int priorityId = 0;
        int productId = 0;
        int typeId = 0;
        double priceMin = 0;
        int currency = 0;

        try {
            priorityId = Integer.parseInt(priority);
            productId = Integer.parseInt(product);
            typeId = Integer.parseInt(type);
            priceMin = Double.parseDouble(pMin);
            currency = Integer.parseInt(currencyI);
        } catch (Exception e) {
            forward(request, response, "Invalid data");
            return;
        }

        if(country == null){
            forward(request, response, "Invalid data");
            return;
        }

        int categoryId = 0;
        double priceMax = 0;

        try {
            categoryId = Integer.parseInt(category);
            priceMax = Double.parseDouble(pMax);
        } catch (Exception e) {
        }

        ProductDetail detail = new ProductDetail();
        detail.setPriorityId(priorityId);
        detail.setProductId(productId);
        detail.setTypeId(typeId);
        detail.setCategoryId(categoryId);

        detail.setPriceMin(priceMin);
        detail.setPriceMax(priceMax);

        detail.setCurrency(CurrencyType.valueOf(currency));
        detail.setCountry(country);

        IProductDetailManager productDetailManager = new ProductDetailManagerImpl();
        try {
            productDetailManager.add(detail);
            response.sendRedirect("/product-details/view");
        } catch (InternalErrorException e) {
            forward(request, response, Constant.MSG_ERROR_INTERNAL);
            return;
        }
    }

    private synchronized void forward(HttpServletRequest request, HttpServletResponse response, String msg) throws ServletException, IOException {
        request.getSession().setAttribute(Constant.MSG_ERROR, msg);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/pages/admin/product-details.jsp");
        dispatcher.forward(request, response);
    }
}
