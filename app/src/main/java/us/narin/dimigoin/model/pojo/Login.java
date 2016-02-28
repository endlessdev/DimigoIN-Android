package us.narin.dimigoin.model.pojo;

import java.io.Serializable;

/**
 * Created by Seungwoo on 2015. 12. 26..
 */
public class Login implements Serializable{

    Integer result;
    String msg;
    LoginData data;

    public Login(Integer result, String msg, LoginData data) {
        this.result = result;
        this.msg = msg;
        this.data = data;
    }

    public LoginData getData() {
        return data;
    }

    public Integer getResult() {
        return result;
    }

    public String getMsg() {
        return msg;
    }

}