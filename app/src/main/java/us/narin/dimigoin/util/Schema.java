package us.narin.dimigoin.util;

/**
 * Created by Seungwoo on 2015. 12. 26..
 */
public class Schema {

    public static final String API_ENDPOINT = "http://app.allabout.kr";
    public static final String FILE_DOWNLOAD = API_ENDPOINT+"/board/article/download/";

    public static final String LOGIN_WEB_ENDPOINT = "https://account.allabout.kr/?module=login&act=prologin";
    public static final String LOGIN_WEB_PARAM_ID = "mb_id";
    public static final String LOGIN_WEB_PARAM_PW = "mb_password";
    public static final String LOGIN_COOKIE_KEY = "PHPSESSID";

    public static final String WEBVIEW_DEFAULT_STYLE = "<body style=\"margin: 0; padding: 0; color:#626262;\"><style>img{display: inline; height: auto; max-width: 100%;}</style><style>iframe{display: inline; height: auto; max-width: 100%;}</style>";
    public static final String WEBVIEW_DEFAULT_TYPE="text/html; charset=UTF-8";
}
