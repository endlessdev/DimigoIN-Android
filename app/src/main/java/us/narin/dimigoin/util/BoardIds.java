package us.narin.dimigoin.util;

/**
 * Created by Seungwoo on 2016. 1. 5..
 */
public enum BoardIds {

    FILE("fileboard"),
    FREE("freeboard"),
    KNOWLEDGE("knowledge"),
    LOSTANDFOUND("laf"),
    SUGGEST("suggest");

    private final String boardId;
    private BoardIds(String boardId){
        this.boardId = boardId;
    }

    @Override
    public String toString(){
        return boardId;
    }

}
