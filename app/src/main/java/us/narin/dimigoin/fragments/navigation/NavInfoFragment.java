package us.narin.dimigoin.fragments.navigation;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import us.narin.dimigoin.R;
import us.narin.dimigoin.activities.MainActivity;
import us.narin.dimigoin.adapter.InfoAdapter;
import us.narin.dimigoin.fragments.element.BoardFragment;
import us.narin.dimigoin.fragments.element.MealFragment;
import us.narin.dimigoin.util.Schema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NavInfoFragment extends Fragment {

    private int[] tabTitleIds = {
            R.string.tab_name_info_notice,
            R.string.tab_name_info_calendar,
            R.string.tab_name_info_meal,
            R.string.tab_name_info_contest,
            R.string.tab_name_info_kin,
            R.string.tab_name_info_enjoy,
            R.string.tab_name_info_playground,
            R.string.tab_name_info_market,
            R.string.tab_name_info_notice_moblie
    };

    private  Fragment[] boardFragments = {
            new BoardFragment(Schema.BoardIds.NOTICE),
            new BoardFragment(Schema.BoardIds.FREE),
            new MealFragment(),
            new BoardFragment(Schema.BoardIds.CONTEST_INFO),
            new BoardFragment(Schema.BoardIds.KIN),
            new BoardFragment(Schema.BoardIds.ENJOY),
            new BoardFragment(Schema.BoardIds.PLAYGROUND),
            new BoardFragment(Schema.BoardIds.MARKET),
            new BoardFragment(Schema.BoardIds.NOTICE_MOBILE)
    };

    TabLayout mTabLayout;
    FragmentManager fragmentManager;

    public NavInfoFragment(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ButterKnife.bind(getActivity());

        View mView = inflater.inflate(R.layout.fragment_info_nav, container, false);
        mTabLayout = ((MainActivity) getActivity()).mTabLayout;
        mTabLayout.setVisibility(View.VISIBLE);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        Log.d("NavInfoFragment", "onCreateView()");

        final List<Fragment> fragmentList = new ArrayList<>();

        Collections.addAll(fragmentList, boardFragments);

        ViewPager mViewPager = (ViewPager) mView.findViewById(R.id.fragment_info_vp);
        mViewPager.setAdapter(new InfoAdapter(fragmentManager, getActivity(), fragmentList));

        mTabLayout.setupWithViewPager(mViewPager);

        setTabsTitle(mTabLayout);

    return mView;
}

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(getActivity());
    }

    private void setTabsTitle(TabLayout mTabLayout) {
        for (int i = 0; i < tabTitleIds.length; i++) {
            final String tabTitle = getActivity().getString(tabTitleIds[i]);
            final TabLayout.Tab tab = mTabLayout.getTabAt(i);
            assert tab != null;
            tab.setText(tabTitle);
        }

    }

}
