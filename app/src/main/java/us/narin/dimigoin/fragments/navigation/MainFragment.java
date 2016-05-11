package us.narin.dimigoin.fragments.navigation;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v4.widget.TextViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import us.narin.dimigoin.R;
import us.narin.dimigoin.activities.MainActivity;
import us.narin.dimigoin.api.ApiObject;
import us.narin.dimigoin.api.ApiRequests;
import us.narin.dimigoin.model.pojo.Meal;
import us.narin.dimigoin.util.Schema;

public class MainFragment extends Fragment {
    private ApiRequests apiRequests = ApiObject.initClient("http://dimigo.in");
    private Call<Meal> meal;

    private TextView txtMealTitle;
    private TextView txtMealContent;

    private Handler handler;

    private int nowTime = Integer.valueOf((new SimpleDateFormat("HH")).format(new Date()));

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View returnView = designInit(inflater, container);

        (new Thread() {
            @Override
            public void run() {
                super.run();

                try {
                    Document doc;
                    Elements titles = null;

                    doc = Jsoup.connect("http://dimigo.in/index.php").get();
                    titles = doc.select("div.wrapper div.at-content div.container div.row div div div.resp_3 div");

                    for(Element e: titles){
                        txtMealTitle.setText(e.text());
                    }
                } catch (IOException ioe) {}
            }
        }).run();

        meal = apiRequests.requestMeal();
        meal.enqueue(new Callback<Meal>() {
            @Override
            public void onResponse(Response<Meal> response) {
                final Meal meals = response.body();

                if(response.code() == 200) {
                    String mealContent = "", mealTitle = "";

                    if(nowTime <= 8) {
                        mealContent = meals.getMealBreakfast();
                        mealTitle = getString(R.string.home_title_today_meal_breakfast);
                    } else if(nowTime <= 13) {
                        mealContent = meals.getMealLunch();
                        mealTitle = getString(R.string.home_title_today_meal_lunch);
                    }else if(nowTime <= 18 || nowTime >= 18) {
                        mealContent = meals.getMealDinner();
                        mealTitle = getString(R.string.home_title_today_meal_dinner);
                    } else {
                        mealContent = getString(R.string.error_meal_get);
                    }

                    txtMealContent.setText(mealContent.replaceAll("/", " "));
                    txtMealTitle.setText(mealTitle);
                }

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });


        return returnView;
    }

    private View designInit(LayoutInflater inflater, ViewGroup container) {
        View mView = inflater.inflate(R.layout.fragment_main, container, false);

        TabLayout tabLayout = ((MainActivity)getActivity()).mTabLayout;
        tabLayout.setVisibility(View.GONE);

        txtMealTitle = (TextView)mView.findViewById(R.id.txt_meal_title);
        txtMealContent = (TextView)mView.findViewById(R.id.txt_meal_content);

        return mView;
    }

    private String unicodeDecoder(String uni) {
        String str = uni.split(" ")[0];
        str = str.replace("\\","");
        String[] arr = str.split("u");

        String decodeText = "";

        for(int i = 1; i < arr.length; i++){
            int hexVal = Integer.parseInt(arr[i], 16);
            decodeText += (char)hexVal;
        }

        return decodeText;
    }
}
