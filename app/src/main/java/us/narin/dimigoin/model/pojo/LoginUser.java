package us.narin.dimigoin.model.pojo;

/**
 * Created by Seungwoo on 2016. 3. 15..
 */
public class LoginUser {

    private String userId;
    private String userPw;

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String userToken;

    public String getUserId() {
        return userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public String getUserToken() {
        return userToken;
    }
}
