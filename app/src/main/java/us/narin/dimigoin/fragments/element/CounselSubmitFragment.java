package us.narin.dimigoin.fragments.element;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import us.narin.dimigoin.R;
import us.narin.dimigoin.util.Schema;
import us.narin.dimigoin.util.Session;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CounselSubmitFragment extends Fragment {

    private static final String TAG = "CounselSubmitFragment";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_counsel_submit, container, false);

        Button submitBtn = (Button) mView.findViewById(R.id.counsel_test_btn);
        submitBtn.setOnClickListener(v -> new Thread(() -> {

            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
            Calendar mCalendar = Calendar.getInstance();
            mCalendar.setTime(new Date());
            mCalendar.add(Calendar.DATE, 2);


            try {
                Document doc = Jsoup.connect("http://counsel."+Schema.HOST+"/request_do.php")
                        .cookie(Schema.LOGIN_COOKIE_KEY, Session.getUserCookie(getActivity()))
                        .data("category", "1")
                        .data("schedule", "1")
                        .post();

                Log.d(TAG, doc.body().text());

            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start());

        return mView;
    }


}
