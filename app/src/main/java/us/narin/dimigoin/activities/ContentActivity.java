package us.narin.dimigoin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import us.narin.dimigoin.R;

public class ContentActivity extends AppCompatActivity {

    WebView contentView;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        Intent intent = getIntent();

        initUI();
        displayContent(intent);
    }

    public void initUI(){
        mToolbar = (Toolbar) findViewById(R.id.content_detail_toolbar);
        Glide.with(getApplicationContext()).load(R.drawable.nav_bg).into((ImageView) findViewById(R.id.content_bg));
        setSupportActionBar(mToolbar);

        contentView = (WebView) findViewById(R.id.content_main);
    }

    public void displayContent(Intent intent){

        Integer contentId = intent.getIntExtra("content_id", 1);
        String contentBoard = intent.getStringExtra("content_board");
        String contentSubject = intent.getStringExtra("content_subject");
        String contentBody = intent.getStringExtra("content_body");
        String contentAuthor = intent.getStringExtra("content_author");

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(contentSubject);
            getSupportActionBar().setSubtitle(contentAuthor);

        }
        contentView.setWebChromeClient(new WebChromeClient());
        contentView.loadData(contentBody, "text/html; charset=UTF-8", null);
    }

}
