package us.narin.dimigoin.util;

/**
 * Created by Seungwoo on 2016. 1. 5..
 */
public enum TransactionFrag {

    HOME(0),
    INFO(1),
    COMMUNITY(2),
    SUBMIT(3),
    SERVICE(4),
    SURVEY(5),
    SETTING(6);

    private final Integer flag;
    private TransactionFrag(Integer flag){
        this.flag = flag;
    }

    public Integer toInt(){
        return flag;
    }

}
