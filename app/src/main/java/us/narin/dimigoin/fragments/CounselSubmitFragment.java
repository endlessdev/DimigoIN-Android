package us.narin.dimigoin.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import us.narin.dimigoin.R;
import us.narin.dimigoin.util.Schema;
import us.narin.dimigoin.util.SessionManager;

public class CounselSubmitFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_counsel_submit, container, false);

        Button submitBtn = (Button) mView.findViewById(R.id.counsel_test_btn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Document doc = Jsoup.connect("http://mento.jeje.pe.kr/request_do.php")
                                    .cookie(Schema.LOGIN_COOKIE_KEY, SessionManager.getUserCookie(getActivity()))
                                    .data("date", "2016-01-06")
                                    .data("schedule", "1")
                                    .data("teacherno", "1")
                                    .post();

                            Log.d("신청", doc.body().text());

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();
            }
        });

        return mView;
    }


}
