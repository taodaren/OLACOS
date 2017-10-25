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
import net.osplay.service.entity.IsFollowBean;
import net.osplay.service.entity.WordDetailsCommentBean;
import net.osplay.service.entity.WordDetailsPostsBean;
import net.osplay.ui.activity.base.BaseActivity;
import net.osplay.ui.adapter.DetailsPostsCommentAdapter;
import net.osplay.ui.adapter.DetailsPostsContentAdapter;
import net.osplay.utils.Uuid;

import java.lang.reflect.Type;
import java.util.List;

/**
 * 帖子详情
 */

public class DetailsPostsActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "DetailsPostsActivity";
    private static final String ACTION_ATTENTION = "0";
    private static final String ACTION_ATTENTION_UN = "1";
    private static final int FOCUS_DOWN = 1;//置底

    private int flag;
    private NestedScrollView mNsv;
    private LinearLayout mllShow, mllHide, mllAll;
    private TextView mTvNick, mTvTime, mTvType, mTvTitle, mTvSwitch;
    private EditText mEdEnter;
    private ImageView mImgSugar, mImgCollect, mAvatar;
    private Button mBtnAttention, mBtnSend;
    private RecyclerView mRvContent;
    private ExpandableListView mElvComment;

    private Gson gson = new Gson();
    private WordDetailsCommentBean mCommentBean;
    private IsFollowBean mIsFollowBean;
    private List<WordDetailsPostsBean> mContentList;
    private List<WordDetailsCommentBean.OneBean> mOneList;
    private List<WordDetailsCommentBean.TwoBean> mTwoList;

    private String postsId, memberId, userId;
    private RequestQueue requestQueue;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == FOCUS_DOWN) {
                mNsv.scrollTo(0, mllAll.getMeasuredHeight() - mNsv.getHeight());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_posts);
        initView();
    }

    private void initView() {
        setToolbar();
        mRvContent = (RecyclerView) findViewById(R.id.recycler_details_posts_content);
        mElvComment = (ExpandableListView) findViewById(R.id.elv_details_posts_comment);
        mNsv = (NestedScrollView) findViewById(R.id.nsv_details_posts);
        mllAll = (LinearLayout) findViewById(R.id.ll_details_posts_all);
        mllShow = (LinearLayout) findViewById(R.id.ll_details_posts_show);
        mllHide = (LinearLayout) findViewById(R.id.ll_details_posts_hide);
        mTvNick = (TextView) findViewById(R.id.tv_details_posts_nick);
        mTvTime = (TextView) findViewById(R.id.tv_details_posts_time);
        mTvType = (TextView) findViewById(R.id.tv_details_posts_type);
        mTvTitle = (TextView) findViewById(R.id.tv_details_posts_title);
        mTvSwitch = (TextView) findViewById(R.id.tv_details_posts_switch);
        mEdEnter = (EditText) findViewById(R.id.etv_details_posts_ed);
        mAvatar = (ImageView) findViewById(R.id.img_details_posts_avatar);
        mImgSugar = (ImageView) findViewById(R.id.img_details_posts_sugar);
        mImgCollect = (ImageView) findViewById(R.id.img_details_posts_collect);
        mBtnAttention = (Button) findViewById(R.id.btn_details_posts_attention);
        mBtnSend = (Button) findViewById(R.id.btn_details_posts_send);
        mImgSugar.setOnClickListener(this);
        mImgCollect.setOnClickListener(this);
        mBtnAttention.setOnClickListener(this);
        findViewById(R.id.img_details_posts_expression).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        changeViewByState();
        postsId = getIntent().getStringExtra(I.Posts.POSTS_ID);//帖子ID
        requestQueue = NoHttp.newRequestQueue();
        getContentData();
    }

    private void changeViewByState() {
        if (!AppHelper.getInstance().isLogined()) {//未登录状态
            memberId = "";
        } else {//登录状态
            memberId = AppHelper.getInstance().getUser().getID();//登录用户ID
        }
    }

    /**
     * 获取帖子内容数据
     */
    private void getContentData() {
        Request<String> request = NoHttp.createStringRequest(I.POSTS_DETAIL, RequestMethod.POST);
        request.add("id", postsId);//帖子ID，只用帖子ID即可，json 数据中没有用户 ID

        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();//得到请求数据
                Log.d(TAG, "帖子详情数据请求成功，json 数据是：" + json);

                Type type = new TypeToken<List<WordDetailsPostsBean>>() {
                }.getType();
                mContentList = gson.fromJson(json, type);
                Log.d(TAG, "帖子详情解析成功");

                getCommentData();//获取一级评论及一级评论下默认显示的二级评论数据
                commitCommentData();//提交评论数据
                getIsFollowData();//获取是否关注用户数据
                initDetailsPosts();//初始化帖子详情
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
     * 获取是否关注用户数据
     */
    private void getIsFollowData() {
        userId = mContentList.get(0).getUSERID();
        Request<String> request = NoHttp.createStringRequest(I.IS_FOLLOW, RequestMethod.POST);
        request.add("followId", userId);//被关注人id
        request.add("memberId", memberId);

        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.d(TAG, "是否关注用户数据请求成功，json 数据是：" + json);

                mIsFollowBean = gson.fromJson(json, IsFollowBean.class);
                Log.d(TAG, "是否关注用户数据解析成功");

                //如果已关注，显示关注状态
                if ("true".equals(mIsFollowBean.getOk())) {
                    mBtnAttention.setBackgroundResource(R.drawable.shape_yuan_trans);
                    mBtnAttention.setText("已关注");
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
     * 获取一级评论及一级评论下默认显示的二级评论数据
     */
    private void getCommentData() {
        Request<String> request = NoHttp.createStringRequest(I.QUERY_COMMENT, RequestMethod.POST);
        request.add("topicId", postsId);
        request.add("page", 1);
        request.add("rows", Integer.MAX_VALUE);
        request.add("twoNum", Integer.MAX_VALUE);
        request.add("memberId", memberId);

        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();//得到请求数据
                Log.d(TAG, "帖子评论数据请求成功，json 数据是：" + json);

                mCommentBean = gson.fromJson(json, WordDetailsCommentBean.class);
                mOneList = mCommentBean.getOne();
                mTwoList = mCommentBean.getTwo();
                Log.d(TAG, "帖子评论解析成功");

                initCommentPosts();//初始化帖子评论
                getCommentMoreData();//获取多级评论数据
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
     * 重新获取评论数据，到达指定位置
     *
     * @param i 位置
     */
    private void getCommentData(final int i) {
        Request<String> comRequest = NoHttp.createStringRequest(I.QUERY_COMMENT, RequestMethod.POST);
        comRequest.add("topicId", postsId);
        comRequest.add("page", 1);
        comRequest.add("rows", Integer.MAX_VALUE);
        comRequest.add("twoNum", Integer.MAX_VALUE);
        comRequest.add("memberId", memberId);

        requestQueue.add(0, comRequest, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();//得到请求数据
                Log.d(TAG, "帖子重新评论数据请求成功，json 数据是：" + json);

                mCommentBean = gson.fromJson(json, WordDetailsCommentBean.class);
                Log.d(TAG, "帖子重新评论解析成功");

                initCommentPosts();//初始化帖子评论

                //置底
                if (i == FOCUS_DOWN) {
                    handler.sendEmptyMessage(FOCUS_DOWN);
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
     * 提交评论数据
     */
    private void commitCommentData() {
        mTvSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//点击底部 TextView 切换布局
                if (AppHelper.getInstance().isLogined()) {
                    //弹出软键盘
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    mEdEnter.requestFocus();
                    //切换布局
                    mllShow.setVisibility(View.GONE);
                    mllHide.setVisibility(View.VISIBLE);
                    //点击发送按钮
                    mBtnSend.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!mEdEnter.getText().toString().isEmpty()) {
                                //保存评论数据
                                saveCommentData();
                            }
                        }
                    });
                } else {
                    MFGT.gotoLogin(DetailsPostsActivity.this, "sendComment");
                }
            }
        });
    }

    /**
     * 保存评论数据
     */
    private void saveCommentData() {
        Request<String> request = NoHttp.createStringRequest(I.SAVE_COMMENT, RequestMethod.POST);
        request.add("topicId", postsId);
        request.add("authorId", mContentList.get(0).getUSERID());//帖子作者ID
        request.add("memberId", memberId);
        request.add("beenMemberId", mContentList.get(0).getUSERID());
        request.add("atId", Uuid.getUuid());
        request.add("parentId", 0);
        request.add("atIds", "");
        request.add("atUsers", "");
        request.add("content", mEdEnter.getText().toString());

        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.e(TAG, "保存评论数据请求成功，json 数据是：" + json);
                //阻止键盘弹出（关闭软键盘）
                InputMethodManager imm1 = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm1.hideSoftInputFromWindow(mEdEnter.getWindowToken(), 0);
                //清空输入内容，切换布局
                mEdEnter.getText().clear();
                mllShow.setVisibility(View.VISIBLE);
                mllHide.setVisibility(View.GONE);
                //刷新评论数据
                getCommentData(FOCUS_DOWN);
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
     * 获取多级评论数据
     */
    private void getCommentMoreData() {
        //二级评论
        mElvComment.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, final int groupPosition, long id) {
                //弹出软键盘
                ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                mEdEnter.requestFocus();
                //切换布局
                mllShow.setVisibility(View.GONE);
                mllHide.setVisibility(View.VISIBLE);
                //发送评论内容
                mBtnSend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Request<String> request = NoHttp.createStringRequest(I.SAVE_COMMENT, RequestMethod.POST);
                        request.add("topicId", postsId);
                        request.add("authorId", mContentList.get(0).getUSERID());
                        request.add("memberId", memberId);
                        request.add("beenMemberId", mOneList.get(groupPosition).getMEMBERID());
                        request.add("atId", Uuid.getUuid());
                        request.add("parentId", mOneList.get(groupPosition).getID());
                        request.add("atIds", "");
                        request.add("atUsers", "");
                        request.add("content", mEdEnter.getText().toString());

                        requestQueue.add(0, request, new OnResponseListener<String>() {
                            @Override
                            public void onStart(int what) {
                            }

                            @Override
                            public void onSucceed(int what, Response<String> response) {
                                String json = response.get();
                                Log.e(TAG, "保存评论数据请求成功，json 数据是：" + json);
                                //阻止键盘弹出
                                InputMethodManager imm1 = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                                imm1.hideSoftInputFromWindow(mEdEnter.getWindowToken(), 0);
                                //清空输入内容，切换布局
                                mEdEnter.getText().clear();
                                mllShow.setVisibility(View.VISIBLE);
                                mllHide.setVisibility(View.GONE);
                                //刷新评论数据
                                getCommentData(2);
                            }

                            @Override
                            public void onFailed(int what, Response<String> response) {
                            }

                            @Override
                            public void onFinish(int what) {
                            }
                        });
                    }
                });
                return false;
            }
        });

        //子选项的点击事件
        mElvComment.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //弹出软键盘
                ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                mEdEnter.requestFocus();
                return false;
            }
        });
    }

    /**
     * 初始化帖子评论
     */
    private void initCommentPosts() {
        if (mCommentBean != null) {
            DetailsPostsCommentAdapter adapter = new DetailsPostsCommentAdapter(this, mCommentBean);
            mElvComment.setAdapter(adapter);
            mElvComment.setGroupIndicator(null);
            //默认展开每一个分组
            for (int i = 0; i < adapter.getGroupCount(); i++) {
                mElvComment.expandGroup(i);
            }
        }
    }

    /**
     * 初始化帖子详情
     */
    private void initDetailsPosts() {
        if (mContentList != null) {
            if (mContentList.get(0).getHEAD_PATH() != null) {//如果用户设置头像则显示
                Glide.with(this).load(I.BASE_URL + mContentList.get(0).getHEAD_PATH()).into(mAvatar);
            } else {//如果没设置，显示默认头像
                Glide.with(this).load(R.drawable.avatar_default).into(mAvatar);
            }
            mTvNick.setText(mContentList.get(0).getNICK_NAME());
            mTvTime.setText(mContentList.get(0).getCREATEDATE());
            mTvType.setText(mContentList.get(0).getPART());
            mTvTitle.setText(mContentList.get(0).getTITLE());

            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            mRvContent.setLayoutManager(layoutManager);
            mRvContent.setHasFixedSize(true);
            DetailsPostsContentAdapter adapter = new DetailsPostsContentAdapter(this, mContentList);
            mRvContent.setAdapter(adapter);
        }
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
                if (!(AppHelper.getInstance().isLogined())) {
                    MFGT.gotoLogin(DetailsPostsActivity.this, "loginAttention");
                } else {
                    actionAttention();
                }
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

    /**
     * 关注功能
     */
    private void actionAttention() {
        final String action;
        if ("true".equals(mIsFollowBean.getOk())) {
            action = ACTION_ATTENTION_UN;//取消关注
        } else {
            action = ACTION_ATTENTION;//关注
        }
        Request<String> request = NoHttp.createStringRequest(I.FOLLOW, RequestMethod.POST);
        request.add("memberId", memberId);
        request.add("followId", userId);
        request.add("mark", action);

        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.e(TAG, "关注数据请求成功，json 数据是：" + json);

                mIsFollowBean = gson.fromJson(json, IsFollowBean.class);
                if ("true".equals(mIsFollowBean.getOk())) {
                    switch (action) {
                        case ACTION_ATTENTION://关注成功
                            mIsFollowBean.setOk("true");//重置用户对当前帖子的关注状态
                            mBtnAttention.setBackgroundResource(R.drawable.shape_yuan_trans);
                            mBtnAttention.setText("已关注");
                            break;
                        case ACTION_ATTENTION_UN://取消关注
                            mIsFollowBean.setOk("false");//重置用户对当前帖子的关注状态
                            mBtnAttention.setBackgroundResource(R.drawable.shape_yuan);
                            mBtnAttention.setText("关注");
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

}
