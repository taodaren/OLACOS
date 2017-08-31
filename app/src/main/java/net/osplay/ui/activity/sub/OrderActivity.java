package net.osplay.ui.activity.sub;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import net.osplay.olacos.R;
import net.osplay.ui.activity.base.BaseActivity;
import net.osplay.ui.fragment.base.BaseFragment;
import net.osplay.ui.fragment.sub.OrderALLFragment;
import net.osplay.ui.fragment.sub.OrderAssessFragment;
import net.osplay.ui.fragment.sub.OrderPayFragment;
import net.osplay.ui.fragment.sub.OrderReceiptFragment;
import net.osplay.ui.fragment.sub.OrderShipFragment;

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
//        setToolbar();
        setTabLayout();
    }

    private void setTabLayout() {
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

//    private void setToolbar() {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.order_toolbar);
//        setSupportActionBar(toolbar);
//
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            //设置左上角后退按钮
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            //设置导航按钮图标
//            actionBar.setHomeAsUpIndicator(R.drawable.title_back);
//            //隐藏 Toolbar 自带标题栏
//            actionBar.setDisplayShowTitleEnabled(false);
//        }
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                finish();
//                break;
//        }
//        return true;
//    }

}
