package net.osplay.ui.activity.sub;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import net.osplay.olacos.R;
import net.osplay.ui.adapter.base.FragmentAdapter;
import net.osplay.ui.fragment.sub.IMGroupFragment;
import net.osplay.ui.fragment.sub.IMGroupingFragment;
import net.osplay.ui.fragment.sub.IMNewsFragment;
import net.osplay.utils.TabUtils;

import java.util.ArrayList;
import java.util.List;



/**
 * 社团管理通讯
 */
public class LeagueIMActivity extends AppCompatActivity {
    private List<Fragment> lists = new ArrayList<>();
    private TabLayout league_im_tab;
    private ViewPager league_im_viewPager;
    private IMNewsFragment nFragment;//消息
    private IMGroupingFragment gpFragment;//分组
    private IMGroupFragment gFragment;//群聊
    private FragmentAdapter fragmentAdapter = null;
    private TextView im_more_tv;//更多
    private String[] titles = new String[]{"消息", "分组", "群聊"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_im_);
        initView();
        bindFragment();
        bindListener();

    }

    private void bindListener() {
        im_more_tv.setOnClickListener(mOnClickListener);
    }

    private void bindFragment() {
        lists.add(nFragment);
        lists.add(gpFragment);
        lists.add(gFragment);
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), LeagueIMActivity.this, lists, titles);
        league_im_viewPager.setAdapter(fragmentAdapter);
        league_im_tab.setupWithViewPager(league_im_viewPager);//设置 TabLayout 和 ViewPager 绑定
    }
    private void initView() {
        league_im_tab = (TabLayout) findViewById(R.id.league_im_tab);
        league_im_tab.post(new Runnable() {
            @Override
            public void run() {
                TabUtils.setIndicator(league_im_tab, 25, 25);
            }
        });
        league_im_viewPager = (ViewPager) findViewById(R.id.league_im_viewPager);
        im_more_tv = (TextView) findViewById(R.id.im_more_tv);
        nFragment = new IMNewsFragment();
        gpFragment = new IMGroupingFragment();
        gFragment = new IMGroupFragment();

    }


    private View.OnClickListener mOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.im_more_tv:

                    break;
            }
        }
    };

}
