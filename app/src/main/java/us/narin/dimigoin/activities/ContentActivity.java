package us.narin.dimigoin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import us.narin.dimigoin.R;
import us.narin.dimigoin.api.ApiObject;
import us.narin.dimigoin.api.ApiRequests;
import us.narin.dimigoin.model.ArticleModel;
import us.narin.dimigoin.model.ContentDetailModel;
import us.narin.dimigoin.model.ContentModel;
import us.narin.dimigoin.util.SessionManager;

public class ContentActivity extends AppCompatActivity {

    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        mToolbar = (Toolbar) findViewById(R.id.content_detail_toolbar);
        setSupportActionBar(mToolbar);
        Intent intent = getIntent();
        Integer contentId = intent.getIntExtra("content_id", 1);
        String contentBoard = intent.getStringExtra("content_board");
        String contentSubject = intent.getStringExtra("content_subject");
        String contentBody = intent.getStringExtra("content_body");
        String contentAuthor = intent.getStringExtra("content_author");
        Glide.with(getApplicationContext()).load(R.drawable.nav_bg).into((ImageView) findViewById(R.id.content_bg));
        final WebView contentView = (WebView) findViewById(R.id.content_main);
        getSupportActionBar().setTitle(contentSubject);
        getSupportActionBar().setSubtitle(contentAuthor);
        contentView.loadData(contentBody, "text/html; charset=UTF-8", null);

    }

}
