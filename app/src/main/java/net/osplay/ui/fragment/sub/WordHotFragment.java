package net.osplay.ui.fragment.sub;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import net.osplay.olacos.R;
import net.osplay.ui.adapter.base.FragmentAdapter;
import net.osplay.ui.adapter.base.vpTabAdapter;
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
    private List<Fragment> mFragmentList;
    private String[] mTitles = new String[]{"专题", "热帖", "专栏"};
    private vpTabAdapter mAdapter;

    public WordHotFragment() {
    }

    public WordHotFragment(Context mContext, int resId) {
        super(mContext, resId);
    }

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
    }

    private void setViewPager() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new WordHotTopicFragment());
        mFragmentList.add(new WordHotPostsFragment());
        mFragmentList.add(new WordHotColumnFragment());
        mAdapter = new vpTabAdapter(mContext, getChildFragmentManager(), mTitles, mFragmentList);
        mViewPager.setAdapter(mAdapter);
    }

}
