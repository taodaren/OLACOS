package net.osplay.ui.adapter.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * TabLayout + ViewPager 使用
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private List<Fragment> mFragmentList;
    private FragmentManager mFragmentManager;
    private String[] mTitles;

    public FragmentAdapter(FragmentManager fragmentManager, Context context, List<Fragment> fragmentList) {
        super(fragmentManager);
        this.mFragmentManager = fragmentManager;
        this.mContext = context;
        this.mFragmentList = fragmentList;
    }

    public FragmentAdapter(FragmentManager fragmentManager, Context context, List<Fragment> fragmentList, String[] titles) {
        super(fragmentManager);
        this.mFragmentManager = fragmentManager;
        this.mContext = context;
        this.mFragmentList = fragmentList;
        this.mTitles = titles;
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
        mFragmentManager.beginTransaction().show(fragment).commitAllowingStateLoss();
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Fragment fragment = getItem(position);
        mFragmentManager.beginTransaction().hide(fragment).commitAllowingStateLoss();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

}
