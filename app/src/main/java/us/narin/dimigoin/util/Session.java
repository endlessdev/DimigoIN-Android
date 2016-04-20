package us.narin.dimigoin.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Seungwoo on 2015. 12. 27..
 */
public class Session {
    static SharedPreferences pref;
    static SharedPreferences.Editor editor;

    public static void saveAccount(Context mContext, String userId, String userPw, String userToken, String userCookie) {
        pref = mContext.getSharedPreferences(Schema.PACKAGE_TAG, Context.MODE_PRIVATE);
        editor = pref.edit();
        editor.putString("userId", userId);
        editor.putString("userPw", userPw);
        editor.putString("userToken", userToken);
        editor.putString("userCookie", userCookie);
        editor.apply();
    }

    public static String getAccountId(Context mContext) {
        return mContext.getSharedPreferences(Schema.PACKAGE_TAG, Context.MODE_PRIVATE).getString("userId", "");
    }

/*
    ## Warning! this method is unsafe. please using encrypt. ##
    public static String getAccountPW(Context mContext){
        return mContext.getSharedPreferences(Schema.PACKAGE_TAG, Context.MODE_PRIVATE).getString("userPw", "");
    }
*/

    public static String getUserToken(Context mContext) {
        return mContext.getSharedPreferences(Schema.PACKAGE_TAG, Context.MODE_PRIVATE).getString("userToken", "");
    }

    public static String getUserCookie(Context mContext) {
        return mContext.getSharedPreferences(Schema.PACKAGE_TAG, Context.MODE_PRIVATE).getString("userCookie", "");
    }

}
