package us.narin.dimigoin.api;


import java.util.List;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import us.narin.dimigoin.model.BoardList;
import us.narin.dimigoin.model.ContentDetail;
import us.narin.dimigoin.model.File;
import us.narin.dimigoin.model.Login;

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

}
