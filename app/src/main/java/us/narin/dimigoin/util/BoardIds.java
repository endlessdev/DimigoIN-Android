package us.narin.dimigoin.util;

/**
 * Created by Seungwoo on 2016. 1. 5..
 */
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
    private BoardIds(String boardId){
        this.boardId = boardId;
    }

    public String toString(){
        return boardId;
    }

}
