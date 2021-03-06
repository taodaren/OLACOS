package net.osplay.ui.activity.sub;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import net.osplay.app.AppHelper;
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
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 个人主页（他人）
 */
public class MinePageOtherActivity extends BaseActivity {
    @BindView(R.id.tv_mine_page_other_praise)
    TextView tvMinePageOtherPraise;
    @BindView(R.id.mine_page_other_follow)
    LinearLayout minePageOtherFollow;
    @BindView(R.id.mine_page_other_fans)
    LinearLayout minePageOtherFans;
    @BindView(R.id.tv_mine_page_other_info)
    TextView tvMinePageOtherInfo;
    @BindView(R.id.mine_page_other_avatar)
    CircleImageView minePageOtherAvatar;
    @BindView(R.id.btn_mine_page_other_picture)
    Button btnMinePageOtherPicture;
    @BindView(R.id.toolbar_mine_page_other)
    Toolbar toolbarMinePageOther;
    @BindView(R.id.collapsing_toolbar_mine_page_other)
    CollapsingToolbarLayout collapsingToolbarMinePageOther;
    @BindView(R.id.tl_mine_page_other)
    TabLayout tlMinePageOther;
    @BindView(R.id.vp_mine_page_other)
    ViewPager vpMinePageOther;
    @BindView(R.id.focus_tv)
    TextView focusTv;
    @BindView(R.id.fans_tv)
    TextView fansTv;

    private String[] titles = new String[]{"足迹", "百宝箱", "idol", "情敌"};
    private List<Fragment> lists = new ArrayList<>();
    private OtherPostsFragment pFragment;//帖子
    private OtherCollectionFragment cFragment;//收藏
    private OtherFocusFragment fcFragment;//关注
    private OtherFansFragment faFragment;//粉丝
    private Gson gson = new Gson();
    private String memberId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        memberId = getIntent().getStringExtra("memberId");
        setContentView(R.layout.activity_mine_page_other);
        ButterKnife.bind(this);
        setToolbar();
        initView();
        initUserData();//请求个人数据
        // TODO: 2017/10/7   关注关系发生改变  没有返回值
        //  initAttention();//请求关注信息
        Bundle bundle = new Bundle();
        bundle.putString("otherMemberId", memberId);
        pFragment.setArguments(bundle);
        cFragment.setArguments(bundle);
        fcFragment.setArguments(bundle);
        faFragment.setArguments(bundle);
    }

    private void initAttention() {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest(I.IS_FANS, RequestMethod.POST);
        request.add("memberId", AppHelper.getInstance().getUser().getID());
        request.add("followId", memberId);
        request.add("mark", 1);
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.e("TAG", "关注信息：" + json);
                if (json != null) {
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

    //得到其他个人信息
    private void initUserData() {
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

    //设置用户信息
    private void formatCenter(String json) {
        Type type = new TypeToken<List<OtherCenterBean>>() {
        }.getType();
        List<OtherCenterBean> otherList = gson.fromJson(json, type);
        //Glide.with(this).load(I.BASE_URL + otherList.get(0).getHEAD_PATH()).error(R.drawable.avatar_default).into(minePageOtherAvatar);
        Glide.with(this).load(I.BASE_URL + otherList.get(0).getHEAD_PATH()).into(minePageOtherAvatar);
        tvMinePageOtherPraise.setText(otherList.get(0).getNICK_NAME());
        focusTv.setText(otherList.get(0).getFOCUS_COUNT());
        fansTv.setText(otherList.get(0).getFANS_COUNT());
        if (otherList.get(0).getINTRODUCE() != null) {
            tvMinePageOtherInfo.setText(otherList.get(0).getINTRODUCE());
        }
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
        fcFragment = new OtherFocusFragment();
        faFragment = new OtherFansFragment();
        setTabLayout();
    }

    private void setTabLayout() {
        lists.add(pFragment);
        lists.add(cFragment);
        lists.add(fcFragment);
        lists.add(faFragment);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), MinePageOtherActivity.this, lists, titles);
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

    @OnClick(R.id.btn_mine_page_other_picture)
    public void onViewClicked() {
        Toast.makeText(MinePageOtherActivity.this,"功能还未开放，敬请期待！",Toast.LENGTH_SHORT).show();
        //startActivity(new Intent(MinePageOtherActivity.this, DouPictureActivity.class));
    }
}
