package us.narin.dimigoin.model;

/**
 * Created by Seungwoo on 2015. 12. 27..
 */

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class LoginData implements Serializable{
//
//    "number": 21,
//            "expire_time": "2016-01-04",
//            "grade": 1,
//            "class": 3,
//            "token": "dae442a062075104d9af",
//            "name": "염승우"

    String token;
    Integer grade;

    @SerializedName("class")
    Integer stdClass;

    Integer number;
    String name;
    String expire_time;

    public LoginData(String token, Integer grade, Integer number, String name, String expire_time){
        this.token = token;
        this.grade = grade;
        this.number = number;
        this.name = name;
        this.expire_time = expire_time;
    }

    public Integer getGrade() {
        return grade;
    }

    public Integer getNumber() {
        return number;
    }

    public String getExpire_time() {
        return expire_time;
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
