package net.osplay.ui.activity.sub;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import net.osplay.app.AppHelper;
import net.osplay.app.I;
import net.osplay.app.MFGT;
import net.osplay.olacos.R;
import net.osplay.service.entity.CheckInfoBean;
import net.osplay.service.entity.IsAttentionBean;
import net.osplay.service.entity.IsCheckBean;
import net.osplay.service.entity.WordTopicTitleBean;
import net.osplay.ui.activity.base.BaseActivity;
import net.osplay.ui.adapter.TabViewPagerAdapter;
import net.osplay.ui.fragment.sub.DetailsTopicInfoFragment;
import net.osplay.utils.SharedPreferencesUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 专题某一分区详情
 */

public class DetailsTopicActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "DetailsTopicActivity";
    @BindView(R.id.topic_details_nick)
    TextView tvNick;
    @BindView(R.id.tv_topic_details_level)
    TextView tvLevel;
    @BindView(R.id.pb_topic_details_level)
    ProgressBar progressBar;
    @BindView(R.id.topic_page_avatar)
    CircleImageView imgAvatar;
    @BindView(R.id.btn_topic_heck_in)
    Button btnHeckIn;
    @BindView(R.id.btn_topic_attention)
    Button btnAttention;

    private Unbinder unbinder;
    private int flag;
    private ViewPager mViewPager;
    private String memberId, partId;
    private RequestQueue mRequestQueue;
    private Gson gson = new Gson();
    private IsCheckBean mIsCheckBean;
    private IsCheckBean mCheckInBean;
    private List<CheckInfoBean> mCheckInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_topic);
        unbinder = ButterKnife.bind(this);
        initData();
        initView();
        getIsJoinData();
    }

    private void initData() {
        memberId = AppHelper.getInstance().getUserID();
        partId = getIntent().getStringExtra("partId");//获取大区 id
        Log.i(TAG, "initData: memberId==" + memberId);
        Log.i(TAG, "initData: partId==" + partId);

        mRequestQueue = NoHttp.newRequestQueue();
        getDetailsTopicData();//请求专区数据
        getCheckInfoData();//获取用户当前的签到经验等级
        getIsCheckInData();//今天是否签到过
    }

    private void initView() {
        setToolbar();
        initFabButton();

        btnHeckIn.setOnClickListener(this);
        btnAttention.setOnClickListener(this);
        imgAvatar.setOnClickListener(this);

        TabLayout tabLayout = findViewById(R.id.tab_layout_topic_details);
        mViewPager = findViewById(R.id.vp_topic_details);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        changeViewByState();
    }

    /**
     * 通过登录状态改变 View
     */
    private void changeViewByState() {
        if (!AppHelper.getInstance().isLogined()) {//未登录状态
            tvLevel.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            Intent intent = getIntent();
            if (intent != null) {
                String title = intent.getStringExtra(I.Type.TYPE_NAME);
                String strAvatarId = intent.getStringExtra(I.Img.IMG_KEY);
//                int imgId = intent.getIntExtra(I.Img.IMG_KEY, 0);//传过来模拟数据
                tvNick.setText(title);
                Glide.with(DetailsTopicActivity.this).load(strAvatarId).into(imgAvatar);
            }
        } else {//登录状态
            tvLevel.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            // set Avatar and Nick
//            Glide.with(DetailsTopicActivity.this).load(I.BASE_URL + AppHelper.getInstance().getUser().getHEAD_PATH()).into(imgAvatar);
            String HEAD_PATH = (String) SharedPreferencesUtils.getParam(DetailsTopicActivity.this, "HEAD_PATH", "");//获取头像
            if (HEAD_PATH.equals("")) {
                Picasso.with(this).load(I.BASE_URL + AppHelper.getInstance().getUser().getHEAD_PATH()).error(R.drawable.avatar_default).into(imgAvatar);
                if(AppHelper.getInstance().getUser().getHEAD_PATH()==null){
                    Picasso.with(this).load((R.drawable.avatar_default)).into(imgAvatar);
                }
            } else {
                Picasso.with(this).load(I.BASE_URL +HEAD_PATH).error(R.drawable.avatar_default).into(imgAvatar);
            }
            tvNick.setText(AppHelper.getInstance().getUser().getNICK_NAME());

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.topic_page_avatar:
                startActivity(new Intent(this, MinePageSelfActivity.class));
                break;
            case R.id.btn_topic_heck_in://签到
                if (!(AppHelper.getInstance().isLogined())) {
                    Toast.makeText(this, "请先加入专区", Toast.LENGTH_SHORT).show();
                } else {
                    if ("false".equals(mIsCheckBean.getOk())) {
                        btnHeckIn.setText("已签到");
                        btnHeckIn.setBackgroundResource(R.drawable.shape_yuan_trans);
                        getCheckInData();//获取签到数据
                    } else {
                        Toast.makeText(this, "今天签完啦，明天再来呦~", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.btn_topic_attention://加入/退出专区
                if (!(AppHelper.getInstance().isLogined())) {
                    MFGT.gotoLogin(this, "loginAttention");
                } else {
                    CharSequence text = btnAttention.getText();
                    if (text.equals("已加入")) {
                        getJoinOrExitData(1);//加入/退出专区数据请求解析
                    } else if (text.equals("加入专区")) {
                        getJoinOrExitData(0);
                    }
                    flag = (flag + 1) % 2;
                }
                break;
        }
    }

    /**
     * 获取用户当前的签到经验等级数据
     */
    private void getCheckInfoData() {
        Request<String> request = NoHttp.createStringRequest(I.GET_CHECK_INFO, RequestMethod.POST);
        request.add("memberId", memberId);
        request.add("departId", partId);
        mRequestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @SuppressLint({"SetTextI18n", "CommitPrefEdits"})
            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.d(TAG, "获取当前签到用户的等级数据请求成功，json 数据是：" + json);

                Type type = new TypeToken<List<CheckInfoBean>>() {
                }.getType();
                mCheckInfoList = gson.fromJson(json, type);
                Log.d(TAG, "获取当前签到用户的等级数据解析成功");

                //如果当前用户为登录状态且获取当前签到用户的数据不为空，显示当前数据
                if (AppHelper.getInstance().isLogined() && mCheckInfoList != null) {
                    tvLevel.setText("我的等级 LV " + mCheckInfoList.get(0).getMEMBER_RANK());//当前等级
                    progressBar.setMax(Integer.parseInt(mCheckInfoList.get(0).getNEXTPOINT()));//设置经验最大值
                    progressBar.setProgress(Integer.parseInt(mCheckInfoList.get(0).getFENSHU()));//当前经验
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

    /**
     * 获取签到数据
     */
    private void getCheckInData() {
        Request<String> request = NoHttp.createStringRequest(I.CHECK_IN, RequestMethod.POST);
        request.add("memberId", memberId);
        request.add("partId", partId);
        mRequestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @SuppressLint({"SetTextI18n", "CommitPrefEdits"})
            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.d(TAG, "获取签到后用户数据请求成功，json 数据是：" + json);

                mCheckInBean = gson.fromJson(json, IsCheckBean.class);
                Log.d(TAG, "获取签到后用户数据解析成功");

                getCheckInfoData();//获取用户当前的签到经验等级
            }

            @Override
            public void onFailed(int what, Response<String> response) {
            }

            @Override
            public void onFinish(int what) {
            }
        });
    }

    /**
     * 获取今天是否签到过数据
     */
    private void getIsCheckInData() {
        Request<String> request = NoHttp.createStringRequest(I.IS_CHECK_IN, RequestMethod.POST);
        request.add("memberId", memberId);
        request.add("departId", partId);
        mRequestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.d(TAG, "今天是否签到过数据请求成功，json 数据是：" + json);

                mIsCheckBean = gson.fromJson(json, IsCheckBean.class);
                Log.d(TAG, "今天是否签到过数据解析成功");

                //如果已签到，显示签到状态
                if ("true".equals(mIsCheckBean.getOk())) {
                    btnHeckIn.setBackgroundResource(R.drawable.shape_yuan_trans);
                    btnHeckIn.setText("已签到");
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

    /**
     * 获取请求专区数据
     */
    private void getDetailsTopicData() {
        Request<String> request = NoHttp.createStringRequest(I.AREA_SUB, RequestMethod.POST);
        request.add("partId", partId);
        mRequestQueue.add(0, request, new OnResponseListener<String>() {
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

            private void setViewPager(List<WordTopicTitleBean> wordTopicList) {
                //设置标题个数和值以及 fragment 的对应个数
                String[] areaArr = new String[wordTopicList.size()];

                for (int i = 0; i < wordTopicList.size(); i++) {
                    areaArr[i] = wordTopicList.get(i).getPART();//获取专区名
                }

                List<Fragment> mFragmentList = new ArrayList<>();
                for (int i = 0; i < wordTopicList.size(); i++) {
                    mFragmentList.add(DetailsTopicInfoFragment.newInstance(wordTopicList.get(i).getID()));//对应专区添加布局
                }

                TabViewPagerAdapter adapter = new TabViewPagerAdapter(getSupportFragmentManager(), DetailsTopicActivity.this, mFragmentList, areaArr);
                mViewPager.setAdapter(adapter);
            }
        });
    }

    /**
     * 验证专区是否加入过
     */
    public void getIsJoinData() {
        Request<String> request = NoHttp.createStringRequest(I.IS_JOIN_AREA, RequestMethod.POST);
        request.add("memberId", memberId);
        request.add("deparId", partId);
        mRequestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                if (json != null) {
                    IsAttentionBean bean = gson.fromJson(json,
                            IsAttentionBean.class);
                    String code = bean.getCode();
                    if (code.equals("true")) {
                        btnAttention.setText("已加入");
                        btnAttention.setBackgroundResource(R.drawable.shape_yuan_trans);
                    } else {
                        btnAttention.setText("加入专区");
                        btnAttention.setBackgroundResource(R.drawable.shape_yuan);
                    }
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

    /**
     * 加入/退出专区
     */
    private void getJoinOrExitData(final int mark) {
        Request<String> request = NoHttp.createStringRequest(I.JOIN_OR_EXIT_AREA, RequestMethod.POST);
        request.add("memberId", memberId);
        request.add("myarrondiId", partId);
        request.add("mark", mark);
        mRequestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                if (json != null) {
                    switch (mark) {
                        case 0:
                            btnAttention.setText("已加入");
                            btnAttention.setBackgroundResource(R.drawable.shape_yuan_trans);
                            break;
                        case 1:
                            btnAttention.setText("加入专区");
                            btnAttention.setBackgroundResource(R.drawable.shape_yuan);
                            break;
                    }
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

    /**
     * 设置悬浮按钮
     */
    private void initFabButton() {
        FloatingActionButton fab =  findViewById(R.id.fab_layout_topic_details);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AppHelper.getInstance().isLogined()) {//如果是登录状态
                    startActivity(new Intent(DetailsTopicActivity.this, PublishPostsActivity.class));
                } else {
                    MFGT.gotoLogin(DetailsTopicActivity.this, "sendPosts");
                }
            }
        });
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_topic_page);
        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar_topic);

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
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
