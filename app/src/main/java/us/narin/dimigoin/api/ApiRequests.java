package us.narin.dimigoin.api;


import retrofit.Call;
import retrofit.http.*;
import us.narin.dimigoin.model.pojo.*;

import java.util.List;

public interface ApiRequests {

    @POST("/login")
    @FormUrlEncoded
    Call<Login> apiLogin(
            @Field("id") String userId,
            @Field("pw") String userPW
    );

    @GET("/board/article/detail/{board}/{contentId}/{token}")
    Call<ContentDetail> getContentDetail(
            @Path("board") String boardId,
            @Path("contentId") Integer contentId,
            @Path("token") String userToken
    );

    @GET("/board/article/list/{board}/{page}/{token}")
    Call<BoardList> getBoardList(
            @Path("board") String boardId,
            @Path("page") Integer pageId,
            @Path("token") String userToken
    );

    @GET("/board/article/download/{bf_file}/{token}")
    Call<List<File>> downloadFiles(
            @Path("bf_file") Integer fileType,
            @Path("token") String userToken
    );

    @POST("/push/register")
    Call<Result> getRegisterResult(
            @Field("id") String userId,
            @Field("token") String userToken,
            @Field("phoneType") Integer phoneType,
            @Field("deviceId") String deviceId
    );

    //TODO ENDPOINT CHANGE TO ingang.dimigo.in
    @POST("/request_do.php")
    Call<Result> requestIngang(
            @Field("seat") Integer ingangSeat,
            @Field("when") Integer submitTime,
            @Field("code") Integer capchaCode
    );

    //TODO ENDPOINT CHANGE TO counsel.dimigo.in
    @POST("/request_do.php")
    Call<Result> requestCounsel(
            @Field("schedule") String submitSchedule,
            @Field("ctegory") String submitCategory
    );

    @GET("/pages/dimibob_getdata.php")
    Call<Meal> requestMeal();



}
