package net.osplay.ui.activity.sub;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import net.osplay.app.SetOnClickListen;
import net.osplay.data.bean.ActionResultBean;
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
import java.util.Objects;

/**
 * 帖子详情
 */

public class DetailsPostsActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "DetailsPostsActivity";
    private static final String ACTION_ATTENTION = "0";
    private static final String ACTION_ATTENTION_UN = "1";

    private static final int FOCUS_DOWN = 1;//置底

    private NestedScrollView mNsv;
    private LinearLayout mllShow, mllHide, mllAll;
    private TextView mTvNick, mTvTime, mTvType, mTvTitle, mTvSwitch;
    private EditText mEdEnter;
    private ImageView mImgSugar, mImgCollect, mAvatar;
    private Button mBtnAttention, mBtnSend;
    private RecyclerView mRvContent;
    private ExpandableListView mElvComment;
    private DetailsPostsCommentAdapter mCommentAdapter;

    private Gson gson = new Gson();
    private WordDetailsCommentBean mCommentBean;
    private IsFollowBean mIsFollowBean;
    private List<WordDetailsPostsBean> mContentList;
    private List<WordDetailsCommentBean.OneBean> mOneList;
    private List<WordDetailsCommentBean.TwoBean> mTwoList;

    private String postsId, memberId, userId;
    private RequestQueue mRequestQueue;

    @SuppressLint("HandlerLeak")
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

        if (!AppHelper.getInstance().isLogined()) {//未登录状态
            memberId = "";
        } else {//登录状态
            memberId = AppHelper.getInstance().getUser().getID();//登录用户ID
        }
        postsId = getIntent().getStringExtra(I.Posts.POSTS_ID);//帖子ID

        mRequestQueue = NoHttp.newRequestQueue();
        getContentData();//获取帖子内容数据
    }

    private void initView() {
        setToolbar();
        mRvContent = findViewById(R.id.recycler_details_posts_content);
        mElvComment = findViewById(R.id.elv_details_posts_comment);
        mNsv = findViewById(R.id.nsv_details_posts);
        mllAll = findViewById(R.id.ll_details_posts_all);
        mllShow = findViewById(R.id.ll_details_posts_show);
        mllHide = findViewById(R.id.ll_details_posts_hide);
        mTvNick = findViewById(R.id.tv_details_posts_nick);
        mTvTime = findViewById(R.id.tv_details_posts_time);
        mTvType = findViewById(R.id.tv_details_posts_type);
        mTvTitle = findViewById(R.id.tv_details_posts_title);
        mTvSwitch = findViewById(R.id.tv_details_posts_switch);
        mEdEnter = findViewById(R.id.etv_details_posts_ed);
        mAvatar = findViewById(R.id.img_details_posts_avatar);
        mImgSugar = findViewById(R.id.img_details_posts_sugar);
        mImgCollect = findViewById(R.id.img_details_posts_collect);
        mBtnAttention = findViewById(R.id.btn_details_posts_attention);
        mBtnSend = findViewById(R.id.btn_details_posts_send);
        mImgSugar.setOnClickListener(this);
        mImgCollect.setOnClickListener(this);
        mBtnAttention.setOnClickListener(this);
        mTvNick.setOnClickListener(this);
        mAvatar.setOnClickListener(this);
//        findViewById(R.id.img_details_posts_expression).setOnClickListener(this);
    }

    /**
     * 获取帖子内容数据
     */
    private void getContentData() {
        Log.i(TAG, "getContentData: postsId==" + postsId);
        Log.i(TAG, "getContentData: memberId==" + memberId);
        Request<String> request = NoHttp.createStringRequest(I.POSTS_DETAIL, RequestMethod.POST);
        request.add("id", postsId);
        request.add("memberId", memberId);

        mRequestQueue.add(0, request, new OnResponseListener<String>() {
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

                getCommentData(2);//获取一级评论及一级评论下默认显示的二级评论数据
                getIsFollowData();//获取是否关注用户数据
                initDetailsPosts();//初始化帖子详情

                Log.i(TAG, "onSucceed: zan==" + mContentList.get(0).getZAN());
                Log.i(TAG, "onSucceed: collect==" + mContentList.get(0).getCOLLECT());
                if ("true".equals(mContentList.get(0).getZAN())) {//如果已点赞，显示点赞状态
                    mImgSugar.setImageResource(R.drawable.ic_sugar_selected);
                }

                if ("true".equals(mContentList.get(0).getCOLLECT())) {//如果已收藏，显示收藏状态
                    mImgCollect.setImageResource(R.drawable.ic_collect_selected);
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
     * 获取是否关注用户数据
     */
    private void getIsFollowData() {
        userId = mContentList.get(0).getUSERID();
        Request<String> request = NoHttp.createStringRequest(I.IS_FOLLOW, RequestMethod.POST);
        request.add("followId", userId);//被关注人id
        request.add("memberId", memberId);

        mRequestQueue.add(0, request, new OnResponseListener<String>() {
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

                //如果进入自己的帖子，隐藏关注控件
                if (Objects.equals(userId, memberId)) {
                    mBtnAttention.setVisibility(View.GONE);
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
     * 重新获取评论数据（刷新评论）
     *
     * @param i 位置
     */
    private void getCommentData(final int i) {
        Request<String> request = NoHttp.createStringRequest(I.QUERY_COMMENT, RequestMethod.POST);
        request.add("topicId", postsId);
        request.add("page", 1);
        request.add("rows", Integer.MAX_VALUE);
        request.add("twoNum", Integer.MAX_VALUE);
        request.add("memberId", memberId);

        mRequestQueue.add(0, request, new OnResponseListener<String>() {
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
                if (i == FOCUS_DOWN) {//置底
                    handler.sendEmptyMessage(FOCUS_DOWN);
                }

//                clickCommentDefault();//点击默认评论
                commitComment();//提交一级评论功能

                oneCommentClick();//一级评论点击事件
                twoCommentClick();//二级评论点击事件
                zanOneOnClick();//一级评论点赞点击事件
                zanTwoOnClick();//二级评论点赞点击事件
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
     * 进入帖子详情点击评论切换（默认评论）
     */
    private void clickCommentDefault() {
        mTvSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//点击底部 TextView 切换布局
                if (AppHelper.getInstance().isLogined()) {//登录状态
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
                                //保存默认评论数据
                                saveOneCommentData();
                            }
                        }
                    });
                } else {//未登录状态
                    MFGT.gotoLogin(DetailsPostsActivity.this, "sendComment");
                }
            }
        });
    }

    /**
     * 提交评论功能
     */
    private void commitComment() {
        mTvSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//点击底部 TextView 切换布局
                if (AppHelper.getInstance().isLogined()) {//登录状态
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
                                //保存一级及默认评论数据
                                saveOneCommentData();
                            }
                        }
                    });
                } else {//未登录状态
                    MFGT.gotoLogin(DetailsPostsActivity.this, "sendComment");
                }
            }
        });
    }

    /**
     * 保存一级及默认评论数据
     */
    private void saveOneCommentData() {
        Request<String> request = NoHttp.createStringRequest(I.SAVE_COMMENT, RequestMethod.POST);
        request.add("topicId", postsId);//帖子id
        request.add("authorId", mOneList.get(0).getBEENMEMBERID());//帖子作者id
        request.add("memberId", memberId);//评论人id
        if (Objects.equals(mOneList.get(0).getMEMBERID(), memberId)) {//如果被评论人是自己
            request.add("beenMemberId", memberId);//被评论人id（一级评论的被评论人是贴主）
        } else {//如果被评论人不是自己
            request.add("beenMemberId", mOneList.get(0).getBEENMEMBERID());//被评论人id
        }
        request.add("atId", Uuid.getUuid());
        request.add("parentId", "0");//"0"代表一级评论
        request.add("atIds", "");
        request.add("atUsers", "");
        request.add("content", mEdEnter.getText().toString());

        mRequestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.e(TAG, "保存一级评论数据请求成功，json 数据是：" + json);
                //阻止键盘弹出（关闭软键盘）
                InputMethodManager imm1 = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm1.hideSoftInputFromWindow(mEdEnter.getWindowToken(), 0);
                //清空输入内容，切换布局
                mEdEnter.getText().clear();
                mllShow.setVisibility(View.VISIBLE);
                mllHide.setVisibility(View.GONE);
                //重新获取帖子评论数据（刷新）？为什么刷新 直接底部增加数据不就可以了吗？
                getCommentData(FOCUS_DOWN);//一级评论结束置底
//                insertParentComment(mOneList.size()-1,);
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
     * 保存二级评论数据
     * @param groupPosition
     */
    private void saveTwoCommentData(int groupPosition) {
        EditText ed=findViewById(R.id.etv_details_posts_ed);
        Request<String> request = NoHttp.createStringRequest(I.SAVE_COMMENT, RequestMethod.POST);
        request.add("topicId", postsId);//帖子id
        request.add("authorId", mContentList.get(0).getUSERID());//帖子作者ID
        request.add("memberId", memberId);//评论人id
//        if (Objects.equals(mTwoList.get(0).getMEMBERID(), memberId)) {//如果被评论人是自己
//            request.add("beenMemberId", memberId);//被评论人id（一级评论的被评论人是贴主）
//        } else {//如果被评论人不是自己
//            request.add("beenMemberId", mTwoList.get(0).getBEENMEMBERID());//被评论人id
//        }
        request.add("beenMemberId", mOneList.get(groupPosition).getMEMBERID());//被评论人id
        request.add("atId", Uuid.getUuid());
        request.add("parentId", mOneList.get(groupPosition).getID());//父级评论id，这里传为一级评论的id
        request.add("atIds", "");
        request.add("atUsers", "");
        request.add("content", ed.getText().toString());

        mRequestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.e(TAG, "保存二级评论数据请求成功，json 数据是：" + json);
                //阻止键盘弹出（关闭软键盘）
                InputMethodManager imm1 = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm1.hideSoftInputFromWindow(mEdEnter.getWindowToken(), 0);
                //清空输入内容，切换布局
                mEdEnter.getText().clear();
                mllShow.setVisibility(View.VISIBLE);
                mllHide.setVisibility(View.GONE);
                //重新获取帖子评论数据（刷新）
                getCommentData(2);//二级评论保持原位
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
     * 保存多级评论数据
     * @param groupPosition
     * @param childPosition
     */
    private void saveMoreCommentData(int groupPosition, int childPosition) {
        Request<String> request = NoHttp.createStringRequest(I.SAVE_COMMENT, RequestMethod.POST);
        request.add("topicId", postsId);//帖子id
        request.add("authorId", mTwoList.get(0).getBEENMEMBERID());//帖子作者id
        request.add("memberId", memberId);//评论人id
        if (Objects.equals(mTwoList.get(0).getMEMBERID(), memberId)) {//如果被评论人是自己
            request.add("beenMemberId", memberId);//被评论人id（一级评论的被评论人是贴主）
        } else {//如果被评论人不是自己
            request.add("beenMemberId", mTwoList.get(0).getBEENMEMBERID());//被评论人id
        }
        request.add("atId", Uuid.getUuid());
        request.add("parentId", mOneList.get(0).getID());//父级评论id，这里传为一级评论的id
        request.add("atIds", "");
        request.add("atUsers", mTwoList.get(0).getATUSERS());
        request.add("content", mEdEnter.getText().toString());

        mRequestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.e(TAG, "保存三级评论数据请求成功，json 数据是：" + json);
                //阻止键盘弹出（关闭软键盘）
                InputMethodManager imm1 = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm1.hideSoftInputFromWindow(mEdEnter.getWindowToken(), 0);
                //清空输入内容，切换布局
                mEdEnter.getText().clear();
                mllShow.setVisibility(View.VISIBLE);
                mllHide.setVisibility(View.GONE);
                //重新获取帖子评论数据（刷新）
                getCommentData(2);//多级评论保持原位
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
     * 一级评论点击事件
     */
    private void oneCommentClick() {
        mElvComment.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, final int groupPosition, long id) {
                if (AppHelper.getInstance().isLogined()) {//登录状态
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
                                //保存二级评论数据
                                saveTwoCommentData(groupPosition);
                            }
                        }
                    });
                } else {//未登录状态
                    MFGT.gotoLogin(DetailsPostsActivity.this, "sendComment");
                }
                return false;
            }
        });
    }

    /**
     * 二级评论点击事件
     */
    private void twoCommentClick() {
        mElvComment.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, final int groupPosition, final int childPosition, long id) {
                if (AppHelper.getInstance().isLogined()) {//登录状态
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
                                //保存多级评论数据
                                saveMoreCommentData(groupPosition, childPosition);
                            }
                        }
                    });
                } else {//未登录状态
                    MFGT.gotoLogin(DetailsPostsActivity.this, "sendComment");
                }
                return false;
            }
        });
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_posts_details);

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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_details_posts_attention://关注
                if (!(AppHelper.getInstance().isLogined())) {
                    MFGT.gotoLogin(this, "loginAttention");
                } else {
                    actionAttention();
                }
                break;
            case R.id.img_details_posts_sugar://点赞
                if (!(AppHelper.getInstance().isLogined())) {
                    MFGT.gotoLogin(this, "loginZan");
                } else {
                    actionSupport();
                }
                break;
            case R.id.img_details_posts_collect://收藏
                if (!(AppHelper.getInstance().isLogined())) {
                    MFGT.gotoLogin(this, "loginCollect");
                } else {
                    actionCommunity();
                }
                break;
