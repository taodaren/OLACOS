package net.osplay.ui.fragment.sub;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import net.osplay.olacos.R;
import net.osplay.ui.fragment.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 专区详情容器部分
 */

public class DetailsTopicInfoFragment extends BaseFragment implements TabLayout.OnTabSelectedListener {
    private static final int FRAGMENT_ALL = 0;
    private static final int FRAGMENT_FINE = 1;
    private static final int FRAGMENT_CITY = 2;
    @BindView(R.id.container_fm_details_topic_info)
    FrameLayout containerFmDetailsTopicInfo;
    Unbinder unbinder;
    @BindView(R.id.tbl_fm_details_topic_info)
    TabLayout tblFmDetailsTopicInfo;

    private Fragment[] mFragments;
    private BaseFragment currentFragment;
    private TopicInfoAllFragment mAllFragment;
    private TopicInfoFineFragment mFineFragment;
    private TopicInfoCityFragment mCityFragment;

    @SuppressLint("ValidFragment")
    public DetailsTopicInfoFragment() {
    }

    @SuppressLint("ValidFragment")
    public DetailsTopicInfoFragment(Context context, int resId) {
        super(context, resId);
    }

    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.fragment_details_topic_info, null);
        return inflate;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        assert rootView != null;
        unbinder = ButterKnife.bind(this, rootView);

        tblFmDetailsTopicInfo.addTab(tblFmDetailsTopicInfo.newTab().setText(R.string.text_all), true);
        tblFmDetailsTopicInfo.addTab(tblFmDetailsTopicInfo.newTab().setText(R.string.text_fine));
        tblFmDetailsTopicInfo.addTab(tblFmDetailsTopicInfo.newTab().setText(R.string.text_same_city));
        tblFmDetailsTopicInfo.addOnTabSelectedListener(this);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initFragment();
        showFragment();
    }

    @SuppressLint("CommitTransaction")
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case FRAGMENT_ALL://点击全部
                addOrShowFragment(getActivity().getSupportFragmentManager().beginTransaction(), mAllFragment);
                break;
            case FRAGMENT_FINE://点击精品
                addOrShowFragment(getActivity().getSupportFragmentManager().beginTransaction(), mFineFragment);
                break;
            case FRAGMENT_CITY://点击同城
                addOrShowFragment(getActivity().getSupportFragmentManager().beginTransaction(), mCityFragment);
                break;
        }
    }

    private void initFragment() {
        mAllFragment = new TopicInfoAllFragment();
        mFineFragment = new TopicInfoFineFragment();
        mCityFragment = new TopicInfoCityFragment();

        mFragments = new Fragment[3];
        mFragments[0] = mAllFragment;
        mFragments[1] = mFineFragment;
        mFragments[2] = mCityFragment;

        currentFragment = mAllFragment;
    }

    private void showFragment() {
        getChildFragmentManager().beginTransaction()
                .add(R.id.container_fm_details_topic_info, mFragments[0])
                .add(R.id.container_fm_details_topic_info, mFragments[1])
                .add(R.id.container_fm_details_topic_info, mFragments[2])
                .show(mFragments[0])
                .hide(mFragments[1])
                .hide(mFragments[2])
                .commit();
    }

    private void addOrShowFragment(FragmentTransaction transaction, BaseFragment fragment) {
        if (currentFragment == fragment)
            return;
        if (!fragment.isAdded()) {//如果当前 fragment 未被添加，则添加到 Fragment 管理器中
            transaction.hide(currentFragment).add(R.id.tab_home_content, fragment).commit();
        } else {//如果当前 fragment 已添加，则显示该 Fragment
            transaction.hide(currentFragment).show(fragment).commit();
        }
        currentFragment = fragment;
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
