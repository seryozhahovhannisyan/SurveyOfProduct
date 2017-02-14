package am.pm.dataaccess.model;

import java.util.Date;
import java.util.List;

/**
 * Created by Artur on 4/5/2016.
 */
public class Survey {

    private int id;
    private String name;
    private String description;
    private Date createdAt;
    private User createdBy;
    private int createdById;
    private Date startAt;
    private Date deadline;

    private List<Integer> memberIdes;
    private List<User> members;

    private List<Integer> surveyQuestionIdes;
    private List<SurveyQuestion> surveyQuestions;

    private List<Integer> surveyAnswerIdes;
    private List<SurveyAnswer> surveyAnswers;
    private boolean isAnswered;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public List<Integer> getMemberIdes() {
        return memberIdes;
    }

    public void setMemberIdes(List<Integer> memberIdes) {
        this.memberIdes = memberIdes;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public List<Integer> getSurveyQuestionIdes() {
        return surveyQuestionIdes;
    }

    public void setSurveyQuestionIdes(List<Integer> surveyQuestionIdes) {
        this.surveyQuestionIdes = surveyQuestionIdes;
    }

    public List<SurveyQuestion> getSurveyQuestions() {
        return surveyQuestions;
    }

    public void setSurveyQuestions(List<SurveyQuestion> surveyQuestions) {
        this.surveyQuestions = surveyQuestions;
    }

    public List<Integer> getSurveyAnswerIdes() {
        return surveyAnswerIdes;
    }

    public void setSurveyAnswerIdes(List<Integer> surveyAnswerIdes) {
        this.surveyAnswerIdes = surveyAnswerIdes;
    }

    public List<SurveyAnswer> getSurveyAnswers() {
        return surveyAnswers;
    }

    public void setSurveyAnswers(List<SurveyAnswer> surveyAnswers) {
        this.surveyAnswers = surveyAnswers;
    }

    public int getCreatedById() {
        return createdById;
    }

    public void setCreatedById(int createdById) {
        this.createdById = createdById;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }
}
