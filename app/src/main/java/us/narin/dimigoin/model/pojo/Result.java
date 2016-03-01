package us.narin.dimigoin.model.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Seungwoo on 2016. 2. 29..
 */
public class Result {
    @SerializedName("result")
    private Integer resultCode;
    @SerializedName("msg")
    private String resultMsg;

    public Integer getResult() {
        return resultCode;
    }

    public String getMsg() {
        return resultMsg;
    }
}
