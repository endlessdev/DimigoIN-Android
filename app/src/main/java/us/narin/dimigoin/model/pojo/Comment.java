package us.narin.dimigoin.model.pojo;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Comment implements Serializable{

    @SerializedName("wr_content")
    String commentContent;
    @SerializedName("wr_datetime")
    String postTime;
    @SerializedName("wr_reply")
//    List<Comment> replyComment = new ArrayList<>();
    String commentReply;
    @SerializedName("wr_id")
    Integer contentId;
    @SerializedName("wr_parent")
    Integer parentPost;
    @SerializedName("wr_name")
    String commentAuthor;


    public String getCommentAuthor() {
        return commentAuthor;
    }

    public String getCommentReply() {
        return commentReply;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Integer getContentId() {
        return contentId;
    }

    public Integer getParentPost() {
        return parentPost;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }



    public void setParentPost(Integer parentPost) {
        this.parentPost = parentPost;
    }

    public String getCommentContent() {
        return commentContent;
    }

}
