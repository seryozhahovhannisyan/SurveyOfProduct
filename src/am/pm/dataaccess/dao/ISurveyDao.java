package am.pm.dataaccess.dao;

import am.pm.dataaccess.exception.DatabaseException;
import am.pm.dataaccess.exception.EntityNotFoundException;
import am.pm.dataaccess.exception.PermissionDeniedException;
import am.pm.dataaccess.model.Survey;

import java.util.List;
import java.util.Map;

/**
 * Created by Artur on 4/2/2016.
 */
public interface ISurveyDao {

    public void add(Survey survey) throws DatabaseException;

    public List<Survey> getByParams(Map<String, Object> params) throws DatabaseException,PermissionDeniedException;
    public Survey getById(int id) throws DatabaseException;


    public void delete(int id) throws DatabaseException, EntityNotFoundException;

}
