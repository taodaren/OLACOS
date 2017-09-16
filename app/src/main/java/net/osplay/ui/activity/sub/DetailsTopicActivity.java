package net.osplay.ui.activity.sub;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import net.osplay.olacos.R;
import net.osplay.ui.activity.base.BaseActivity;
import net.osplay.ui.adapter.TabViewPagerAdapter;
import net.osplay.ui.fragment.sub.DetailsTopicInfoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 专题某一分区详情
 */

public class DetailsTopicActivity extends BaseActivity implements View.OnClickListener {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private String[] mTitles = new String[]{"道具", "摄影", "化妆", "COS正片"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_topic);

        initView();
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_topic_page);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_topic);

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
        findViewById(R.id.btn_topic_heck_in).setOnClickListener(this);
        findViewById(R.id.btn_topic_attention).setOnClickListener(this);
        findViewById(R.id.topic_page_avatar).setOnClickListener(this);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout_topic_details);
        mViewPager = (ViewPager) findViewById(R.id.vp_topic_details);

        setToolbar();
        setTabLayout();
        setViewPager();
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setTabLayout() {
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitles[0]));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitles[1]));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitles[2]));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitles[3]));
    }

    private void setViewPager() {
        List<Fragment> mFragmentList = new ArrayList<>();
        mFragmentList.add(new DetailsTopicInfoFragment(this, R.layout.fragment_word_hot_posts));
        mFragmentList.add(new DetailsTopicInfoFragment(this, R.layout.fragment_word_hot_posts));
        mFragmentList.add(new DetailsTopicInfoFragment(this, R.layout.fragment_word_hot_posts));
        mFragmentList.add(new DetailsTopicInfoFragment(this, R.layout.fragment_word_hot_posts));
        TabViewPagerAdapter mAdapter = new TabViewPagerAdapter(getSupportFragmentManager(), this, mFragmentList, mTitles);
        mViewPager.setAdapter(mAdapter);
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
            case R.id.btn_topic_heck_in://签到
                Toast.makeText(this, "btn_topic_heck_in", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_topic_attention://关注
                Toast.makeText(this, "btn_topic_attention", Toast.LENGTH_SHORT).show();
                break;
            case R.id.topic_page_avatar:
                startActivity(new Intent(this, MinePageOtherActivity.class));
                break;
        }
    }

}
