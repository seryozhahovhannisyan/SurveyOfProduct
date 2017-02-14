package am.pm.dataaccess.sevice;

import am.pm.dataaccess.exception.EntityNotFoundException;
import am.pm.dataaccess.exception.InternalErrorException;
import am.pm.dataaccess.model.Survey;
import am.pm.dataaccess.model.SurveyAnswer;

import java.util.List;
import java.util.Map;

/**
 * Created by Artur on 4/2/2016.
 */
public interface ISurveyManager {

    public void add(Survey survey) throws InternalErrorException;

    public List<Survey> getByParams(Map<String, Object> params) throws InternalErrorException;

    public Survey getById(int id) throws InternalErrorException;
    public Survey getAnsweredById(Map<String,Object> params) throws InternalErrorException;

    public void updateMembersAndQuestions(int surveyId, int[] memberIdes, int[] questionIdes) throws InternalErrorException;
    public void answer(int surveyId, List<SurveyAnswer> surveyAnswers ) throws InternalErrorException;


    public void delete(int id) throws InternalErrorException, EntityNotFoundException;

}
