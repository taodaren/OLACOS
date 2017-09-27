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
import android.widget.ProgressBar;
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
import net.osplay.service.entity.WordTopicTitleBean;
import net.osplay.ui.activity.base.BaseActivity;
import net.osplay.ui.adapter.TabViewPagerAdapter;
import net.osplay.ui.fragment.sub.DetailsTopicInfoFragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 专题某一分区详情
 */

public class DetailsTopicActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.topic_details_nick)
    TextView topicDetailsNick;
    @BindView(R.id.tv_topic_details_level)
    TextView tvTopicDetailsLevel;
    @BindView(R.id.pb_topic_details_level)
    ProgressBar pbTopicDetailsLevel;
    @BindView(R.id.topic_page_avatar)
    CircleImageView topicPageAvatar;
    @BindView(R.id.btn_topic_heck_in)
    Button btnHeckIn;
    @BindView(R.id.btn_topic_attention)
    Button btnAttention;

    private ViewPager mViewPager;
    private Gson gson = new Gson();
    private TabLayout tabLayout;
    private int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_topic);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        changeViewByState();
    }

    private void initView() {
        setToolbar();
        btnHeckIn.setOnClickListener(this);
        btnAttention.setOnClickListener(this);
        topicPageAvatar.setOnClickListener(this);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout_topic_details);
        mViewPager = (ViewPager) findViewById(R.id.vp_topic_details);

        tabLayout.setupWithViewPager(mViewPager);
    }

    /**
     * 未登录状态显示专区图片和信息
     */
    private void changeViewByState() {
        if (!AppHelper.getInstance().isLogined()) {
            tvTopicDetailsLevel.setVisibility(View.GONE);
            pbTopicDetailsLevel.setVisibility(View.GONE);
            Intent intent = getIntent();
            if (intent != null) {
                String title = intent.getStringExtra(I.Type.TYPE_NAME);
                topicDetailsNick.setText(title);
                int imgId = intent.getIntExtra(I.Img.IMG_KEY, 0);
                Glide.with(DetailsTopicActivity.this).load(imgId).into(topicPageAvatar);
            }
        } else {
            tvTopicDetailsLevel.setVisibility(View.VISIBLE);
            pbTopicDetailsLevel.setVisibility(View.VISIBLE);
            // set Avator
            Glide.with(DetailsTopicActivity.this).load(R.drawable.avatar_boy).into(topicPageAvatar);
            topicDetailsNick.setText(AppHelper.getInstance().getCurrentUserName());
        }
    }

    private void initData() {
        String partId = getIntent().getStringExtra("partId");//获取大区 id

        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> topicRequest = NoHttp.createStringRequest(I.AREA_SUB, RequestMethod.POST);
        topicRequest.add("partId", partId);

        getDetailsTopicData(requestQueue, topicRequest);//帖子内容
    }

    private void getDetailsTopicData(RequestQueue requestQueue, Request<String> request) {
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();//得到请求数据
                if (json != null) {
                    Type type = new TypeToken<List<WordTopicTitleBean>>() {
                    }.getType();
                    List<WordTopicTitleBean> titleBeanList = gson.fromJson(json, type);
                    setViewPager(titleBeanList);
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

    private void setViewPager(final List<WordTopicTitleBean> wordTopicList) {
        //设置标题个数和值以及 fragment 的对应个数
        String[] areaArr = new String[wordTopicList.size()];

        for (int i = 0; i < wordTopicList.size(); i++) {
            areaArr[i] = wordTopicList.get(i).getPART();//获取专区名
        }

        List<Fragment> mFragmentList = new ArrayList<>();
        for (int i = 0; i < wordTopicList.size(); i++) {
            mFragmentList.add(DetailsTopicInfoFragment.newInstance(wordTopicList.get(i).getID()));//对应专区添加布局
        }

        //viewpager的滑动监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {//当前界面0
                // TODO: 2017/9/27 问题：在改变一级 TabLayout 时，二级 TabLayout 随之改变
                String id = wordTopicList.get(arg0).getID();
                Toast.makeText(DetailsTopicActivity.this, "id:" + id, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });


        TabViewPagerAdapter mAdapter = new TabViewPagerAdapter(getSupportFragmentManager(), this, mFragmentList, areaArr);
        mViewPager.setAdapter(mAdapter);
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
                if (!(AppHelper.getInstance().isLogined())) {
                    Intent intent = new Intent(this, LoginActivity.class);
                    intent.putExtra("loginId", "loginHeck");
                    startActivity(intent);
                } else {
                    if (flag == 0) {
                        btnHeckIn.setText("已签到");
                        btnHeckIn.setBackgroundResource(R.drawable.shape_yuan_trans);
                    } else if (flag == 1) {
                        btnHeckIn.setText("签到");
                        btnHeckIn.setBackgroundResource(R.drawable.shape_yuan);
                    }
                    flag = (flag + 1) % 2;
                }
                break;
            case R.id.btn_topic_attention://关注
                if (!(AppHelper.getInstance().isLogined())) {
                    Intent intent = new Intent(this, LoginActivity.class);
                    intent.putExtra("loginId", "loginAttention");
                    startActivity(intent);
                } else {
                    if (flag == 0) {
                        btnAttention.setText("已关注");
                        btnAttention.setBackgroundResource(R.drawable.shape_yuan_trans);
                    } else if (flag == 1) {
                        btnAttention.setText("关注");
                        btnAttention.setBackgroundResource(R.drawable.shape_yuan);
                    }
                    flag = (flag + 1) % 2;
                }
                break;
            case R.id.topic_page_avatar:
                startActivity(new Intent(this, MinePageOtherActivity.class));
                break;
        }
    }

}
