package us.narin.dimigoin.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seungwoo on 2016. 1. 3..
 */
public class ContentDetail {

    @SerializedName("result")
    Integer resultCode;
    @SerializedName("msg")
    String resultMsg;
    @SerializedName("data")
    List<Data> resultData = new ArrayList<>();

    public List<Data> getResultData() {
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
        Article article;
        @SerializedName("files")
        List<File> files = new ArrayList<>();
        @SerializedName("comment")
        List<Comment> comments = new ArrayList<>();

        public Article getArticle() {
            return article;
        }

        public List<Comment> getComments() {
            return comments;
        }

        public List<File> getFiles() {
            return files;
        }
    }


}
