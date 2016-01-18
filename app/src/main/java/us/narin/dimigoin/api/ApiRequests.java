package us.narin.dimigoin.api;


import java.util.List;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import us.narin.dimigoin.model.BoardListModel;
import us.narin.dimigoin.model.ContentDetailModel;
import us.narin.dimigoin.model.FileModel;
import us.narin.dimigoin.model.LoginModel;

public interface ApiRequests {

    @POST("/login")
    @FormUrlEncoded
    Call<LoginModel> apiLogin(
            @Field("id") String userId,
            @Field("pw") String userPW
    );

    @GET("/board/article/detail/{board}/{contentId}/{token}")
    Call<ContentDetailModel> getContentDetail(
            @Path("board") String boardId,
            @Path("contentId") Integer contentId,
            @Path("token") String userToken
    );

    @GET("/board/article/list/{board}/{page}/{token}")
    Call<BoardListModel> getBoardList(
            @Path("board") String boardId,
            @Path("page") Integer pageId,
            @Path("token") String userToken
    );

    @GET("/board/article/download/{bf_file}/{token}")
    Call<List<FileModel>> downloadFiles(
            @Path("bf_file") Integer fileType,
            @Path("token") String userToken
    );

}
