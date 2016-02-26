package us.narin.dimigoin.api;

import com.squareup.okhttp.OkHttpClient;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import us.narin.dimigoin.util.Schema;

public class ApiObject {

    private static ApiRequests apiRequests;

    public static ApiRequests initClient() {

        if (apiRequests == null) {
            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.interceptors().add(chain -> chain.proceed(chain.request()));


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
