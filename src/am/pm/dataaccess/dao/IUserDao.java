package am.pm.dataaccess.dao;

import am.pm.dataaccess.exception.DatabaseException;
import am.pm.dataaccess.exception.EntityNotFoundException;
import am.pm.dataaccess.model.User;

import java.util.List;

/**
 * Created by Artur on 4/2/2016.
 */
public interface IUserDao {

    public void add(User user) throws DatabaseException;

    public User getByEmailAndPassword(String email, String password) throws DatabaseException, EntityNotFoundException;

    public List<User> getMembers(int exceptUserId, int surveyId) throws DatabaseException;

}
