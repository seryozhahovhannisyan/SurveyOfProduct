package am.pm.dataaccess.sevice.impl;

import am.pm.dataaccess.dao.IUserDao;
import am.pm.dataaccess.dao.impl.UserDaoImpl;
import am.pm.dataaccess.exception.DatabaseException;
import am.pm.dataaccess.exception.EntityNotFoundException;
import am.pm.dataaccess.exception.InternalErrorException;
import am.pm.dataaccess.model.User;
import am.pm.dataaccess.sevice.IUserManager;

import java.util.List;

/**
 * Created by Artur on 4/2/2016.
 */
public class UserManagerImpl implements IUserManager {

    private IUserDao userDao;

    public UserManagerImpl() {
        userDao = new UserDaoImpl();
    }

    @Override
    public void add(User user) throws InternalErrorException {
        try {
            userDao.add(user);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public User getByEmailAndPassword(String email, String password) throws InternalErrorException, EntityNotFoundException {
        try {
            return userDao.getByEmailAndPassword(email, password);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<User> getMembers(int exceptUserId, int surveyId) throws InternalErrorException {
        try {
            return userDao.getMembers(exceptUserId, surveyId);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }
}
