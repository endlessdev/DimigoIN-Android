package us.narin.dimigoin.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.dd.processbutton.ProcessButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import us.narin.dimigoin.R;
import us.narin.dimigoin.api.ApiObject;
import us.narin.dimigoin.api.ApiRequests;
import us.narin.dimigoin.model.pojo.Login;
import us.narin.dimigoin.services.gcm.RegistrationService;
import us.narin.dimigoin.util.Schema;
import us.narin.dimigoin.util.Session;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.email)
    AutoCompleteTextView accountField;
    @Bind(R.id.password)
    EditText passwordField;
    @Bind(R.id.sign_in_button)
    ProcessButton signInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        signInBtn.setOnClickListener(view -> {
            signInBtn.setProgress(50);
            final String userId = accountField.getText().toString();
            final String userPw = passwordField.getText().toString();

            setEnabled(false);

            ApiRequests apiRequests = ApiObject.initClient();
            Call<Login> callLogin = apiRequests.apiLogin(userId, userPw);
            if (isConnectionAvailable()) {
                callLogin.enqueue(new Callback<Login>() {

                    @Override
                    public void onResponse(Response<Login> response) {
                        final Login login = response.body();
                        if (response.code() == 200) {

                            new Thread(() -> {
                                try {
//                                디미고인 웹버전으로 로그인하여 쿠키 탈취
                                    Connection.Response loginResponse = Jsoup.connect(Schema.LOGIN_WEB_ENDPOINT)
                                            .data(Schema.LOGIN_WEB_PARAM_ID, userId)
                                            .data(Schema.LOGIN_WEB_PARAM_PW, userPw)
                                            .method(Connection.Method.POST)
                                            .execute();

//                                API가 미반영된 기능에 대한 지원을 위해 쿠키를 저장합니다
//                                쿠키는 로그인 성공마다 갱신되어 반영됩니다.
                                    final String userCookie = loginResponse.cookie(Schema.LOGIN_COOKIE_KEY);
                                    Session.saveAccount(getApplicationContext(), userId, userPw, login.getData().getToken(), userCookie);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }).start();

                            signInBtn.setProgress(100);

                            getInstanceIdToken();

                            Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                            mainIntent.putExtra("stdModel", login);
                            startActivity(mainIntent);

                        } else {
                            signInBtn.setErrorText(getString(R.string.login_failed_msg));
                            signInBtn.setProgress(-1);
                            setEnabled(true);
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        signInBtn.setProgress(-1);
                        setEnabled(true);
                    }
                });

            }else {
                signInBtn.setErrorText(getString(R.string.login_failed_network_msg));
                setEnabled(true);
            }
        });

    }

    private void setEnabled(boolean isEnabled) {
            accountField.setEnabled(isEnabled);
            passwordField.setEnabled(isEnabled);
            signInBtn.setEnabled(isEnabled);
    }

    private void getInstanceIdToken() {
        if (isPlayServicesAvailable()) {
            // Start IntentService to register this application with GCM.
            Intent intent = new Intent(this, RegistrationService.class);
            startService(intent);
        }
    }

    //    GCM이 구동되기위해 필요한 구글 플레이서비스의 유무를 검사합니다.
    private boolean isPlayServicesAvailable() {
        final GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = googleApiAvailability.isGooglePlayServicesAvailable(this);
        return resultCode != ConnectionResult.SUCCESS && !googleApiAvailability.isUserResolvableError(resultCode);
    }

    //    로그인 시도중 네트워크 연결을 확인합니다.
    private boolean isConnectionAvailable() {
        final ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            switch (networkInfo.getType()) {
                case ConnectivityManager.TYPE_WIFI:
                    return true;
                case ConnectivityManager.TYPE_MOBILE:
                    return true;
            }
        } else {
            return false;
        }
        return false;
    }

}
