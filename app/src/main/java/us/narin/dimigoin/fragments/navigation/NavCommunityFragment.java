package us.narin.dimigoin.fragments.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import us.narin.dimigoin.activities.EditorActivity;
import us.narin.dimigoin.activities.MainActivity;
import us.narin.dimigoin.adapter.BoardAdapter;
import us.narin.dimigoin.fragments.element.BoardFragment;
import us.narin.dimigoin.util.Schema;

import java.util.ArrayList;
import java.util.List;

public class NavCommunityFragment extends Fragment {

    private int[] tabTitleIds = {
            R.string.tab_name_community_file,
            R.string.tab_name_community_freeboard,
            R.string.tab_name_community_knowledge,
            R.string.tab_name_community_laf,
            R.string.tab_name_community_suggest
    };

    private Schema.BoardIds[] boardIds = {
            Schema.BoardIds.FILE,
            Schema.BoardIds.FREE,
            Schema.BoardIds.KNOWLEDGE,
            Schema.BoardIds.LOSTANDFOUND,
            Schema.BoardIds.SUGGEST,
    };

    private FragmentManager fragmentManager;

    @Bind(R.id.fragment_comunity_vp)
    ViewPager mViewPager;

    @Bind(R.id.community_fab)
    FloatingActionButton writorBtn;

    public NavCommunityFragment(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View mView = inflater.inflate(R.layout.fragment_community_nav, container, false);

        ButterKnife.bind(this, mView);

        TabLayout mTabLayout = ((MainActivity) getActivity()).mTabLayout;
        mTabLayout.setVisibility(View.VISIBLE);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        final List<Fragment> fragmentList = new ArrayList<>();

        for (Schema.BoardIds element : boardIds) {
            fragmentList.add(new BoardFragment(element));
        }

        mViewPager.setAdapter(new BoardAdapter(fragmentManager, getActivity(), fragmentList));

        mTabLayout.setupWithViewPager(mViewPager);

        setTabsTitle(mTabLayout);

        writorBtn.setOnClickListener(v -> startActivity(new Intent(getActivity(), EditorActivity.class)));

        return mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroy();
        ButterKnife.unbind(this);
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
