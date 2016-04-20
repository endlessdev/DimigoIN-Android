package us.narin.dimigoin.api;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class ApiObject {

    private static ApiRequests apiRequests;

    public static ApiRequests initClient(String baseUrl) {

        if (apiRequests == null) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiRequests = retrofit.create(ApiRequests.class);
        }
        return apiRequests;
    }
}
