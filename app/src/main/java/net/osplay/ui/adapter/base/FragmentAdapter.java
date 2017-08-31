package net.osplay.ui.adapter.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by leeandy007 on 2017/6/15.
 */

public class FragmentAdapter extends FragmentPagerAdapter {

    private Context context;

    private List<Fragment> list;

    private FragmentManager fm;

    private String[] mTitles;


    public FragmentAdapter(FragmentManager fm, Context context, List<Fragment> list) {
        super(fm);
        this.fm = fm;
        this.context = context;
        this.list = list;
    }

    public FragmentAdapter(FragmentManager fm, Context context, List<Fragment> list, String[] titles) {
        super(fm);
        this.fm = fm;
        this.context = context;
        this.list = list;
        this.mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        fm.beginTransaction().show(fragment).commitAllowingStateLoss();
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Fragment fragment = getItem(position);
        fm.beginTransaction().hide(fragment).commitAllowingStateLoss();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
