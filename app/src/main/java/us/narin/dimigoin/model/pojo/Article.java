package us.narin.dimigoin.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Seungwoo on 2016. 1. 4..
 */
public class Article {

    @SerializedName("wr_hit")
    Integer viewCount;
    @SerializedName("unix_timestamp")
    Integer postTime;
    @SerializedName("wr_name")
    String authorName;
    @SerializedName("wr_last")
    String postDate;
    @SerializedName("wr_content")
    String contentBody;
    @SerializedName("wr_good")
    Integer goodCount;
    @SerializedName("wr_subject")
    String contentSubject;
    @SerializedName("wr_reply")
    String commentCount;
    @SerializedName("wr_id")
    Integer contentId;

    public Date getPostTime() {
        return new Date((long)postTime*1000);
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getPostDate() {
        return postDate;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public Integer getGoodCount() {
        return goodCount;
    }

    public String getContentBody() {
        return contentBody;
    }

    public String getContentSubject() {
        return contentSubject;
    }

    public Integer getContentId() {
        return contentId;
    }

}
