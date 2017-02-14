package am.pm.controller;

import am.pm.dataaccess.exception.EntityNotFoundException;
import am.pm.dataaccess.exception.InternalErrorException;
import am.pm.dataaccess.model.User;
import am.pm.dataaccess.model.lcp.UserRole;
import am.pm.dataaccess.sevice.IUserManager;
import am.pm.dataaccess.sevice.impl.UserManagerImpl;
import am.pm.util.EncryptException;
import am.pm.util.SHAHashEnrypt;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Artur on 4/2/2016.
 */
public class Initializer implements ServletContextListener  {

    public static ServletContext context;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        boolean initialized = true;
        IUserManager userManager = new UserManagerImpl();
        context = servletContextEvent.getServletContext();

        String password = "123456";
        try {
            password = SHAHashEnrypt.getMD5SecurePassword(password);
        } catch (EncryptException ex) {
            ex.printStackTrace();
        }

        try {
            User user = userManager.getByEmailAndPassword("admin", password);
        } catch (InternalErrorException e) {
            initialized = false;
        } catch (EntityNotFoundException e) {


            User user = new User();
            user.setName("name");
            user.setRole(UserRole.ADMIN);
            user.setName("name");
            user.setSurname("surname");
            user.setEmail("admin");
            user.setPassword(password);

            try {
                userManager.add(user);
            } catch (InternalErrorException e1) {
                initialized = false;
            }
        }

        if(!initialized){
            throw new RuntimeException("unable initialize application");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
