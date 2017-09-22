package net.osplay.ui.activity.sub;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import net.osplay.app.I;
import net.osplay.olacos.R;
import net.osplay.service.entity.MeiZiBean;
import net.osplay.ui.activity.base.BaseActivity;
import net.osplay.ui.adapter.DetailsPostsCommentAdapter;
import net.osplay.ui.adapter.DetailsPostsContentAdapter;
import net.osplay.ui.adapter.MessageAdapter;

import java.util.List;

/**
 * 热帖某一热帖详情
 */

public class DetailsPostsActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "DetailsPostsActivity";
    private LinearLayout mllShow, mllHide;
    private ImageView mImgSugar, mImgCollect;
    private Button mBtnAttention;
    private int flag;

    private RecyclerView mRvContent, mRvComment;
    private List<MeiZiBean.ResultsBean> mContentList;
    private List<MeiZiBean.ResultsBean> mCommentList;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_posts);
        initData();
        initView();
    }

    private void initData() {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> msgRequest = NoHttp.createStringRequest(I.MSG_FULI, RequestMethod.GET);
        getContentData(requestQueue, msgRequest);//帖子内容
        getCommentData(requestQueue, msgRequest);//评论
    }

    private void getContentData(RequestQueue requestQueue, Request<String> request) {
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();//得到请求数据
                Log.d(TAG, "onSucceed: 帖子详情内容 json 数据====================" + json);

                //数据解析（集合）
                MeiZiBean bean = gson.fromJson(json, MeiZiBean.class);
                mContentList = bean.getResults();
                Log.d(TAG, "onSucceed: 帖子详情内容解析结果====================" + mContentList);
                initRvContent();
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }

            @Override
            public void onFinish(int what) {

            }
        });
    }

    private void getCommentData(RequestQueue requestQueue, Request<String> request) {
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();//得到请求数据
                Log.d(TAG, "onSucceed: 帖子详情评论 json 数据====================" + json);

                //数据解析（集合）
                MeiZiBean bean = gson.fromJson(json, MeiZiBean.class);
                mCommentList = bean.getResults();
                Log.d(TAG, "onSucceed: 帖子详情评论解析结果====================" + mCommentList);
                initRvComment();
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }

            @Override
            public void onFinish(int what) {

            }
        });
    }

    private void initRvComment() {
        if (mCommentList != null) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            mRvComment.setLayoutManager(layoutManager);
            mRvComment.setHasFixedSize(true);
            DetailsPostsCommentAdapter adapter = new DetailsPostsCommentAdapter(this, mCommentList);
            mRvContent.setAdapter(adapter);
        }
    }

    private void initRvContent() {
        if (mContentList != null) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            mRvContent.setLayoutManager(layoutManager);
            mRvContent.setHasFixedSize(true);
            DetailsPostsContentAdapter adapter = new DetailsPostsContentAdapter(this, mContentList);
            mRvContent.setAdapter(adapter);
        }
    }

    private void initView() {
        mRvContent = (RecyclerView) findViewById(R.id.recycler_details_posts_content);
        mRvComment = (RecyclerView) findViewById(R.id.recycler_details_posts_comment);
        setToolbar();
        mllShow = (LinearLayout) findViewById(R.id.ll_details_posts_show);
        mllHide = (LinearLayout) findViewById(R.id.ll_details_posts_hide);
        mImgSugar = (ImageView) findViewById(R.id.img_details_posts_sugar);
        mImgCollect = (ImageView) findViewById(R.id.img_details_posts_collect);
        mBtnAttention = (Button) findViewById(R.id.btn_details_posts_attention);
        mImgSugar.setOnClickListener(this);
        mImgCollect.setOnClickListener(this);
        mBtnAttention.setOnClickListener(this);
        findViewById(R.id.tv_details_posts_switch).setOnClickListener(this);
        findViewById(R.id.img_details_posts_expression).setOnClickListener(this);
        findViewById(R.id.btn_details_posts_send).setOnClickListener(this);
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_posts_details);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            //隐藏 Toolbar 自带标题栏
//            actionBar.setDisplayShowTitleEnabled(false);
            //设置后退导航按钮图标
            actionBar.setHomeAsUpIndicator(R.drawable.title_back);
            actionBar.setTitle("帖子详情");
        }
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
            case R.id.btn_details_posts_attention://关注
                if (flag == 0) {
                    mBtnAttention.setText("已关注");
                    mBtnAttention.setBackgroundResource(R.drawable.shape_yuan_trans);
                } else if (flag == 1) {
                    mBtnAttention.setText("关注");
                    mBtnAttention.setBackgroundResource(R.drawable.shape_yuan);
                }
                flag = (flag + 1) % 2;
                break;
            case R.id.tv_details_posts_switch://切换
                mllShow.setVisibility(View.GONE);
                mllHide.setVisibility(View.VISIBLE);
                break;
            case R.id.img_details_posts_sugar://糖果
                if (flag == 0) {
                    mImgSugar.setImageResource(R.drawable.ic_sugar_selected);
                } else if (flag == 1) {
                    mImgSugar.setImageResource(R.drawable.ic_sugar_unselected);
                }
                flag = (flag + 1) % 2;
                break;
            case R.id.img_details_posts_collect://收藏
                if (flag == 0) {
                    mImgCollect.setImageResource(R.drawable.ic_collect_selected);
                } else if (flag == 1) {
                    mImgCollect.setImageResource(R.drawable.ic_collect_unselected);
                }
                flag = (flag + 1) % 2;
                break;
            case R.id.img_details_posts_expression://表情
                break;
            case R.id.btn_details_posts_send://发送
                break;
            default:
                break;
        }
    }

}
