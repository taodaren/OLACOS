package net.osplay.module_mine;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import net.osplay.module_mine.fragment.OrderALLFragment;
import net.osplay.module_mine.fragment.OrderAssessFragment;
import net.osplay.module_mine.fragment.OrderPayFragment;
import net.osplay.module_mine.fragment.OrderReceiptFragment;
import net.osplay.module_mine.fragment.OrderShipFragment;
import net.osplay.olacos.R;
import net.osplay.olacos.base.BaseActivity;

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

        setToolbar("我的订单", View.VISIBLE);
        initView();
    }

    private void initView() {
        //接收上一个 Activity 传来数据
        int tabNum = getIntent().getIntExtra("data", 1);
        switch (tabNum) {
            case 0:
                new OrderActivity();
                break;
            case 1:
                new OrderPayFragment();
                break;
            case 2:
                new OrderShipFragment();
                break;
            case 3:
                new OrderReceiptFragment();
                break;
            case 4:
                new OrderAssessFragment();
                break;
            default:
                break;
        }

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
        //滑动到指定页码
        viewPager.setCurrentItem(tabNum);
    }
}
