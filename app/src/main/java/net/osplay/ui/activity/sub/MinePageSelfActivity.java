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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.osplay.app.AppHelper;
import net.osplay.app.I;
import net.osplay.olacos.R;
import net.osplay.ui.activity.base.BaseActivity;
import net.osplay.ui.fragment.sub.MinePageDynamicFragment;
import net.osplay.ui.fragment.sub.MinePageGoodsFragment;
import net.osplay.ui.fragment.sub.MinePageWordFragment;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 个人主页（自己）
 */

public class MinePageSelfActivity extends BaseActivity implements View.OnClickListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private CircleImageView nickIcon;
    private TextView tv_mine_page_praise,attention_tv,fans_tv,tv_mine_page_info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_page_self);
        setToolbar();
        initView();
        setUserInfo();
    }

    private void setUserInfo() {
        if(AppHelper.getInstance().isLogined()){
            Glide.with(MinePageSelfActivity.this).load(I.BASE_URL+AppHelper.getInstance().getUser().getHEAD_PATH()).into(nickIcon);
            tv_mine_page_praise.setText(AppHelper.getInstance().getUser().getNICK_NAME());
            attention_tv.setText(AppHelper.getInstance().getUser().getFOCUS_COUNT());
            fans_tv.setText(AppHelper.getInstance().getUser().getFANS_COUNT());
            tv_mine_page_info.setText(AppHelper.getInstance().getUser().getINTRODUCE());

        }

    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_mine_page);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_mine_page);
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
        findViewById(R.id.btn_mine_page_edit_picture).setOnClickListener(this);
        nickIcon= (CircleImageView) findViewById(R.id.mine_page_avatar);
        tv_mine_page_praise= (TextView) findViewById(R.id.tv_mine_page_praise);
        attention_tv= (TextView) findViewById(R.id.attention_tv);
        fans_tv= (TextView) findViewById(R.id.fans_tv);
        tv_mine_page_info= (TextView) findViewById(R.id.tv_mine_page_info);
        setTabLayout();
    }

    /**
     * 设置 TabLayout
     */
    private void setTabLayout() {
        tabLayout = (TabLayout) findViewById(R.id.tl_mine_page);
        viewPager = (ViewPager) findViewById(R.id.vp_mine_page);
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
            case R.id.btn_mine_page_edit_picture://编辑资料
                startActivity(new Intent(MinePageSelfActivity.this, EditInfoActivity.class));
                break;
        }
    }

}
