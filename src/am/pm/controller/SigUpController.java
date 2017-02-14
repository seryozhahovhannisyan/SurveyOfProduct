package am.pm.controller;

import am.pm.dataaccess.exception.InternalErrorException;
import am.pm.dataaccess.model.User;
import am.pm.dataaccess.model.lcp.UserRole;
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
public class SigUpController extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (Utils.isEmpty(name)) {
            forward(request, response, "Invalid name");
            return;
        }
        if (Utils.isEmpty(surname)) {
            forward(request, response, "Invalid surname");
            return;
        }
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
            User user = new User();
            user.setRole(UserRole.USER);
            user.setName(name);
            user.setSurname(surname);
            user.setEmail(email);
            user.setPassword(password);

            userManager.add(user);
            String nextJSP = "/user/home";
            HttpSession session = request.getSession();
            session.setAttribute(Constant.SESSION_USER, user);
            response.sendRedirect(nextJSP);
        } catch (InternalErrorException e) {
            forward(request, response, "An internal Error occurred please try later");
            return;
        }
    }

    private synchronized void forward(HttpServletRequest request, HttpServletResponse response, String msg) throws ServletException, IOException {
        request.getSession().setAttribute(Constant.MSG_ERROR, msg);
        String loginJSP = "/WEB-INF/pages/registration.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(loginJSP);
        dispatcher.forward(request, response);
    }
}
