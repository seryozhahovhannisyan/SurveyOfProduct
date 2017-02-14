package am.pm.controller.filter;

import am.pm.dataaccess.model.User;
import am.pm.dataaccess.model.lcp.UserRole;
import am.pm.util.Constant;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Artur on 4/2/2016.
 */
public class UserFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest)req).getSession();

        User user = (User) session.getAttribute(Constant.SESSION_USER);
        if(user == null) {
            session.setAttribute(Constant.MSG_ERROR,"Please sign in,The session was expired");
            ((HttpServletResponse) resp).sendRedirect("/go/login");
            return;
        } else if(user.getRole().getId() != UserRole.USER.getId()) {
            session.setAttribute(Constant.MSG_ERROR,"The page not allowed, Please sign in with your user account");
            ((HttpServletResponse) resp).sendRedirect("/go/login");
            return;
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
