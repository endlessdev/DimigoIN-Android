package us.narin.dimigoin.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Seungwoo on 2016. 1. 4..
 */
public class ArticleModel {

//    "article": {
//        "wr_hit": "1",
//                "unix_timestamp": 1451615884,
//                "wr_name": "염승우",
//                "wr_last": "2016-01-09 11:38:04",
//                "wr_content": "세션 앱 테스트",
//                "wr_good": "0",
//                "wr_subject": "ClientBot TEST",
//                "wr_reply": "",
//                "wr_id": "269"
//    },
    @SerializedName("wr_hit")
    Integer viewCount;
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
