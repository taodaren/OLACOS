package net.osplay.ui.activity.sub;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.zxing.common.StringUtils;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import net.osplay.app.AppHelper;
import net.osplay.app.I;
import net.osplay.olacos.R;
import net.osplay.service.entity.WordDetailsCommentBean;
import net.osplay.service.entity.WordDetailsPostsBean;
import net.osplay.ui.activity.base.BaseActivity;
import net.osplay.ui.adapter.DetailsPostsCommentAdapter;
import net.osplay.ui.adapter.DetailsPostsContentAdapter;
import net.osplay.utils.Uuid;

import java.lang.reflect.Type;
import java.util.List;

/**
 * 热帖某一热帖详情
 */

public class DetailsPostsActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "DetailsPostsActivity";
    private int flag;
    private LinearLayout mllShow, mllHide;
    private ImageView mImgSugar, mImgCollect, mAvatar;
    private Button mBtnAttention, btn_details_posts_send;
    private TextView mTvNick, mTvTime, mTvType, mTvTitle;
    private NestedScrollView details_layout_nsv;
    private LinearLayout details_layout_ll;

    private RecyclerView mRvContent;
    private ExpandableListView mElvComment;
    private Gson gson = new Gson();
    private List<WordDetailsPostsBean> mContentList;
    private WordDetailsCommentBean mCommentBean;
    private String postsId;

    private EditText tv_details_posts_ed;
    private RequestQueue requestQueue;
    private Request<String> comRequest;
    private String memberId;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                details_layout_nsv.scrollTo(0, details_layout_ll.getMeasuredHeight() - details_layout_nsv.getHeight());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_posts);
        initData();
        initView();
    }

    private void initData() {
        postsId = getIntent().getStringExtra(I.Posts.POSTS_ID);
        memberId = AppHelper.getInstance().getUser().getID();
        requestQueue = NoHttp.newRequestQueue();

        Request<String> request = NoHttp.createStringRequest(I.POSTS_DETAIL, RequestMethod.POST);
        request.add("id", postsId);//帖子ID，只用帖子ID即可，json 数据中没有用户 ID

        comRequest = NoHttp.createStringRequest(I.QUERY_COMMENT, RequestMethod.POST);
        comRequest.add("topicId", postsId);
        comRequest.add("page", 1);
        comRequest.add("rows", Integer.MAX_VALUE);
        comRequest.add("twoNum", 3);
        comRequest.add("memberId", memberId);

        getContentData(requestQueue, request);//帖子内容
        getCommentData(requestQueue, comRequest);//评论
    }

    private void getContentData(RequestQueue requestQueue, Request<String> request) {
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();//得到请求数据
                Log.d(TAG, "onSucceed: 帖子详情 json 数据====================" + json);

                Type type = new TypeToken<List<WordDetailsPostsBean>>() {
                }.getType();
                mContentList = gson.fromJson(json, type);
                Log.d(TAG, "帖子详情解析 Succeed");
                getCommitComment(mContentList);
                initContentData();
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
                Log.d(TAG, "onSucceed: 帖子评论 json 数据====================" + json);
                mCommentBean = gson.fromJson(json, WordDetailsCommentBean.class);
                Log.d(TAG, "帖子评论解析 Succeed");
                initCommentData();
            }

            @Override
            public void onFailed(int what, Response<String> response) {
            }

            @Override
            public void onFinish(int what) {
            }
        });
    }

    private void initCommentData() {
        if (mCommentBean != null) {
            DetailsPostsCommentAdapter adapter = new DetailsPostsCommentAdapter(this, mCommentBean);
            mElvComment.setAdapter(adapter);
            mElvComment.setGroupIndicator(null);
            // 默认展开每一个分组
            for (int i = 0; i < adapter.getGroupCount(); i++) {
                mElvComment.expandGroup(i);
            }
        }
    }

    private void initContentData() {
        if (mContentList != null) {
            for (int i = 0; i < mContentList.size(); i++) {
                Glide.with(this).load(I.BASE_URL + mContentList.get(i).getHEAD_PATH()).into(mAvatar);
                mTvNick.setText(mContentList.get(i).getNICK_NAME());
                mTvTime.setText(mContentList.get(i).getCREATEDATE());
                mTvType.setText(mContentList.get(i).getPART());
                mTvTitle.setText(mContentList.get(i).getTITLE());
            }

            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            mRvContent.setLayoutManager(layoutManager);
            mRvContent.setHasFixedSize(true);
            DetailsPostsContentAdapter adapter = new DetailsPostsContentAdapter(this, mContentList);
            mRvContent.setAdapter(adapter);
        }
    }

    private void getCommitComment(final List<WordDetailsPostsBean> mContentList) {
        btn_details_posts_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tv_details_posts_ed.getText().toString().isEmpty()){
                    return;
                }else{
                    requestQueue = NoHttp.newRequestQueue();
                    Request<String> request = NoHttp.createStringRequest(I.SAVE_COMMENT, RequestMethod.POST);
                    request.add("topicId", postsId);
                    request.add("authorId", mContentList.get(0).getUSERID());
                    request.add("memberId", AppHelper.getInstance().getUser().getID());
                    request.add("beenMemberId", mContentList.get(0).getUSERID());
                    request.add("atId", Uuid.getUuid());
                    request.add("parentId", 0);
                    request.add("atIds", "");
                    request.add("atUsers", "");
                    request.add("content", tv_details_posts_ed.getText().toString());
                    requestQueue.add(0, request, new OnResponseListener<String>() {
                        @Override
                        public void onStart(int what) {
                        }

                        @Override
                        public void onSucceed(int what, Response<String> response) {
                            String json = response.get();
                            Log.e("JGB", "评论结果:" + json);
                            //阻止键盘弹出
                            InputMethodManager imm1 = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                            imm1.hideSoftInputFromWindow(tv_details_posts_ed.getWindowToken(), 0);
                            tv_details_posts_ed.setText("");
                            handler.sendEmptyMessage(1);

                            comRequest = NoHttp.createStringRequest(I.QUERY_COMMENT, RequestMethod.POST);
                            comRequest.add("topicId", postsId);
                            comRequest.add("page", 1);
                            comRequest.add("rows", Integer.MAX_VALUE);
                            comRequest.add("twoNum", 3);
                            comRequest.add("memberId", memberId);

                            requestQueue.add(0, comRequest, new OnResponseListener<String>() {
                                @Override
                                public void onStart(int what) {
                                }

                                @Override
                                public void onSucceed(int what, Response<String> response) {
                                    String json = response.get();//得到请求数据
                                    Log.d(TAG, "onSucceed: 帖子重新评论 json 数据====================" + json);
                                    mCommentBean = gson.fromJson(json, WordDetailsCommentBean.class);
                                    Log.d(TAG, "帖子重新评论解析 Succeed");
                                    initCommentData();
                                }

                                @Override
                                public void onFailed(int what, Response<String> response) {
                                }

                                @Override
                                public void onFinish(int what) {
                                }
                            });
                        }

                        @Override
                        public void onFailed(int what, Response<String> response) {
                        }

                        @Override
                        public void onFinish(int what) {
                        }
                    });
                }

            }
        });

    }

    private void initView() {
        mRvContent = (RecyclerView) findViewById(R.id.recycler_details_posts_content);
        mElvComment = (ExpandableListView) findViewById(R.id.elv_details_posts_comment);
        setToolbar();
        mllShow = (LinearLayout) findViewById(R.id.ll_details_posts_show);
        mllHide = (LinearLayout) findViewById(R.id.ll_details_posts_hide);
        mAvatar = (ImageView) findViewById(R.id.img_details_posts_avatar);
        mTvNick = (TextView) findViewById(R.id.tv_details_posts_nick);
        mTvTime = (TextView) findViewById(R.id.tv_details_posts_time);
        mTvType = (TextView) findViewById(R.id.tv_details_posts_type);
        mTvTitle = (TextView) findViewById(R.id.tv_details_posts_title);
        mImgSugar = (ImageView) findViewById(R.id.img_details_posts_sugar);
        mImgCollect = (ImageView) findViewById(R.id.img_details_posts_collect);
        mBtnAttention = (Button) findViewById(R.id.btn_details_posts_attention);
        tv_details_posts_ed = (EditText) findViewById(R.id.tv_details_posts_ed);
        btn_details_posts_send = (Button) findViewById(R.id.btn_details_posts_send);
        details_layout_nsv = (NestedScrollView) findViewById(R.id.details_layout_nsv);
        details_layout_ll = (LinearLayout) findViewById(R.id.details_layout_ll);
        mImgSugar.setOnClickListener(this);
        mImgCollect.setOnClickListener(this);
        mBtnAttention.setOnClickListener(this);
        findViewById(R.id.tv_details_posts_switch).setOnClickListener(this);
        findViewById(R.id.img_details_posts_expression).setOnClickListener(this);
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
            default:
                break;
        }
    }

}
