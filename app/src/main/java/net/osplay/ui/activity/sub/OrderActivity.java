package net.osplay.ui.activity.sub;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import net.osplay.olacos.R;
import net.osplay.ui.activity.base.BaseActivity;
import net.osplay.ui.adapter.base.FragmentAdapter;
import net.osplay.ui.fragment.base.BaseFragment;
import net.osplay.ui.fragment.sub.OrderALLFragment;
import net.osplay.ui.fragment.sub.OrderAssessFragment;
import net.osplay.ui.fragment.sub.OrderPayFragment;
import net.osplay.ui.fragment.sub.OrderReceiptFragment;
import net.osplay.ui.fragment.sub.OrderShipFragment;
import net.osplay.ui.fragment.sub.TradingBuyFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的订单
 */

public class OrderActivity extends FragmentActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> mList = new ArrayList<>();
    private String[] titles = new String[]{"全部", "待付款", "待发货", "待收货", "待评价"};
    private FragmentAdapter fragmentAdapter = null;

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
        mList.add(new OrderALLFragment(OrderActivity.this,R.layout.fragment_order_all));
        mList.add(new OrderPayFragment(OrderActivity.this,R.layout.fragment_order_pay));
        mList.add(new OrderShipFragment(OrderActivity.this,R.layout.fragment_order_ship));
        mList.add(new OrderReceiptFragment(OrderActivity.this,R.layout.fragment_order_receipt));
        mList.add(new OrderAssessFragment(OrderActivity.this,R.layout.fragment_order_assess));
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),OrderActivity.this,mList,titles);
        viewPager.setAdapter(fragmentAdapter);
        //设置tablayout和viewpager绑定
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
