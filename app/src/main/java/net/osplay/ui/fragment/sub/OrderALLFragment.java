package net.osplay.ui.fragment.sub;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import net.osplay.olacos.R;
import net.osplay.ui.fragment.base.BaseFragment;

/**
 * 全部订单
 */
public class OrderALLFragment extends BaseFragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.fragment_order_all, null);

        tabLayout = (TabLayout) inflate.findViewById(R.id.tab_layout_order_all);
        viewPager = (ViewPager) inflate.findViewById(R.id.vp_order_all);
        viewPager.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            String[] itemName = {"我买到的", "我卖出的"};

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new TradingBuyFragment();
                    case 1:
                        return new TradingSellFragment();
                }
                return new TradingBuyFragment();
            }

            @Override
            public int getCount() {
                return itemName.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return itemName[position];
            }
        });

        //绑定
        tabLayout.setupWithViewPager(viewPager);
        return inflate;
    }

}