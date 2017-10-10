package net.osplay.ui.fragment.sub;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import net.osplay.olacos.R;
import net.osplay.ui.adapter.TabViewPagerAdapter;
import net.osplay.ui.fragment.base.BaseFragment;
import net.osplay.utils.TabUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 社区 → 热区
 */

public class WordHotFragment extends BaseFragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private String[] mTitles = new String[]{"专区", "热帖", "专栏"};

    @Override
    public View initView() {
        View view = View.inflate(getContext(), R.layout.fragment_word_hot, null);
        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout_word_hot);
        mViewPager = (ViewPager) view.findViewById(R.id.vp_word_hot);
        setTabLayout();
        setViewPager();
        mTabLayout.setupWithViewPager(mViewPager);
        return view;
    }

    private void setTabLayout() {
        //设置 TabLayout 下划线长度
        mTabLayout.post(new Runnable() {
            @Override
            public void run() {
                TabUtils.setIndicator(mTabLayout, 25, 25);
            }
        });
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitles[0]));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitles[1]), true);
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitles[2]));
    }

    private void setViewPager() {
        List<Fragment> mFragmentList = new ArrayList<>();
        mFragmentList.add(new WordHotTopicFragment());
        mFragmentList.add(new WordHotPostsFragment());
        mFragmentList.add(new WordHotColumnFragment());
        TabViewPagerAdapter mAdapter = new TabViewPagerAdapter(getChildFragmentManager(), mContext, mFragmentList, mTitles);
        mViewPager.setAdapter(mAdapter);
    }

}
