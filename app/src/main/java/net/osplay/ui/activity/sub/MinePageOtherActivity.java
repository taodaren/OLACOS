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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import net.osplay.app.I;
import net.osplay.olacos.R;
import net.osplay.service.entity.OtherCenterBean;
import net.osplay.ui.activity.base.BaseActivity;
import net.osplay.ui.adapter.base.FragmentAdapter;
import net.osplay.ui.fragment.sub.OtherCollectionFragment;
import net.osplay.ui.fragment.sub.OtherFansFragment;
import net.osplay.ui.fragment.sub.OtherFocusFragment;
import net.osplay.ui.fragment.sub.OtherPostsFragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 个人主页（他人）
 */
public class MinePageOtherActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tv_mine_page_other_praise)
    TextView tvMinePageOtherPraise;
    @BindView(R.id.mine_page_other_praise)
    LinearLayout minePageOtherPraise;
    @BindView(R.id.mine_page_other_follow)
    LinearLayout minePageOtherFollow;
    @BindView(R.id.mine_page_other_fans)
    LinearLayout minePageOtherFans;
    @BindView(R.id.tv_mine_page_other_info)
    TextView tvMinePageOtherInfo;
    @BindView(R.id.mine_page_other_avatar)
    CircleImageView minePageOtherAvatar;
    @BindView(R.id.btn_mine_page_other_dou_picture)
    Button btnMinePageOtherDouPicture;
    @BindView(R.id.toolbar_mine_page_other)
    Toolbar toolbarMinePageOther;
    @BindView(R.id.collapsing_toolbar_mine_page_other)
    CollapsingToolbarLayout collapsingToolbarMinePageOther;
    @BindView(R.id.tl_mine_page_other)
    TabLayout tlMinePageOther;
    @BindView(R.id.vp_mine_page_other)
    ViewPager vpMinePageOther;

    private FragmentAdapter fragmentAdapter = null;
    private List<Fragment> lists = new ArrayList<>();
    private String[] titles = new String[]{"我的专区", "我的帖子", "我的收藏", "我的关注", "我的粉丝"};
    private OtherPostsFragment pFragment;//帖子
    private OtherCollectionFragment cFragment;//收藏
    private OtherFocusFragment fcFragemnt;//关注
    private OtherFansFragment faFragment;//
    private Gson mGson = new Gson();
    private OtherCenterBean otherCenterBean;
    private List<OtherCenterBean> otherList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_page_other);
        ButterKnife.bind(this);
        setToolbar();
        initView();
        initData();
        //setUserInfo();
    }


    //得到其他个人信息
    private void initData() {
        String memberId = getIntent().getStringExtra("memberId");
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest(I.OTHER_CENTER, RequestMethod.POST);
        request.add("memberId", memberId);
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                if (json != null) {
                    formatCenter(json);
                } else {
                    return;
                }

            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }

            @Override
            public void onFinish(int what) {

            }
        });
    }

    private void formatCenter(String json) {
        Type type = new TypeToken<List<OtherCenterBean>>() {
        }.getType();
        otherList = mGson.fromJson(json, type);

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
        pFragment = new OtherPostsFragment();
        cFragment = new OtherCollectionFragment();
        fcFragemnt = new OtherFocusFragment();
        faFragment = new OtherFansFragment();
        setTabLayout();
        findViewById(R.id.btn_mine_page_other_dou_picture).setOnClickListener(this);

    }

    private void setTabLayout() {
        lists.add(pFragment);
        lists.add(cFragment);
        lists.add(fcFragemnt);
        lists.add(faFragment);
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), MinePageOtherActivity.this, lists, titles);
        vpMinePageOther.setAdapter(fragmentAdapter);
        tlMinePageOther.setupWithViewPager(vpMinePageOther);//设置 TabLayout 和 ViewPager 绑定
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
