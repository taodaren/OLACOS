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
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.osplay.app.AppHelper;
import net.osplay.app.I;
import net.osplay.olacos.R;
import net.osplay.ui.activity.base.BaseActivity;
import net.osplay.ui.adapter.base.FragmentAdapter;
import net.osplay.ui.fragment.sub.MyFansFragment;
import net.osplay.ui.fragment.sub.MycollectionFragment;
import net.osplay.ui.fragment.sub.MyfocusFragment;
import net.osplay.ui.fragment.sub.MypostsFragment;
import net.osplay.ui.fragment.sub.MyareaFragment;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 个人主页（自己）
 */

public class MinePageSelfActivity extends BaseActivity implements View.OnClickListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentAdapter fragmentAdapter = null;
    private List<Fragment> lists = new ArrayList<>();
    private String[] titles = new String[]{"我的专区", "我的帖子", "我的收藏","我的关注","我的粉丝"};
    private CircleImageView nickIcon;
    private TextView tv_mine_page_praise,attention_tv,fans_tv,tv_mine_page_info;
    private MyareaFragment aFragment;
    private MypostsFragment  pFragment;
    private MycollectionFragment cFragment;
    private MyfocusFragment fcFragemnt;
    private MyFansFragment  faFragment;

    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_page_self);
        setToolbar();
        initView();
        setUserInfo();
    }

    private void setUserInfo() {
            Glide.with(MinePageSelfActivity.this).load(I.BASE_URL+AppHelper.getInstance().getUser().getHEAD_PATH()).into(nickIcon);
            tv_mine_page_praise.setText(AppHelper.getInstance().getUser().getNICK_NAME());
            attention_tv.setText(AppHelper.getInstance().getUser().getFOCUS_COUNT());
            fans_tv.setText(AppHelper.getInstance().getUser().getFANS_COUNT());
            tv_mine_page_info.setText(AppHelper.getInstance().getUser().getINTRODUCE());
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
        tabLayout = (TabLayout) findViewById(R.id.tl_mine_page);
        viewPager = (ViewPager) findViewById(R.id.vp_mine_page);
        aFragment=new MyareaFragment();
        pFragment=new MypostsFragment();
        cFragment=new MycollectionFragment();
        fcFragemnt=new MyfocusFragment();
        faFragment=new MyFansFragment();
        setTabLayout();
    }

    /**
     * 设置 TabLayout
     */
    private void setTabLayout() {
        lists.add(aFragment);
        lists.add(pFragment);
        lists.add(cFragment);
        lists.add(fcFragemnt);
        lists.add(faFragment);
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), MinePageSelfActivity.this, lists, titles);
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);//设置 TabLayout 和 ViewPager 绑定

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
