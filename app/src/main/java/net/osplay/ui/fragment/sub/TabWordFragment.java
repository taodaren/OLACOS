package net.osplay.ui.fragment.sub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;

import net.osplay.olacos.R;
import net.osplay.ui.adapter.TabViewPagerAdapter;
import net.osplay.ui.fragment.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 社区模块
 */

public class TabWordFragment extends BaseFragment {
    private DrawerLayout mDrawerLayout;//侧滑菜单
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private String[] mTitles = new String[]{"我的", "热区"};

    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.fragment_tab_word, null);

        mDrawerLayout = getActivity().findViewById(R.id.drawer_layout);
        mTabLayout = inflate.findViewById(R.id.tab_layout_toolbar);
        mViewPager = inflate.findViewById(R.id.vp_tab_word);

        initDrawerLayout();
        initTabLayout();
        initViewPager();
        mTabLayout.setupWithViewPager(mViewPager);
        return inflate;
    }

    private void initTabLayout() {
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitles[0]), 0, false);
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitles[1]), 1, true);
    }

    private void initViewPager() {
        List<Fragment> mFragmentList = new ArrayList<>();
        mFragmentList.add(new WordMineFragment());
        mFragmentList.add(new WordHotFragment());
        TabViewPagerAdapter mAdapter = new TabViewPagerAdapter(getChildFragmentManager(), mContext, mFragmentList, mTitles);
        mViewPager.setAdapter(mAdapter);
    }

    /**
     * 在 onActivityCreated 方法中初始化 Toolbar
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setToolbar(R.id.toolbar_word, R.string.word_name, View.GONE, View.GONE, true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//导航按钮固定 id
            mDrawerLayout.openDrawer(GravityCompat.START);//展示滑动菜单
        }
        return true;
    }

}
