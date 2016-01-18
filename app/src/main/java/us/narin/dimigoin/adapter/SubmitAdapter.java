package us.narin.dimigoin.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import us.narin.dimigoin.fragments.CommunityFragment;
import us.narin.dimigoin.fragments.CounselSubmitFragment;
import us.narin.dimigoin.fragments.MentoSubmitFragment;
import us.narin.dimigoin.util.BoardIds;

/**
 * Created by Seungwoo on 2016. 1. 2..
 */
public class SubmitAdapter extends FragmentPagerAdapter {

    private static final int COUNT_PAGES = 8;
    FragmentManager fm;
    Context mContext;

    public SubmitAdapter(FragmentManager fm, Context mContext) {
        super(fm);
        this.fm = fm;
        this.mContext = mContext;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new CounselSubmitFragment();
            case 1:
                return new MentoSubmitFragment();
            case 2:
                return new MentoSubmitFragment();
            case 3:
                return new MentoSubmitFragment();
            case 4:
                return new MentoSubmitFragment();
            case 5:
                return new MentoSubmitFragment();
            case 6:
                return new MentoSubmitFragment();
            case 7:
                return new MentoSubmitFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return COUNT_PAGES;
    }
}
