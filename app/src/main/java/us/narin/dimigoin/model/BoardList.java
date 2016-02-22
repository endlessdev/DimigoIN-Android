package us.narin.dimigoin.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seungwoo on 2016. 1. 4..
 */
public class BoardList {

    @SerializedName("result")
    Integer resultCode;
    @SerializedName("msg")
    String resultMsg;
    @SerializedName("data")
    Data data;

    public String getResultMsg() {
        return resultMsg;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public Data getData() {
        return data;
    }

    public class Data {
        @SerializedName("page")
        Integer pageCount;
        @SerializedName("list")
        List<Content> contentList = new ArrayList<>();

        public Integer getPageCount() {
            return pageCount;
        }

        public List<Content> getContentList() {
            return contentList;
        }

    }

}
