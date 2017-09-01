package net.osplay.ui.activity.sub;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import net.osplay.olacos.R;
import net.osplay.ui.activity.base.BaseActivity;
import net.osplay.ui.adapter.base.FragmentAdapter;
import net.osplay.ui.fragment.sub.OrderALLFragment;
import net.osplay.ui.fragment.sub.OrderAssessFragment;
import net.osplay.ui.fragment.sub.OrderPayFragment;
import net.osplay.ui.fragment.sub.OrderReceiptFragment;
import net.osplay.ui.fragment.sub.OrderShipFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的订单
 */

public class OrderActivity extends BaseActivity {
    private List<Fragment> mFragmentList = new ArrayList<>();
    private String[] mTitles = new String[]{"全部", "待付款", "待发货", "待收货", "待评价"};
    private FragmentAdapter mAdapter = null;

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
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout_order);
        ViewPager viewPager = (ViewPager) findViewById(R.id.vp_order);

        mFragmentList.add(new OrderALLFragment(this, R.layout.fragment_order_all));
        mFragmentList.add(new OrderPayFragment(this, R.layout.fragment_order_pay));
        mFragmentList.add(new OrderShipFragment(this, R.layout.fragment_order_ship));
        mFragmentList.add(new OrderReceiptFragment(this, R.layout.fragment_order_receipt));
        mFragmentList.add(new OrderAssessFragment(this, R.layout.fragment_order_assess));

        mAdapter = new FragmentAdapter(getSupportFragmentManager(), OrderActivity.this, mFragmentList, mTitles);
        viewPager.setAdapter(mAdapter);

        //设置 TabLayout 和 ViewPager 绑定
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
