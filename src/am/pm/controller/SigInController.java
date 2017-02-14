package am.pm.controller;

import am.pm.dataaccess.exception.EntityNotFoundException;
import am.pm.dataaccess.exception.InternalErrorException;
import am.pm.dataaccess.model.User;
import am.pm.dataaccess.sevice.IUserManager;
import am.pm.dataaccess.sevice.impl.UserManagerImpl;
import am.pm.util.Constant;
import am.pm.util.EncryptException;
import am.pm.util.SHAHashEnrypt;
import am.pm.util.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Artur on 4/2/2016.
 */
public class SigInController extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (Utils.isEmpty(email)) {
            forward(request, response, "Invalid email");
            return;
        }
        if (Utils.isEmpty(password)) {
            forward(request, response, "Invalid password");
            return;
        }

        try {
            password = SHAHashEnrypt.getMD5SecurePassword(password);
        } catch (EncryptException e) {
            e.printStackTrace();
        }

        IUserManager userManager = new UserManagerImpl();
        try {
            User user = userManager.getByEmailAndPassword(email, password);
            HttpSession session = request.getSession();
            session.setAttribute(Constant.SESSION_USER, user);
            String role = user.getRole().getRole();
            String nextJSP = String.format("/%s/home", role);
            response.sendRedirect(nextJSP);
        } catch (InternalErrorException e) {
            forward(request, response, "An internal Error occurred please try later");
            return;
        } catch (EntityNotFoundException e) {
            forward(request, response, "Could not found user");
            return;
        }

    }

    private synchronized void forward(HttpServletRequest request, HttpServletResponse response, String msg) throws ServletException, IOException {
        request.getSession().setAttribute(Constant.MSG_ERROR, msg);
        String loginJSP = "/WEB-INF/pages/login.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(loginJSP);
        dispatcher.forward(request, response);
    }
}
