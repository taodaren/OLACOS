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
import net.osplay.ui.adapter.base.vpTabAdapter;
import net.osplay.ui.fragment.base.BaseFragment;
import net.osplay.utils.TabUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 社区模块
 */

public class TabWordFragment extends BaseFragment {
    private DrawerLayout mDrawerLayout;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<Fragment> mFragmentList;
    private String[] mTitles = new String[]{"热区", "我的"};
    private vpTabAdapter mAdapter;

    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.fragment_tab_word, null);
        mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);//注意使用的是 getActivity()
        mTabLayout = (TabLayout) inflate.findViewById(R.id.tab_layout_toolbar);
        mViewPager = (ViewPager) inflate.findViewById(R.id.vp_tab_word);
        setDrawerLayout();
        setTabLayout();
        setViewPager();
        mTabLayout.setupWithViewPager(mViewPager);
        return inflate;
    }

    private void setTabLayout() {
        //设置 TabLayout 下划线长度
        mTabLayout.post(new Runnable() {
            @Override
            public void run() {
                TabUtils.setIndicator(mTabLayout, 30, 30);
            }
        });
    }

    private void setViewPager() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new WordHotFragment(getActivity(),R.layout.fragment_word_hot));
        mFragmentList.add(new WordMineFragment(getActivity(),R.layout.fragment_word_mine));
        mAdapter = new vpTabAdapter(mContext, getChildFragmentManager(), mTitles, mFragmentList);
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
            //展示滑动菜单
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return true;
    }

}
