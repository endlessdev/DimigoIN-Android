package us.narin.dimigoin.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Seungwoo on 2016. 1. 2..
 */

public class InfoAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    Context mContext;


    public InfoAdapter(FragmentManager fm, Context mContext, List<Fragment> fragmentList) {
        super(fm);
        this.mContext = mContext;
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
