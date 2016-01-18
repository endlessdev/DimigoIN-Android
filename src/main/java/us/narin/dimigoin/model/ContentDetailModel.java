package us.narin.dimigoin.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seungwoo on 2016. 1. 3..
 */
public class ContentDetailModel {

    @SerializedName("result")
    Integer resultCode;
    @SerializedName("msg")
    String resultMsg;
    @SerializedName("data")
    Data resultData;

    public Data getResultData() {
        return resultData;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public class Data {
        @SerializedName("article")
        ArticleModel articleModel;
        @SerializedName("files")
        List<FileModel> fileModels = new ArrayList<>();
        @SerializedName("comment")
        List<CommentModel> commentModels = new ArrayList<>();

        public ArticleModel getArticleModel() {
            return articleModel;
        }

        public List<CommentModel> getCommentModels() {
            return commentModels;
        }

        public List<FileModel> getFileModels() {
            return fileModels;
        }
    }

}