//            case R.id.img_details_posts_expression://表情
//                break;
            case R.id.img_details_posts_avatar://头像
            case R.id.tv_details_posts_nick://贴主名
                //跳转至个人中心
                startActivity(new Intent(this, MinePageOtherActivity.class).putExtra("memberId", userId));
                break;
            default:
                break;
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

        mRequestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.e(TAG, "关注数据请求成功，json 数据是：" + json);

                mIsFollowBean = gson.fromJson(json, IsFollowBean.class);
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

            @Override
            public void onFailed(int what, Response<String> response) {
            }

            @Override
            public void onFinish(int what) {
            }
        });
    }

    /**
     * 点赞功能
     */
    private void actionSupport() {
        mRequestQueue = NoHttp.newRequestQueue();
        int action = 0;
        if ("true".equals(mContentList.get(0).getZAN())) {
            action = 1;//取消点赞
        }
        Request<String> request = NoHttp.createStringRequest(I.POSTS_ZAN, RequestMethod.POST);
        request.add("memberId", AppHelper.getInstance().getUserID());
        request.add("topickId", mContentList.get(0).getID());
        request.add("mark", action);

        final int finalAction = action;
        mRequestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.e(TAG, "点赞请求：" + json);
                if (json != null) {
                    Type type = new TypeToken<ActionResultBean>() {
                    }.getType();
                    Gson gson = new Gson();
                    ActionResultBean resultBean = gson.fromJson(json, type);
                    if (resultBean.isOk()) {
                        switch (finalAction) {
                            case 0://点赞成功
                                mContentList.get(0).setZAN("true");//重置用户对当前帖子的点赞状态
                                mContentList.get(0).setZAN_COUNT(String.valueOf(
                                        Integer.parseInt(mContentList.get(0).getZAN_COUNT()) + 1));
                                mImgSugar.setImageResource(R.drawable.ic_sugar_selected);
                                break;
                            case 1://取消点赞成功
                                mContentList.get(0).setZAN("false");//重置用户对当前帖子的点赞状态
                                mContentList.get(0).setZAN_COUNT(String.valueOf(
                                        Integer.parseInt(mContentList.get(0).getZAN_COUNT()) - 1));
                                mImgSugar.setImageResource(R.drawable.ic_sugar_unselected);
                                break;
                        }
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
     * 收藏功能
     */
    private void actionCommunity() {
        mRequestQueue = NoHttp.newRequestQueue();
        int action = 0;
        if ("true".equals(mContentList.get(0).getCOLLECT())) {
            action = 1;//取消收藏
        }
        Request<String> request = NoHttp.createStringRequest(I.POSTS_COLLECT, RequestMethod.POST);
        request.add("memberId", AppHelper.getInstance().getUserID());
        request.add("CommunityId", mContentList.get(0).getID());
        request.add("mark", action);

        final int finalAction = action;
        mRequestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.e(TAG, "收藏请求：" + json);
                if (json != null) {
                    Type type = new TypeToken<ActionResultBean>() {
                    }.getType();
                    ActionResultBean resultBean = gson.fromJson(json, type);
                    if (resultBean.isOk()) {
                        switch (finalAction) {
                            case 0://收藏成功
                                mContentList.get(0).setCOLLECT("true");//重置用户对当前帖子的收藏状态
                                mContentList.get(0).setCOLLECT_COUNT(String.valueOf(
                                        Integer.parseInt(mContentList.get(0).getCOLLECT_COUNT()) + 1));
                                mImgCollect.setImageResource(R.drawable.ic_collect_selected);
                                break;
                            case 1://取消收藏成功
                                mContentList.get(0).setCOLLECT("false");//重置用户对当前帖子的收藏状态
                                mContentList.get(0).setCOLLECT_COUNT(String.valueOf(
                                        Integer.parseInt(mContentList.get(0).getCOLLECT_COUNT()) - 1));
                                mImgCollect.setImageResource(R.drawable.ic_collect_unselected);
                                break;
                        }
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
     * 一级评论点赞功能
     */
    private void oneCommentZan(final int position, final ImageView zanIv, final TextView commentTv) {
        int action = 0;//点赞
        // TODO: 2017/11/1 没有保存评论点赞状态的功能字段
//        if ("true".equals(mOneList.get(position).getISZAN())) {
//            action = 1;//取消点赞
//        }
        Request<String> request = NoHttp.createStringRequest(I.COMMENT_ZAN, RequestMethod.POST);
        request.add("topicId", mOneList.get(position).getTOPICID());//帖子id
        request.add("zanMemberId", memberId);//点赞用户id(登录用户id)
        request.add("beenZanMemberId", mOneList.get(position).getMEMBERID());//被点赞用户id(当前条评论提交人id)
        request.add("commentId", mOneList.get(position).getID());//评论id
        request.add("zan", action);//点赞、取消赞

        Log.i(TAG, "oneCommentZan: topicId==" + mOneList.get(position).getTOPICID());
        Log.i(TAG, "oneCommentZan: zanMemberId==" + memberId);
        Log.i(TAG, "oneCommentZan: beenZanMemberId==" + mOneList.get(position).getMEMBERID());
        Log.i(TAG, "oneCommentZan: commentId==" + mOneList.get(position).getID());
        Log.i(TAG, "oneCommentZan: zan==" + action);

        final int finalAction = action;
        mRequestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.e(TAG, "点赞请求：" + json);
                if (json != null) {
                    Type type = new TypeToken<ActionResultBean>() {
                    }.getType();
                    Gson gson = new Gson();
                    ActionResultBean resultBean = gson.fromJson(json, type);
                    if (resultBean.isOk()) {
                        switch (finalAction) {
                            case 0://点赞成功
//                                mContentList.get(position).setZAN("true");//重置用户对当前帖子的点赞状态
                                mOneList.get(position).setZANCOUNT(String.valueOf(Integer.parseInt(mOneList.get(position).getZANCOUNT()) + 1));
                                commentTv.setText(mOneList.get(position).getZANCOUNT());
                                zanIv.setImageResource(R.drawable.ic_zan_selected);
                                break;
                            case 1://取消点赞成功
//                                mContentList.get(position).setZAN("false");//重置用户对当前帖子的点赞状态
                                mOneList.get(position).setZANCOUNT(String.valueOf(Integer.parseInt(mOneList.get(position).getZANCOUNT()) - 1));
                                commentTv.setText(mOneList.get(position).getZANCOUNT());
                                zanIv.setImageResource(R.drawable.ic_zan_unselected);
                                break;
                        }
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
     * 二级评论点赞功能
     */
    private void twoCommentZan(final int position, final ImageView zanIv, final TextView commentTv) {
        int action = 0;//点赞
        // TODO: 2017/11/1 没有保存评论点赞状态的功能字段
//        if ("true".equals(mOneList.get(position).getISZAN())) {
//            action = 1;//取消点赞
//        }
        Request<String> request = NoHttp.createStringRequest(I.COMMENT_ZAN, RequestMethod.POST);
        request.add("topicId", mTwoList.get(position).getTOPICID());//帖子id
        request.add("zanMemberId", memberId);//点赞用户id(登录用户id)
        request.add("beenZanMemberId", mTwoList.get(position).getMEMBERID());//被点赞用户id(当前条评论提交人id)
        request.add("commentId", mTwoList.get(position).getID());//评论id
        request.add("zan", action);//点赞、取消赞

        Log.i(TAG, "twoCommentZan: topicId==" + mTwoList.get(position).getTOPICID());
        Log.i(TAG, "twoCommentZan: zanMemberId==" + memberId);
        Log.i(TAG, "twoCommentZan: beenZanMemberId==" + mTwoList.get(position).getMEMBERID());
        Log.i(TAG, "twoCommentZan: commentId==" + mTwoList.get(position).getID());
        Log.i(TAG, "twoCommentZan: zan==" + action);

        final int finalAction = action;
        mRequestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.e(TAG, "点赞请求：" + json);
                if (json != null) {
                    Type type = new TypeToken<ActionResultBean>() {
                    }.getType();
                    Gson gson = new Gson();
                    ActionResultBean resultBean = gson.fromJson(json, type);
                    if (resultBean.isOk()) {
                        switch (finalAction) {
                            case 0://点赞成功
//                                mContentList.get(position).setZAN("true");//重置用户对当前帖子的点赞状态
                                mTwoList.get(position).setZANCOUNT(String.valueOf(Integer.parseInt(mTwoList.get(position).getZANCOUNT()) + 1));
                                commentTv.setText(mTwoList.get(position).getZANCOUNT());
                                zanIv.setImageResource(R.drawable.ic_zan_selected);
                                break;
                            case 1://取消点赞成功
//                                mContentList.get(position).setZAN("false");//重置用户对当前帖子的点赞状态
                                mTwoList.get(position).setZANCOUNT(String.valueOf(Integer.parseInt(mTwoList.get(position).getZANCOUNT()) - 1));
                                commentTv.setText(mTwoList.get(position).getZANCOUNT());
                                zanIv.setImageResource(R.drawable.ic_zan_unselected);
                                break;
                        }
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
     * 一级评论点赞点击事件
     */
    private void zanOneOnClick() {
        mCommentAdapter.oneClick(new SetOnClickListen() {
            @Override
            public void setOnClick(int position) {
            }

            @Override
            public void setOnClick(int position, TextView zanTv, TextView collecTv, TextView commentTv, ImageView zanIv, ImageView cllecIv) {
                oneCommentZan(position, zanIv, commentTv);
            }
        });
    }

    /**
     * 二级评论点赞点击事件
     */
    private void zanTwoOnClick() {
        mCommentAdapter.twoClick(new SetOnClickListen() {
            @Override
            public void setOnClick(int position) {
            }

            @Override
            public void setOnClick(int position, TextView zanTv, TextView collecTv, TextView commentTv, ImageView zanIv, ImageView cllecIv) {
                twoCommentZan(position, zanIv, commentTv);
            }
        });
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

    /**
     * 初始化帖子评论
     */
    private void initCommentPosts() {
        if (mCommentBean != null) {
            mCommentAdapter = new DetailsPostsCommentAdapter(this, mCommentBean);
            mElvComment.setAdapter(mCommentAdapter);
            mElvComment.setGroupIndicator(null);
            //默认展开每一个分组
            for (int i = 0; i < mCommentAdapter.getGroupCount(); i++) {
                mElvComment.expandGroup(i);
            }
        }
    }

}
