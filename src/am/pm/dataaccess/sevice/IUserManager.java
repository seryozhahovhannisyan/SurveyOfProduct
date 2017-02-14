package am.pm.dataaccess.sevice;

import am.pm.dataaccess.exception.EntityNotFoundException;
import am.pm.dataaccess.exception.InternalErrorException;
import am.pm.dataaccess.model.User;

import java.util.List;

/**
 * Created by Artur on 4/2/2016.
 */
public interface IUserManager {

    public void add(User user) throws InternalErrorException;

    public User getByEmailAndPassword(String email, String password) throws InternalErrorException, EntityNotFoundException;

    public List<User> getMembers(int exceptUserId, int surveyId) throws InternalErrorException;

   // public void makeUserAsAdmin();

}
