package net.osplay.ui.activity.sub;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import net.osplay.olacos.R;
import net.osplay.ui.activity.base.BaseActivity;

/**
 * 热帖某一热帖详情
 */

public class DetailsPostsActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout mllShow, mllHide;
    private ImageView mImgSugar, mImgCollect;
    private Button mBtnAttention, mBtnUnAttention;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_posts);
        initView();
    }

    private void initView() {
        setToolbar();
        mllShow = (LinearLayout) findViewById(R.id.ll_details_posts_show);
        mllHide = (LinearLayout) findViewById(R.id.ll_details_posts_hide);
        mImgSugar = (ImageView) findViewById(R.id.img_details_posts_sugar);
        mImgCollect = (ImageView) findViewById(R.id.img_details_posts_collect);
        mBtnAttention = (Button) findViewById(R.id.btn_details_posts_attention);
        mBtnUnAttention = (Button) findViewById(R.id.btn_details_posts_unattention);
        mImgSugar.setOnClickListener(this);
        mImgCollect.setOnClickListener(this);
        mBtnAttention.setOnClickListener(this);
        mBtnUnAttention.setOnClickListener(this);
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
            case R.id.btn_details_posts_unattention://关注
                mBtnUnAttention.setVisibility(View.GONE);
                mBtnAttention.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_details_posts_attention://已关注
                mBtnAttention.setVisibility(View.GONE);
                mBtnUnAttention.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_details_posts_switch://切换
                mllShow.setVisibility(View.GONE);
                mllHide.setVisibility(View.VISIBLE);
                break;
            case R.id.img_details_posts_sugar://糖果
                mImgSugar.setImageResource(R.drawable.ic_sugar_selected);
                break;
            case R.id.img_details_posts_collect://收藏
                mImgCollect.setImageResource(R.drawable.ic_collect_selected);
                break;
            case R.id.img_details_posts_expression://表情
                break;
            case R.id.btn_details_posts_send://发送
                break;
        }
    }

}
