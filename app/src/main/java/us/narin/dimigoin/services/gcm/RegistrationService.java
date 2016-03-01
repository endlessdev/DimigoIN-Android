package us.narin.dimigoin.services.gcm;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import us.narin.dimigoin.api.ApiObject;
import us.narin.dimigoin.api.ApiRequests;
import us.narin.dimigoin.model.pojo.Result;
import us.narin.dimigoin.util.QuickstartPreferences;
import us.narin.dimigoin.util.Schema;
import us.narin.dimigoin.util.Session;

import java.io.IOException;

public class RegistrationService extends IntentService {

    private static final String TAG = "RegistrationService";

    public RegistrationService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        // GCM Instance ID의 토큰을 가져오는 작업이 시작되면 LocalBoardcast로 GENERATING 액션을 알려 ProgressBar가 동작하도록 한다.
        LocalBroadcastManager.getInstance(this)
                .sendBroadcast(new Intent(QuickstartPreferences.REGISTRATION_GENERATING));

        // GCM을 위한 Instance ID를 가져온다.
        InstanceID instanceID = InstanceID.getInstance(this);
        String token = null;
        try {
            synchronized (TAG) {
                // GCM 앱을 등록하고 획득한 설정파일인 google-services.json을 기반으로 SenderID를 자동으로 가져온다.
                String default_senderId = "732997330595";
                // GCM 기본 scope는 "GCM"이다.
                String scope = GoogleCloudMessaging.INSTANCE_ID_SCOPE;
                // Instance ID에 해당하는 토큰을 생성하여 가져온다.
                token = instanceID.getToken(default_senderId, scope, null);

                Log.i(TAG, "GCM Registration Token: " + token);

                final String finalToken = token;

                ApiRequests apiRequests = ApiObject.initClient();
                Call<Result> getResult = apiRequests.getResult(
                        Session.getAccountId(this),
                        Session.getUserToken(this),
                        Schema.GCM_REGISTRATION_PHONETYPE,
                        finalToken
                );

                getResult.enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Response<Result> response) {
                        Log.i(TAG, finalToken);
                        Log.i(TAG, Session.getUserToken(RegistrationService.this));
                        Log.i(TAG, Session.getAccountId(RegistrationService.this));
                        Log.i(TAG, response.body().getMsg());
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.i(TAG, "GCM 등록에 실패했습니다.");
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // GCM Instance ID에 해당하는 토큰을 획득하면 LocalBoardcast에 COMPLETE 액션을 알린다.
        // 이때 토큰을 함께 넘겨주어서 UI에 토큰 정보를 활용할 수 있도록 했다.
        Intent registrationComplete = new Intent(QuickstartPreferences.REGISTRATION_COMPLETE);
        registrationComplete.putExtra("token", token);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }
}