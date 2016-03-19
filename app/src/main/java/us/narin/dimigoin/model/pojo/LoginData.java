package us.narin.dimigoin.model.pojo;

/**
 * Created by Seungwoo on 2015. 12. 27..
 */

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


//Intent로 LoginData의 객체 전달을 위해 Serializable 구현

public class LoginData implements Serializable{

    String token;
    Integer grade;

    @SerializedName("class")
    Integer stdClass;

    Integer number;
    String name;
    String expireTime;

    public LoginData(String token, Integer grade, Integer number, String name, String expire_time){
        this.token = token;
        this.grade = grade;
        this.number = number;
        this.name = name;
        this.expireTime = expire_time;
    }

    public Integer getGrade() {
        return grade;
    }

    public Integer getNumber() {
        return number;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public String getName() {
        return name;
    }

    public Integer getStdClass() {
        return stdClass;
    }

    public String getToken() {
        return token;
    }
}
