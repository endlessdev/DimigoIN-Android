package us.narin.dimigoin.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import us.narin.dimigoin.R;
import us.narin.dimigoin.activities.MainActivity;
import us.narin.dimigoin.adapter.InfoAdapter;

public class InfoNavFragment extends Fragment {

    private String[] newsTabTitles = {"공지사항", "학사일정", "디미밥", "대회정보", "지식IN", "맛집 및 놀거리", "디미운동장", "디미마켓", "모바일 공지"};
    TabLayout mTabLayout;
    FragmentManager fragmentManager;

    public InfoNavFragment(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_info_nav, container, false);
        mTabLayout = ((MainActivity) getActivity()).mTabLayout;
        mTabLayout.setVisibility(View.VISIBLE);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        Log.d("InfoNavFragment","onCreateView()");

        ViewPager mViewPager = (ViewPager) mView.findViewById(R.id.fragment_info_vp);
        mViewPager.setAdapter(new InfoAdapter(fragmentManager, getActivity()));

        mTabLayout.setupWithViewPager(mViewPager);

        for (int i = 0; i < newsTabTitles.length; i++) {
            mTabLayout.getTabAt(i).setText(newsTabTitles[i]);
        }

        return mView;
    }


}
