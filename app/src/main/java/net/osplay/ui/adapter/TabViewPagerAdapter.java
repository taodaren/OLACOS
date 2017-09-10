package net.osplay.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * TabLayout + ViewPager 使用
 */

public class TabViewPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private String[] mTitles;
    private List<Fragment> mFragmentList;

    public TabViewPagerAdapter(FragmentManager fm, Context mContext, List<Fragment> mFragmentList, String[] mTitles) {
        super(fm);
        this.mContext = mContext;
        this.mFragmentList = mFragmentList;
        this.mTitles = mTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

}
