package net.osplay.ui.activity.sub;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;

import net.osplay.ui.activity.base.BaseActivity;
import net.osplay.ui.fragment.sub.OrderALLFragment;
import net.osplay.ui.fragment.sub.OrderAssessFragment;
import net.osplay.ui.fragment.sub.OrderPayFragment;
import net.osplay.ui.fragment.sub.OrderReceiptFragment;
import net.osplay.ui.fragment.sub.OrderShipFragment;
import net.osplay.olacos.R;

/**
 * 我的订单
 */

public class OrderActivity extends BaseActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        initView();
    }

    private void initView() {
        setToolbar("我的订单", View.VISIBLE);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout_order);
        viewPager = (ViewPager) findViewById(R.id.vp_order);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            String[] itemName = {"全部", "待付款", "待发货", "待收货", "待评价"};

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new OrderALLFragment();
                    case 1:
                        return new OrderPayFragment();
                    case 2:
                        return new OrderShipFragment();
                    case 3:
                        return new OrderReceiptFragment();
                    case 4:
                        return new OrderAssessFragment();
                }
                return new OrderALLFragment();
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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

}
