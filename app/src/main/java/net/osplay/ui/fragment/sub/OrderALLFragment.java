package net.osplay.ui.fragment.sub;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import net.osplay.olacos.R;
import net.osplay.ui.adapter.base.FragmentAdapter;
import net.osplay.ui.fragment.base.BaseBussFragment;
import net.osplay.ui.fragment.base.BaseFragment;
import net.osplay.utils.TabUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 全部订单
 */
public class OrderALLFragment extends BaseFragment {

    public OrderALLFragment(){

    }

    public OrderALLFragment(Context mContext, int resId) {
        super(mContext, resId);
    }
    private TabLayout tab_layout_order_all;
    private ViewPager vp_order_all;
    private List<Fragment> mList = new ArrayList<>();
    private String[] titles = new String[]{"我买到的", "我卖出的"};
    private FragmentAdapter fragmentAdapter = null;

    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.fragment_order_all, null);
        tab_layout_order_all= (TabLayout) inflate.findViewById(R.id.tab_layout_order_all);
        vp_order_all= (ViewPager) inflate.findViewById(R.id.vp_order_all);
        tab_layout_order_all.post(new Runnable() {
            @Override
            public void run() {
                TabUtils.setIndicator(tab_layout_order_all,25,25);
            }
        });
        mList.add(new TradingBuyFragment(getActivity(),R.layout.fragment_trading_buy));
        mList.add(new TradingSellFragment(getActivity(),R.layout.fragment_trading_sell));
        fragmentAdapter = new FragmentAdapter(getChildFragmentManager(),mContext,mList,titles);
        vp_order_all.setAdapter(fragmentAdapter);
        //设置tablayout和viewpager绑定
        tab_layout_order_all.setupWithViewPager(vp_order_all);
        return inflate;
    }


}