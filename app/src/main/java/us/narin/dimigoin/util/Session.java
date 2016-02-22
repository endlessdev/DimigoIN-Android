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
        pref = mContext.getSharedPreferences("us.narin.dimigoin.app", Context.MODE_PRIVATE);
        editor  = pref.edit();
        editor.putString("userId", userId).apply();
        editor.putString("userPw", userPw).apply();
        editor.putString("userToken", userToken).apply();
        editor.putString("userCookie", userCookie).apply();

    }

    public static String getAccountId(Context mContext){
        return mContext.getSharedPreferences("us.narin.dimigoin.app", Context.MODE_PRIVATE).getString("userId", "");
    }

    public static String getAccountPW(Context mContext){
        return mContext.getSharedPreferences("us.narin.dimigoin.app", Context.MODE_PRIVATE).getString("userPw", "");
    }

    public static String getUserToken(Context mContext){
        return mContext.getSharedPreferences("us.narin.dimigoin.app", Context.MODE_PRIVATE).getString("userToken", "");
    }

    public static String getUserCookie(Context mContext){
        return mContext.getSharedPreferences("us.narin.dimigoin.app", Context.MODE_PRIVATE).getString("userCookie", "");
    }

}
