package us.narin.dimigoin.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import us.narin.dimigoin.fragments.element.BoardFragment;
import us.narin.dimigoin.util.Schema;

/**
 * Created by Seungwoo on 2016. 1. 2..
 */

public class InfoAdapter extends FragmentPagerAdapter {

    private static final int COUNT_PAGES = 9;
    FragmentManager fm;
    Context mContext;

    public InfoAdapter(FragmentManager fm, Context mContext) {
        super(fm);
        this.fm = fm;
        this.mContext = mContext;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new BoardFragment(Schema.BoardIds.NOTICE);
            case 1:
                return new BoardFragment(Schema.BoardIds.FREE);
            case 2:
                return new BoardFragment(Schema.BoardIds.KNOWLEDGE);
            case 3:
                return new BoardFragment(Schema.BoardIds.CONTEST_INFO);
            case 4:
                return new BoardFragment(Schema.BoardIds.KIN);
            case 5:
                return new BoardFragment(Schema.BoardIds.ENJOY);
            case 6:
                return new BoardFragment(Schema.BoardIds.PLAYGROUND);
            case 7:
                return new BoardFragment(Schema.BoardIds.MARKET);
            case 8:
                return new BoardFragment(Schema.BoardIds.NOTICE_MOBILE);
        }
        return null;
    }

    @Override
    public int getCount() {
        return COUNT_PAGES;
    }
}
