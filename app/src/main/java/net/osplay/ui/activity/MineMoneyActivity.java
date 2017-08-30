package net.osplay.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;

import net.osplay.olacos.R;
import net.osplay.ui.fragment.MoneyConsumeFragment;
import net.osplay.ui.fragment.MoneyValueFragment;
import net.osplay.ui.fragment.MoneyWithdrawFragment;
import net.osplay.ui.fragment.TradingBuyFragment;
import net.osplay.ui.fragment.TradingSellFragment;

/**
 * 个人中心 → 我的钱包
 */

public class MineMoneyActivity extends BaseActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_money);
        initView();
    }

    private void initView() {
        setToolbar("我的钱包", View.VISIBLE);

        //接收上一个 Activity 传来数据
        int tabNum = getIntent().getIntExtra("data", 1);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout_money);
        viewPager = (ViewPager) findViewById(R.id.vp_money);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            String[] itemName = {"充值记录", "消费记录","提现记录"};

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new MoneyValueFragment();
                    case 1:
                        return new MoneyConsumeFragment();
                    case 2:
                        return new MoneyWithdrawFragment();
                }
                return new MoneyValueFragment();
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
