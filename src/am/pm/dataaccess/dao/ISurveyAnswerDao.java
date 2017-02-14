package am.pm.dataaccess.dao;

import am.pm.dataaccess.exception.DatabaseException;
import am.pm.dataaccess.exception.EntityNotFoundException;
import am.pm.dataaccess.model.SurveyAnswer;

import java.util.List;
import java.util.Map;

/**
 * Created by Artur on 4/2/2016.
 */
public interface ISurveyAnswerDao {

    public void add(int surveyId,List<SurveyAnswer> surveyAnswers) throws DatabaseException;

    public List<SurveyAnswer> getByParams(Map<String, Object> params) throws DatabaseException;
    public SurveyAnswer getById(int id) throws DatabaseException;

}
