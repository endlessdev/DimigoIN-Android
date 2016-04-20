package us.narin.dimigoin.fragments.navigation;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import us.narin.dimigoin.R;
import us.narin.dimigoin.activities.MainActivity;
import us.narin.dimigoin.adapter.SubmitAdapter;

public class NavSubmitFragment extends Fragment {

    private String[] newsTabTitles = {"상담신청", "인강실신청", "멘토신청", "간식신청", "기상송신청", "잔류신청", "빨래신청", "방과후 신청"};
    TabLayout mTabLayout;
    FragmentManager fragmentManager;

    @Bind(R.id.fragment_submit_vp)
    ViewPager mViewPager;

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_submit_nav, container, false);

        ButterKnife.bind(this, mView);

        mTabLayout = ((MainActivity) getActivity()).mTabLayout;
        mTabLayout.setVisibility(View.VISIBLE);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        mViewPager.setAdapter(new SubmitAdapter(fragmentManager, getActivity()));
        mViewPager.setOffscreenPageLimit(0);

        mTabLayout.setupWithViewPager(mViewPager);

        for (int i = 0; i < newsTabTitles.length; i++) {
            mTabLayout.getTabAt(i).setText(newsTabTitles[i]);
        }

        return mView;
    }


}
