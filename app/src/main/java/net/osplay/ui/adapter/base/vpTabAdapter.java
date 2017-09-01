package net.osplay.ui.adapter.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * TabLayout + ViewPager 使用
 */

public class vpTabAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private String[] mTitles;
    private List<Fragment> mFragmentList;

    public vpTabAdapter(Context mContext, FragmentManager fm, String[] mTitles, List<Fragment> mFragmentList) {
        super(fm);
        this.mContext = mContext;
        this.mTitles = mTitles;
        this.mFragmentList = mFragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
