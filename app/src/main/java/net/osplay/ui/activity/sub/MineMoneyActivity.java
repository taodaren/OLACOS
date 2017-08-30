package net.osplay.ui.activity.sub;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;

import net.osplay.olacos.R;
import net.osplay.ui.activity.base.BaseActivity;
import net.osplay.ui.fragment.sub.MoneyConsumeFragment;
import net.osplay.ui.fragment.sub.MoneyValueFragment;
import net.osplay.ui.fragment.sub.MoneyWithdrawFragment;

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
            String[] itemName = {"充值记录", "消费记录", "提现记录"};

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
        setClickListener();
    }

    private void setClickListener() {
        //充值
        findViewById(R.id.btn_money_value).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MineMoneyActivity.this, MoneyValueActivity.class));
            }
        });

        //提现
        findViewById(R.id.btn_money_withdraw).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MineMoneyActivity.this, MoneyWithdrawActivity.class));
            }
        });
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
