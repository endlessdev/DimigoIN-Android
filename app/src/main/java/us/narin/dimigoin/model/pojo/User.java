package us.narin.dimigoin.model.pojo;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("result")
    boolean result;
    @SerializedName("mb_level")
    Integer userLevel;
    @SerializedName("grade")
    Integer userGrade;
    @SerializedName("class")
    Integer userClass;
    @SerializedName("name")
    String userName;
    @SerializedName("no")
    Integer userNumber;

    public Integer getUserClass() {
        return userClass;
    }

    public Integer getUserGrade() {
        return userGrade;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public Integer getUserNumber() {
        return userNumber;
    }

    public String getUserName() {
        return userName;
    }

}
