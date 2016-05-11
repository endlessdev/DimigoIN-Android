package us.narin.dimigoin.api;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class ApiObject {
    private static String baseURL;

    private static ApiRequests apiRequests;

    public static ApiRequests initClient(String baseUrl) {

        if (apiRequests == null || !baseURL.equals(baseUrl)) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiRequests = retrofit.create(ApiRequests.class);

            baseURL = baseUrl;
        }
        return apiRequests;
    }
}
