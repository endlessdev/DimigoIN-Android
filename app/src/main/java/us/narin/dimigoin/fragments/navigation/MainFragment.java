package us.narin.dimigoin.fragments.navigation;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import us.narin.dimigoin.R;
import us.narin.dimigoin.activities.MainActivity;

public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_main, container, false);

        TabLayout tabLayout = ((MainActivity)getActivity()).mTabLayout;
        tabLayout.setVisibility(View.GONE);

        return mView;
    }

}
