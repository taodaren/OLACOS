package net.osplay.ui.activity.sub;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;

import net.osplay.olacos.R;
import net.osplay.ui.activity.base.BaseActivity;
import net.osplay.ui.fragment.sub.ItemGoodsFragment;
import net.osplay.ui.fragment.sub.ItemPostsFragment;
import net.osplay.ui.fragment.sub.TradingBuyFragment;
import net.osplay.ui.fragment.sub.TradingSellFragment;

/**
 * 个人中心 → 我的收藏
 */

public class MineCollectActivity extends BaseActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_collect);
        initView();
    }

    private void initView() {
        setToolbar("收藏列表", View.VISIBLE);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout_collect);
        viewPager = (ViewPager) findViewById(R.id.vp_collect);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            String[] itemName = {"商品", "热帖"};

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new ItemGoodsFragment();
                    case 1:
                        return new ItemPostsFragment();
                }
                return new ItemGoodsFragment();
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
