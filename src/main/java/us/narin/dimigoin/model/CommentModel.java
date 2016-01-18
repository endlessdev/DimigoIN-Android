package us.narin.dimigoin.model;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CommentModel {

    @SerializedName("wr_content")
    String commentContent;
    @SerializedName("wr_datetime")
    String postTime;
    @SerializedName("wr_reply")
    List<CommentModel> replyComment = new ArrayList<>();
    @SerializedName("wr_id")
    Integer contentId;
    @SerializedName("wr_parent")
    Integer parentPost;


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

    public List<CommentModel> getReplyComment() {
        return replyComment;
    }

    public void setParentPost(Integer parentPost) {
        this.parentPost = parentPost;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setReplyComment(List<CommentModel> replyComment) {
        this.replyComment = replyComment;
    }

}
