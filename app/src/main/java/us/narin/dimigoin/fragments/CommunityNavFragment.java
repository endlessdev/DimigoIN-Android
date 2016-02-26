package us.narin.dimigoin.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import us.narin.dimigoin.R;
import us.narin.dimigoin.activities.MainActivity;
import us.narin.dimigoin.adapter.BoardAdapter;

public class CommunityNavFragment extends Fragment {

    private String[] newsTabTitles= {"자료게시판", "자유게시판", "지식나눔대회 후기", "분실물 & 습득물", "건의사항"};
    TabLayout mTabLayout;
        FragmentManager fragmentManager;
        public CommunityNavFragment(FragmentManager fragmentManager){
            this.fragmentManager = fragmentManager;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View mView = inflater.inflate(R.layout.fragment_community_nav, container, false);
            mTabLayout = ((MainActivity)getActivity()).mTabLayout;
            mTabLayout.setVisibility(View.VISIBLE);
            mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

            ViewPager mViewPager = (ViewPager)mView.findViewById(R.id.fragment_comunity_vp);
            mViewPager.setAdapter(new BoardAdapter(fragmentManager,getActivity()));

            mTabLayout.setupWithViewPager(mViewPager);

            for (int i=0; i<newsTabTitles.length; i++){
                mTabLayout.getTabAt(i).setText(newsTabTitles[i]);
            }

        return mView;
    }


}
