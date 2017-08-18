package net.osplay.module_mine.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import net.osplay.module_mine.fragment.MinePageDynamicFragment;
import net.osplay.module_mine.fragment.MinePageGoodsFragment;
import net.osplay.module_mine.fragment.MinePageWordFragment;
import net.osplay.olacos.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 个人主页
 */

public class MinePageActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_page);

        setToolbar();
        initView();
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_mine_page_center);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_page);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            //隐藏 Toolbar 自带标题栏
            actionBar.setDisplayShowTitleEnabled(false);
            //设置侧滑导航按钮图标
            actionBar.setHomeAsUpIndicator(R.drawable.title_back);
        }
        //隐藏 CollapsingToolbarLayout 标题
        collapsingToolbar.setTitleEnabled(false);
    }

    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tab_layout_page);
        viewPager = (ViewPager) findViewById(R.id.vp_page);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            String[] itemName = {"商品", "动态", "社区"};

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new MinePageGoodsFragment();
                    case 1:
                        return new MinePageDynamicFragment();
                    case 2:
                        return new MinePageWordFragment();
                }
                return new MinePageGoodsFragment();
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

//        viewPager.setAdapter(new MyTabAdapter(getSupportFragmentManager()));
        //绑定
        tabLayout.setupWithViewPager(viewPager);
    }

//    public class MyTabAdapter extends FragmentPagerAdapter {
//        private List<Fragment> fragments;
//        private List<String> titles;
//
//        public MyTabAdapter(FragmentManager fm) {
//            super(fm);
//            fragments = new ArrayList<>();
//            fragments.addAll(fragments);
//            titles = new ArrayList<>();
//            titles.addAll(titles);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return fragments.get(position);
//        }
//
//        @Override
//        public int getCount() {
//            return fragments == null ? 0 : fragments.size();
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return titles.get(position);
//        }
//    }

}
