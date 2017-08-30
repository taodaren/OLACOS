package net.osplay.ui.fragment.sub;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;

import net.osplay.olacos.R;
import net.osplay.ui.fragment.base.BaseFragment;
import net.osplay.utils.TabUtils;

/**
 * 社团模块
 */

public class LeagueFragment extends BaseFragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    //侧滑菜单
    private DrawerLayout mDrawerLayout;
    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.fragment_league, null);
        mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        tabLayout = (TabLayout) inflate.findViewById(R.id.league_tabLayout);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                TabUtils.setIndicator(tabLayout,25,25);
            }
        });
        viewPager = (ViewPager) inflate.findViewById(R.id.league_viewPager);
        viewPager.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            String[] itemName = new String[]{
                    "推荐", "社团活动", "社团作品"
            };
            @Override
            public int getCount() {
                return itemName.length;
            }

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new NewestFragment();
                    case 1:
                        return new HottestFragment();
                    case 2:
                        return new MineFragment();
                }
                return new NewestFragment();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return itemName[position];
            }
        });
        tabLayout.setupWithViewPager(viewPager);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setToolbar(R.id.toolbar_home, R.string.league_name, View.VISIBLE, View.GONE, true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//导航按钮固定 id
            //展示滑动菜单
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return true;
    }

}
