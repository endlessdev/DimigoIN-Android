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
import us.narin.dimigoin.adapter.BoardAdapter;

public class NavCommunityFragment extends Fragment {

    private int[] tabTitleIds = {
            R.string.tab_name_community_file,
            R.string.tab_name_community_freeboard,
            R.string.tab_name_community_knowledge,
            R.string.tab_name_community_laf,
            R.string.tab_name_community_suggest
    };
    private FragmentManager fragmentManager;

    @Bind(R.id.fragment_comunity_vp)
    ViewPager mViewPager;

    public NavCommunityFragment(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ButterKnife.bind(getActivity());

        View mView = inflater.inflate(R.layout.fragment_community_nav, container, false);
        TabLayout mTabLayout = ((MainActivity) getActivity()).mTabLayout;
        mTabLayout.setVisibility(View.VISIBLE);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        mViewPager.setAdapter(new BoardAdapter(fragmentManager, getActivity()));

        mTabLayout.setupWithViewPager(mViewPager);

        for (int i = 0; i < tabTitleIds.length; i++)
            mTabLayout.getTabAt(i).setText(getActivity().getString(tabTitleIds[i]));

        return mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
