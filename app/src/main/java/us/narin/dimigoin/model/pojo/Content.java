package us.narin.dimigoin.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Seungwoo on 2015. 12. 24..
 */


public class Content {

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
    @SerializedName("wr_comment")
    Integer contentCommentCount;

    public Integer getContentCommentCount() {
        return contentCommentCount;
    }

    public Integer getContentId() {
        return contentId;
    }

    public Integer getContentViewCount() {
        return contentViewCount;
    }

    public String getContentAuthor() {
        return contentAuthor;
    }

    public Date getUnixDate() {
        long unixTimeStamp = (long) contentDate * 1000;
        return new Date(unixTimeStamp);
    }

    public String getContentGoodCount() {
        return contentGoodCount;
    }

    public String getContentSubject() {
        return contentSubject;
    }

    public String getContentBody() {
        return contentBody;
    }

}
