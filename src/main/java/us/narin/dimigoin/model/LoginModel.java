package us.narin.dimigoin.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Seungwoo on 2015. 12. 26..
 */
public class LoginModel implements Serializable{

    Integer result;
    String msg;
    LoginDataModel data;

    public LoginModel(Integer result, String msg, LoginDataModel data) {
        this.result = result;
        this.msg = msg;
        this.data = data;
    }

    public LoginDataModel getData() {
        return data;
    }

    public Integer getResult() {
        return result;
    }

    public String getMsg() {
        return msg;
    }

}