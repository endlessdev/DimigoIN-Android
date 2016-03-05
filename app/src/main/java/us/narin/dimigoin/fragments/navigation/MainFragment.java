package us.narin.dimigoin.fragments.navigation;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import us.narin.dimigoin.R;
import us.narin.dimigoin.activities.MainActivity;
import us.narin.dimigoin.util.CustomClock;

public class MainFragment extends Fragment {

    @Bind(R.id.main_clock)
    CustomClock mCustomClock;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(getActivity(), mView);

        TabLayout tabLayout = ((MainActivity)getActivity()).mTabLayout;
        tabLayout.setVisibility(View.GONE);

//        mCustomClock.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "Roboto-Thin.ttf"));

        return mView;
    }

}
