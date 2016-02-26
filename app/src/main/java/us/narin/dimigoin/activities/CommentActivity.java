package us.narin.dimigoin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import us.narin.dimigoin.R;
import us.narin.dimigoin.adapter.BoardCommentAdapter;
import us.narin.dimigoin.api.ApiObject;
import us.narin.dimigoin.api.ApiRequests;
import us.narin.dimigoin.model.ContentDetail;
import us.narin.dimigoin.util.Session;

public class CommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        showAsPopup();

        Intent mIntent = getIntent();

        final Integer contentId = mIntent.getIntExtra("content_id", 0);
        final String boardId = mIntent.getStringExtra("content_board");

        final RecyclerView commentView = (RecyclerView) findViewById(R.id.bbs_content_detail_comment_rv);
        final CardView commentWrapper = (CardView) findViewById(R.id.bbs_content_detail_comment_wrappper);

        commentWrapper.setVisibility(View.GONE);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        commentView.setLayoutManager(linearLayoutManager);
        commentView.setHasFixedSize(true);

        ApiRequests apiRequests = ApiObject.initClient();
        Call<ContentDetail> callDetail = apiRequests.getContentDetail(boardId, contentId, Session.getUserToken(getApplicationContext()));

        callDetail.enqueue(new Callback<ContentDetail>() {
            @Override
            public void onResponse(Response<ContentDetail> response) {
                commentView.setAdapter(new BoardCommentAdapter(getApplicationContext(), response.body().getResultData().get(0).getComments()));
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_comment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showAsPopup() {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = (int) (displayMetrics.widthPixels * 0.90);
        int height = (int) (displayMetrics.heightPixels * 0.80);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND,
                WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 1.0f;
        params.dimAmount = 0.5f;
        getWindow().setAttributes(params);
        getWindow().setLayout(width, height);
    }

}
