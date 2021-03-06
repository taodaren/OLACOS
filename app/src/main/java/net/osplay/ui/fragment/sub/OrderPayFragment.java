package net.osplay.ui.fragment.sub;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import net.osplay.olacos.R;
import net.osplay.ui.adapter.base.FragmentAdapter;
import net.osplay.ui.fragment.base.BaseFragment;
import net.osplay.utils.TabUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 待付款
 */

public class OrderPayFragment extends BaseFragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private String[] mTitles = new String[]{"我买到的", "我卖出的"};
    private FragmentAdapter mAdapter = null;

    @SuppressLint("ValidFragment")
    public OrderPayFragment() {
    }

    @SuppressLint("ValidFragment")
    public OrderPayFragment(Context mContext, int resId) {
        super(mContext, resId);
    }

    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.fragment_order_pay, null);
        mTabLayout = (TabLayout) inflate.findViewById(R.id.tab_layout_order_pay);
        mViewPager = (ViewPager) inflate.findViewById(R.id.vp_order_pay);

        //设置 TabLayout 下划线长度
        mTabLayout.post(new Runnable() {
            @Override
            public void run() {
                TabUtils.setIndicator(mTabLayout, 25, 25);
            }
        });

        mFragmentList.add(new TradingBuyFragment(getActivity(), R.layout.fragment_trading_buy));
        mFragmentList.add(new TradingSellFragment(getActivity(), R.layout.fragment_trading_sell));

        mAdapter = new FragmentAdapter(getChildFragmentManager(), mContext, mFragmentList, mTitles);
        mViewPager.setAdapter(mAdapter);

        //设置 TabLayout 和 ViewPager 绑定
        mTabLayout.setupWithViewPager(mViewPager);
        return inflate;
    }

}
