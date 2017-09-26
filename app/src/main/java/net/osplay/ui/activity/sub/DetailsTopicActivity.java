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
import android.view.View;
import android.widget.Toast;

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
import net.osplay.service.entity.WordTopicTitleBean;
import net.osplay.ui.activity.base.BaseActivity;
import net.osplay.ui.adapter.TabViewPagerAdapter;
import net.osplay.ui.fragment.sub.DetailsTopicInfoFragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 专题某一分区详情
 */

public class DetailsTopicActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "DetailsTopicActivity";
    private ViewPager mViewPager;
    private List<WordTopicTitleBean> titleBeanList;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_topic);
        initData();
        initView();
    }

    private void initView() {
        findViewById(R.id.btn_topic_heck_in).setOnClickListener(this);
        findViewById(R.id.btn_topic_attention).setOnClickListener(this);
        findViewById(R.id.topic_page_avatar).setOnClickListener(this);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout_topic_details);
        mViewPager = (ViewPager) findViewById(R.id.vp_topic_details);

        setToolbar();
        tabLayout.setupWithViewPager(mViewPager);
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
                Log.d(TAG, "onSucceed: 专区分区标题数据请求====================" + json);

                //数据解析（集合）
                if (json != null) {
                    Type type = new TypeToken<List<WordTopicTitleBean>>() {
                    }.getType();
                    titleBeanList = gson.fromJson(json, type);
                    Log.d(TAG, "onSucceed: 专区分区标题解析结果====================" + titleBeanList);
                } else {//为了不崩溃
                    return;
                }

                setViewPager(titleBeanList);
            }

            @Override
            public void onFailed(int what, Response<String> response) {
            }

            @Override
            public void onFinish(int what) {
            }
        });
    }

    private void setViewPager(List<WordTopicTitleBean> titleBeanList) {
        //设置标题个数和值以及 fragment 的对应个数
        String[] arr = new String[titleBeanList.size()];
        for (int i = 0; i < titleBeanList.size(); i++) {
            arr[i] = titleBeanList.get(i).getPART();
        }

        List<Fragment> mFragmentList = new ArrayList<>();
        for (int i = 0; i < titleBeanList.size(); i++) {
            mFragmentList.add(new DetailsTopicInfoFragment(this, R.layout.layout_word_hot_posts));
        }
        TabViewPagerAdapter mAdapter = new TabViewPagerAdapter(getSupportFragmentManager(), this, mFragmentList, arr);
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
