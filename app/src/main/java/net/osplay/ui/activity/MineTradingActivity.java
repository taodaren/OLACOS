package net.osplay.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;

import net.osplay.olacos.R;
import net.osplay.ui.fragment.TradingBuyFragment;
import net.osplay.ui.fragment.TradingSellFragment;

/**
 * 宅配宝箱
 */

public class MineTradingActivity extends BaseActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_trading);
        initView();
    }

    private void initView() {
        setToolbar("宅配宝箱", View.VISIBLE);

        //接收上一个 Activity 传来数据
        int tabNum = getIntent().getIntExtra("data", 1);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout_trading);
        viewPager = (ViewPager) findViewById(R.id.vp_trading);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
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
        //滑动到指定页码
        viewPager.setCurrentItem(tabNum);
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
