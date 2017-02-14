package am.pm.dataaccess.model;

import java.util.Date;

/**
 * Created by Artur on 4/5/2016.
 */
public class SurveyAnswer {

    private int id;
    private int surveyQuestionId;
    private int productDetailId;
    private int answeredBy;
    private int rating;
    private String answer;
    private Date answeredAt;

    private ProductDetail productDetail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSurveyQuestionId() {
        return surveyQuestionId;
    }

    public void setSurveyQuestionId(int surveyQuestionId) {
        this.surveyQuestionId = surveyQuestionId;
    }

    public int getAnsweredBy() {
        return answeredBy;
    }

    public void setAnsweredBy(int answeredBy) {
        this.answeredBy = answeredBy;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getAnsweredAt() {
        return answeredAt;
    }

    public void setAnsweredAt(Date answeredAt) {
        this.answeredAt = answeredAt;
    }

    public int getProductDetailId() {
        return productDetailId;
    }

    public void setProductDetailId(int productDetailId) {
        this.productDetailId = productDetailId;
    }

    public ProductDetail getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;
    }
}
