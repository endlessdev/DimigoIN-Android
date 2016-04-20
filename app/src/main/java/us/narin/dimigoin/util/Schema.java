package us.narin.dimigoin.util;

/**
 * Created by Seungwoo on 2015. 12. 26..
 */
public class Schema {

    public static final String HOST = "allabout.kr";
    public static final String ENDPOINT = "http://" + HOST;
    public static final String API_ENDPOINT = "http://app." + HOST;
    public static final String FILE_DOWNLOAD = API_ENDPOINT + "/board/article/download/";

    public static final String LOGIN_WEB_ENDPOINT = "https://account."+HOST+"/?module=login&act=prologin";
    public static final String LOGIN_WEB_PARAM_ID = "mb_id";
    public static final String LOGIN_WEB_PARAM_PW = "mb_password";
    public static final String LOGIN_COOKIE_KEY = "PHPSESSID";

    public static final String WEBVIEW_DEFAULT_STYLE = "<body style=\"margin: 0; padding: 0; color:#626262;\"><style>img{display: inline; width: 100%; height: auto; max-width: 100%;}</style><style>iframe{display: inline; height: auto; max-width: 100%;}</style>";
    public static final String WEBVIEW_DEFAULT_TYPE = "text/html; charset=UTF-8";
//  public static final String REGISTRATION_READY = "registrationReady";
    public static final String REGISTRATION_GENERATING = "registrationGenerating";
    public static final String REGISTRATION_COMPLETE = "registrationComplete";

    public static final String PACKAGE_TAG = "us.narin.dimigoin";

    public static final Integer GCM_REGISTRATION_PHONETYPE = 1;


    /*
        ## WARNING! ## THIS IS TEMP SUBMIT PAGE! DONT USE MAIN WAY..
     */
    public static final String S_PROTOCAL = "http://";
    public static final String S_URL = ".allabout.kr/";

    public enum SubmitURL {
        COUNSEL("counsel", "request_wee.php"),
        INGANG("ingang", "request.php"),
        MENTO("mento", "request.php"),
        SNACK("snack", "request.php"),
        MORNINGSONG("song", "morning_song.php"),
        STAY("stay", "?module=request"),
        DOR("dor", "laundry/request.php"),
        SUGANG("sugang", "requestList.php");


        private final String submitUrl;

        SubmitURL(String subUrl, String down) {
            this.submitUrl = S_PROTOCAL + subUrl + S_URL + down;
        }

        public String toString() {
            return submitUrl;
        }
    }

    public enum BoardIds {

        FILE("fileboard"),
        FREE("freeboard"),
        KNOWLEDGE("knowledge"),
        LOSTANDFOUND("laf"),
        SUGGEST("suggest"),
        NOTICE("notice"),
        CONTEST_INFO("contentinfo"),
        KIN("kin"),
        ENJOY("enjoy"),
        PLAYGROUND("playground"),
        MARKET("market"),
        NOTICE_MOBILE("notice_mobile");

        private final String boardId;

        BoardIds(String boardId) {
            this.boardId = boardId;
        }

        public String toString() {
            return boardId;
        }

    }

    public enum TransactionFrag {

        HOME(0),
        INFO(1),
        COMMUNITY(2),
        SUBMIT(3),
        SERVICE(4),
        SURVEY(5),
        SETTING(6);

        private final Integer flag;

        private TransactionFrag(Integer flag) {
            this.flag = flag;
        }

        public Integer toInt() {
            return flag;
        }

    }
}
