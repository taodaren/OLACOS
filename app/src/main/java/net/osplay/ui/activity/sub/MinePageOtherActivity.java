package net.osplay.ui.activity.sub;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import net.osplay.olacos.R;
import net.osplay.ui.activity.base.BaseActivity;
import net.osplay.ui.fragment.sub.MycollectionFragment;
import net.osplay.ui.fragment.sub.MypostsFragment;
import net.osplay.ui.fragment.sub.MyareaFragment;

/**
 * 个人主页（他人）
 */

public class MinePageOtherActivity extends BaseActivity implements View.OnClickListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_page_other);
        setToolbar();
        initView();
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_mine_page_other);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_mine_page_other);
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
        setTabLayout();
        findViewById(R.id.btn_mine_page_other_dou_picture).setOnClickListener(this);
    }

    /**
     * 设置 TabLayout
     */
    private void setTabLayout() {
        tabLayout = (TabLayout) findViewById(R.id.tl_mine_page_other);
        viewPager = (ViewPager) findViewById(R.id.vp_mine_page_other);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            String[] itemName = {"商品", "动态", "社区"};

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new MyareaFragment();
                    case 1:
                        return new MypostsFragment();
                    case 2:
                        return new MycollectionFragment();
                }
                return new MyareaFragment();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_mine_page_other_dou_picture://斗图
                startActivity(new Intent(MinePageOtherActivity.this, DouPictureActivity.class));
                break;
        }
    }
}
