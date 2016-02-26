package us.narin.dimigoin.model;

/**
 * Created by Seungwoo on 2015. 12. 24..
 */
public class Song {

    String songUrl;
    String songSubject;
    String songSubmitter;
    String songDate;
    Integer songLikeCount;
    String songLikeUrl;

    public String getSongUrl() {
        return songUrl;
    }

    public String getSongSubject() {
        return songSubject;
    }

    public String getSongSubmitter() {
        return songSubmitter;
    }

    public String getSongDate() {
        return songDate;
    }

    public Integer getSongLikeCount() {
        return songLikeCount;
    }

    public String getSongLikeUrl() {
        return songLikeUrl;
    }
}
