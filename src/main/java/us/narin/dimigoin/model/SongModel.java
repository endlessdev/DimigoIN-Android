package us.narin.dimigoin.model;

/**
 * Created by Seungwoo on 2015. 12. 24..
 */
public class SongModel {

    String songUrl;
    String songSubject;
    String songSubmitter;
    String songDate;
    Integer songLikeCount;
    String songLikeUrl;

    public SongModel(String songUrl, String songSubject, String songSubmitter, String songDate, Integer songLikeCount, String songLikeUrl) {
        this.songUrl = songUrl;
        this.songSubject = songSubject;
        this.songSubmitter = songSubmitter;
        this.songDate = songDate;
        this.songLikeCount = songLikeCount;
        this.songLikeUrl = songLikeUrl;
    }

    public Integer getSongLikeCount() {
        return songLikeCount;
    }

    public String getSongDate() {
        return songDate;
    }

    public String getSongLikeUrl() {
        return songLikeUrl;
    }

    public String getSongSubject() {
        return songSubject;
    }

    public String getSongSubmitter() {
        return songSubmitter;
    }

    public String getSongUrl() {
        return songUrl;
    }
}
