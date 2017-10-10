package net.osplay.ui.activity.sub;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import net.osplay.olacos.R;
import net.osplay.ui.activity.base.BaseActivity;
import net.osplay.ui.adapter.base.FragmentAdapter;
import net.osplay.ui.fragment.sub.HeatRankFragment;
import net.osplay.ui.fragment.sub.NewlyCreatedFragment;
import net.osplay.utils.TabUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建或加入社团
 */
public class CreateOrJoinActivity extends BaseActivity {
    private TabLayout coj_tabLayout;
    private ViewPager coj_viewPager;
    private NewlyCreatedFragment newlyCreatedFragment;
    private HeatRankFragment heatRankFragment;
    private List<Fragment> mList = new ArrayList<>();
    private String[] titles = new String[]{"热门排行", "最新创建"};
    private FragmentAdapter fragmentAdapter = null;
    private Button coj_create_but, crj_addcommunity_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_or_join);
        setToolbar("社团", View.VISIBLE);
        initView();
        bindEvent();
    }

    private void initView() {
        coj_create_but = (Button) findViewById(R.id.coj_create_but);
        coj_tabLayout = (TabLayout) findViewById(R.id.coj_tabLayout);
        coj_viewPager = (ViewPager) findViewById(R.id.coj_viewPager);
        coj_tabLayout.post(new Runnable() {
            @Override
            public void run() {
                TabUtils.setIndicator(coj_tabLayout, 25, 25);
            }
        });
        newlyCreatedFragment = new NewlyCreatedFragment(CreateOrJoinActivity.this, R.layout.fragment_newly_created);//加入或创建中的热门排行
        heatRankFragment = new HeatRankFragment(CreateOrJoinActivity.this, R.layout.fragment_heat_rank);//加入或创建中的最新创建
        mList.add(heatRankFragment);
        mList.add(newlyCreatedFragment);
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), CreateOrJoinActivity.this, mList, titles);
        coj_viewPager.setAdapter(fragmentAdapter);
        coj_tabLayout.setupWithViewPager(coj_viewPager);//设置 TabLayout 和 ViewPager 绑定
        crj_addcommunity_btn = (Button) findViewById(R.id.crj_addcommunity_btn);

    }

    private void bindEvent() {
        coj_create_but.setOnClickListener(mOnClickListener);
        crj_addcommunity_btn.setOnClickListener(mOnClickListener);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.coj_create_but://创建社团
                    startActivity(new Intent(CreateOrJoinActivity.this, CreateCommunityActivity.class));
                    break;
                case R.id.crj_addcommunity_btn://加入社团
                    startActivity(new Intent(CreateOrJoinActivity.this, AllCommunityActivity.class));
                    break;
            }
        }
    };

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
