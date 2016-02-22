package us.narin.dimigoin.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dd.processbutton.ProcessButton;
import com.dd.processbutton.iml.ActionProcessButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.gson.Gson;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import us.narin.dimigoin.R;
import us.narin.dimigoin.api.ApiObject;
import us.narin.dimigoin.api.ApiRequests;
import us.narin.dimigoin.model.LoginDataModel;
import us.narin.dimigoin.model.LoginModel;
import us.narin.dimigoin.services.RegistrationIntentService;
import us.narin.dimigoin.util.QuickstartPreferences;
import us.narin.dimigoin.util.Schema;
import us.narin.dimigoin.util.SessionManager;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = "MainActivity";
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    public ProcessButton signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registBroadcastReceiver();

        Glide.with(getApplicationContext()).load(R.drawable.login_bg).diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().bitmapTransform(new GrayscaleTransformation(getApplicationContext())).into((ImageView) findViewById(R.id.login_main_bg));

        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    return true;
                }
                return false;
            }
        });

        signInButton = (ActionProcessButton) findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                signInButton.setProgress(50);
                final String userId = mEmailView.getText().toString();
                final String userPw = mPasswordView.getText().toString();

                mEmailView.setEnabled(false);
                mPasswordView.setEnabled(false);
                signInButton.setEnabled(false);

                ApiRequests apiRequests = ApiObject.initClient();
                Call<LoginModel> callLogin = apiRequests.apiLogin(userId, userPw);
                callLogin.enqueue(new Callback<LoginModel>() {

                    @Override
                    public void onResponse(Response<LoginModel> response) {
                        final LoginModel loginModel = response.body();
                        if (response.code() == 200) {

                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Connection.Response loginResponse = Jsoup.connect(Schema.LOGIN_WEB_ENDPOINT)
                                                .data(Schema.LOGIN_WEB_PARAM_ID, userId)
                                                .data(Schema.LOGIN_WEB_PARAM_PW, userPw)
                                                .method(Connection.Method.POST)
                                                .execute();

                                        final String userCookie = loginResponse.cookie(Schema.LOGIN_COOKIE_KEY);
                                        SessionManager.saveAccount(getApplicationContext(), userId, userPw, loginModel.getData().getToken(), userCookie);

//                                        Jsoup.connect("http://jeje.pe.kr/bbs/write_update.php")
//                                                .cookie(Schema.LOGIN_COOKIE_KEY, SessionManager.getUserCookie(getApplicationContext()))
//                                                .data("bo_table", "freeboard")
//                                                .data("wr_subject", "ClientBot TEST")
//                                                .data("wr_content", "세션 앱 테스트")
//                                                .post();


                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();

                            signInButton.setProgress(100);

                            getInstanceIdToken();

                            Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                            mainIntent.putExtra("stdModel", loginModel);
                            startActivity(mainIntent);
                        } else {
                            signInButton.setErrorText("로그인에 실패했습니다.");
                            signInButton.setProgress(-1);
                            mEmailView.setEnabled(true);
                            mPasswordView.setEnabled(true);
                            signInButton.setEnabled(true);
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        signInButton.setProgress(-1);
                    }
                });
            }
        });

        mLoginFormView = findViewById(R.id.login_form);

    }

    public void getInstanceIdToken() {
        if (checkPlayServices()) {
            // Start IntentService to register this application with GCM.
            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
        }
    }

    public void registBroadcastReceiver() {
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();

                if (action.equals(QuickstartPreferences.REGISTRATION_READY)) {
                    // 액션이 READY일 경우
                } else if (action.equals(QuickstartPreferences.REGISTRATION_GENERATING)) {
                    // 액션이 GENERATING일 경우
                } else if (action.equals(QuickstartPreferences.REGISTRATION_COMPLETE)) {
                    // 액션이 COMPLETE일 경우
                    String token = intent.getStringExtra("token");
                }

            }
        };
    }

    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }

}
