import am.pm.dataaccess.exception.EntityNotFoundException;
import am.pm.dataaccess.exception.InternalErrorException;
import am.pm.dataaccess.model.User;
import am.pm.dataaccess.sevice.IUserManager;
import am.pm.dataaccess.sevice.impl.UserManagerImpl;

/**
 * Created by Artur on 4/2/2016.
 */
public class UserTest {
    public static void main(String[] args) {
        User user = new User();
        try {
            IUserManager userManager = new UserManagerImpl();
            user = userManager.getByEmailAndPassword("e","p");
            userManager = new UserManagerImpl();
            user.setPassword("p2");
            userManager.add(user);
        } catch (InternalErrorException e) {
            e.printStackTrace();
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        }
    }
}
