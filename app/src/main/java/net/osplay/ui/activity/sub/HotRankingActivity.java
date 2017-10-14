package net.osplay.ui.activity.sub;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.osplay.olacos.R;
import net.osplay.ui.activity.base.BaseActivity;
import net.osplay.ui.adapter.base.FragmentAdapter;
import net.osplay.ui.fragment.sub.HeatRankFragment;
import net.osplay.ui.fragment.sub.NewlyCreatedFragment;
import net.osplay.utils.TabUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 社团的热门排行
 */
public class HotRankingActivity extends BaseActivity {

    @BindView(R.id.coj_tabLayout)
    TabLayout cojTabLayout;
    @BindView(R.id.coj_viewPager)
    ViewPager cojViewPager;
    private NewlyCreatedFragment newlyCreatedFragment;
    private HeatRankFragment heatRankFragment;
    private List<Fragment> mList = new ArrayList<>();
    private String[] titles = new String[]{"热门排行", "最新创建"};
    private FragmentAdapter fragmentAdapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_ranking);
        setToolbar("更多社团", View.VISIBLE);
        ButterKnife.bind(this);
        cojTabLayout.post(new Runnable() {
            @Override
            public void run() {
                TabUtils.setIndicator(cojTabLayout, 25, 25);
            }
        });
        newlyCreatedFragment = new NewlyCreatedFragment();//最新创建
        heatRankFragment = new HeatRankFragment();//热门排行
        mList.add(heatRankFragment);
        mList.add(newlyCreatedFragment);
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),this, mList, titles);
        cojViewPager.setAdapter(fragmentAdapter);
        cojTabLayout.setupWithViewPager(cojViewPager);//设置 TabLayout 和 ViewPager 绑定
    }
}
