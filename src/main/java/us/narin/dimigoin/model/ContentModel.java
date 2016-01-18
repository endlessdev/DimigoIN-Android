package us.narin.dimigoin.model;

import android.support.v7.widget.RecyclerView;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Seungwoo on 2015. 12. 24..
 */

//
//"wr_name": "최고관리자",
//        "wr_last": "2016-01-02 18:23:17",
//        "wr_subject": "12341234",
//        "wr_hit": "3",
//        "wr_id": "146",
//        "wr_good": "0"

public class ContentModel {

    @SerializedName("wr_name")
    String contentAuthor;
    @SerializedName("unix_timestamp")
    Integer contentDate;
    @SerializedName("wr_subject")
    String contentSubject;
    @SerializedName("wr_hit")
    Integer contentViewCount;
    @SerializedName("wr_id")
    Integer contentId;
    @SerializedName("wr_good")
    String contentGoodCount;
    @SerializedName("wr_content")
    String contentBody;


    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Integer getContentViewCount() {
        return contentViewCount;
    }

    public void setContentAuthor(String contentAuthor) {
        this.contentAuthor = contentAuthor;
    }

    public String getContentAuthor() {
        return contentAuthor;
    }

    public void setContentDate(Integer contentDate) {
        this.contentDate = contentDate;
    }

    public Integer getContentDate() {
        return contentDate;
    }

    public Date getUnixDate() {
        long unixTimeStamp = (long) contentDate * 1000;
        return new Date(unixTimeStamp);
    }

    public void setContentGoodCount(String contentGoodCount) {
        this.contentGoodCount = contentGoodCount;
    }

    public String getContentGoodCount() {
        return contentGoodCount;
    }

    public void setContentSubject(String contentSubject) {
        this.contentSubject = contentSubject;
    }

    public String getContentSubject() {
        return contentSubject;
    }

    public void setContentViewCount(Integer contentViewCount) {
        this.contentViewCount = contentViewCount;
    }

    public String getContentBody() {
        return contentBody;
    }
}
