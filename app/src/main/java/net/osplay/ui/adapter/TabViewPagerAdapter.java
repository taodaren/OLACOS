package net.osplay.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * TabLayout + ViewPager 使用
 */

public class TabViewPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private String[] mTitles;
    private FragmentManager mManager;
    private List<Fragment> mFragmentList;

    public TabViewPagerAdapter(FragmentManager fm, Context mContext, List<Fragment> mFragmentList, String[] mTitles) {
        super(fm);
        this.mContext = mContext;
        this.mManager = fm;
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
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        mManager.beginTransaction().show(fragment).commitAllowingStateLoss();
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Fragment fragment = getItem(position);
        mManager.beginTransaction().hide(fragment).commitAllowingStateLoss();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

}
