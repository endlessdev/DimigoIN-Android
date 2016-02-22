package us.narin.dimigoin.api;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import us.narin.dimigoin.model.ContentModel;
import us.narin.dimigoin.util.Schema;

public class ApiObject {

    private static ApiRequests apiRequests;

    public static ApiRequests initClient() {

        if (apiRequests == null) {
            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response response = chain.proceed(chain.request());
                    return response;
                }
            });

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Schema.API_ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();


            apiRequests = retrofit.create(ApiRequests.class);
        }
        return apiRequests;
    }
}
