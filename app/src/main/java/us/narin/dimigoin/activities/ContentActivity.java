package us.narin.dimigoin.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import us.narin.dimigoin.R;
import us.narin.dimigoin.adapter.BoardCommentAdapter;
import us.narin.dimigoin.api.ApiObject;
import us.narin.dimigoin.api.ApiRequests;
import us.narin.dimigoin.model.pojo.ContentDetail;
import us.narin.dimigoin.model.pojo.File;
import us.narin.dimigoin.util.Schema;
import us.narin.dimigoin.util.Session;
import us.narin.dimigoin.util.TimeStamp;
import us.narin.dimigoin.util.NestedInRecyclerManager;

import java.util.List;

public class ContentActivity extends AppCompatActivity {

    @Bind(R.id.content_detail_toolbar)
    Toolbar mToolbar;
    @Bind(R.id.bbs_content_detail_content)
    WebView contentView;
    @Bind(R.id.bbs_content_detail_author)
    TextView contentAuthor;
    @Bind(R.id.bbs_content_detail_time)
    TextView contentTime;
    @Bind(R.id.bbs_content_detail_profile)
    Button contentProfile;
    @Bind(R.id.bbs_content_detail_file_1)
    TextView firstFile;
    @Bind(R.id.bbs_content_detail_file_2)
    TextView secondFile;
    @Bind(R.id.bbs_content_detail_file_1_wrapper)
    CardView firstFileWrapper;
    @Bind(R.id.bbs_content_detail_file_2_wrapper)
    CardView secondFileWrapper;
    @Bind(R.id.bbs_content_detail_like_count)
    TextView likeCount;
    @Bind(R.id.bbs_content_detail_view_count)
    TextView viewCount;
    @Bind(R.id.bbs_content_detail_comment_count)
    TextView commentCount;
    @Bind(R.id.bbs_content_detail_comment_rv)
    RecyclerView commentView;
    @Bind(R.id.bbs_content_detail_comment_wrappper)
    CardView commentWrapper;

    @BindString(R.string.bbs_comment)
    String bbsComment;
    @BindString(R.string.bbs_like)
    String bbsLike;
    @BindString(R.string.bbs_view)
    String bbsView;
    @BindString(R.string.bbs_unit)
    String bbsUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        Intent mIntent = getIntent();

        final Integer contentId = mIntent.getIntExtra("content_id", 0);
        final String contentSubject = mIntent.getStringExtra("content_subject");
        final String boardId = mIntent.getStringExtra("content_board");
        final String userToken = Session.getUserToken(getApplicationContext());

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(contentSubject);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Glide.with(getApplicationContext()).load(R.drawable.nav_bg).into((ImageView) findViewById(R.id.content_bg));

        final WebSettings webSettings  = contentView.getSettings();
//        webSettings.setJavaScriptEnabled(true);

        contentView.setBackgroundColor(0);
        contentView.setLongClickable(false);
        webSettings.setDefaultFontSize(14);

        ApiRequests apiRequests = ApiObject.initClient();
        Call<ContentDetail> callDetail = apiRequests.getContentDetail(boardId, contentId, userToken);

        callDetail.enqueue(new Callback<ContentDetail>() {

            @Override
            public void onResponse(Response<ContentDetail> response) {
                final ContentDetail.Data tmpModel = response.body().getResultData().get(0);
                final List<File> fileList = response.body().getResultData().get(0).getFiles();
                contentView.loadData(Schema.WEBVIEW_DEFAULT_STYLE + tmpModel.getArticle().getContentBody(), Schema.WEBVIEW_DEFAULT_TYPE, null);



                contentAuthor.setText(tmpModel.getArticle().getAuthorName());
                contentTime.setText(new TimeStamp(getApplicationContext()).getTimes(tmpModel.getArticle().getPostTime()));
                contentProfile.setText(String.valueOf(tmpModel.getArticle().getAuthorName().charAt(0)));

                likeCount.setText(bbsLike+ tmpModel.getArticle().getGoodCount() + bbsUnit);
                commentCount.setText(bbsComment+ tmpModel.getComments().size() + bbsUnit);
                viewCount.setText(bbsView+ tmpModel.getArticle().getViewCount() + bbsUnit);
                if (!tmpModel.getFiles().isEmpty()) {

                    if (fileList.size()<=1) {
                        firstFile.setText(fileList.get(0).getFileName() + "[" + fileList.get(0).getDownloadCount() + "]");
                        firstFileWrapper.setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Schema.FILE_DOWNLOAD + fileList.get(0).getFilePath() + "/" + Session.getUserToken(getApplicationContext())))));
                        secondFileWrapper.setVisibility(View.GONE);
                    } else if(fileList.size()==2) {
                        firstFile.setText(fileList.get(0).getFileName() + "[" + fileList.get(0).getDownloadCount() + "]");
                        firstFileWrapper.setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Schema.FILE_DOWNLOAD + fileList.get(0).getFilePath() + "/" + Session.getUserToken(getApplicationContext())))));
                        secondFile.setText(fileList.get(1).getFileName() + "[" + fileList.get(1).getDownloadCount() + "]");
                        secondFileWrapper.setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Schema.FILE_DOWNLOAD + fileList.get(1).getFilePath() + "/" + Session.getUserToken(getApplicationContext())))));
                    }
                } else {
                    firstFileWrapper.setVisibility(View.GONE);
                    secondFileWrapper.setVisibility(View.GONE);
                }

                if(!tmpModel.getComments().isEmpty()){

                    //fix NestiedScroollView in RecyclerView
                    commentView.setLayoutManager(new NestedInRecyclerManager(getApplicationContext()));
                    commentView.setHasFixedSize(false);
                    commentView.setNestedScrollingEnabled(false);

                    BoardCommentAdapter commentAdapter = new BoardCommentAdapter(getApplicationContext(), response.body().getResultData().get(0).getComments());
                    commentView.setAdapter(commentAdapter);

                }else{
                    commentWrapper.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("ContentActivity 에러", t.getMessage());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
