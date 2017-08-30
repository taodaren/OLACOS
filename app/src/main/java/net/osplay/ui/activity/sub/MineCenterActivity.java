package net.osplay.ui.activity.sub;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import net.osplay.olacos.R;
import net.osplay.ui.activity.base.BaseActivity;

/**
 * 个人中心
 */

public class MineCenterActivity extends BaseActivity implements View.OnClickListener {
    private Intent mIntentOrder, mIntentTrading;//订单，宅配宝箱

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_center);
        initView();
    }

    private void initView() {
        setToolbar();
        setClickListener();
        mIntentOrder = new Intent(MineCenterActivity.this, OrderActivity.class);
        mIntentTrading = new Intent(MineCenterActivity.this, MineTradingActivity.class);
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_mine_center);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
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

    private void setClickListener() {
        findViewById(R.id.mine_avatar).setOnClickListener(this);
        findViewById(R.id.mine_collect).setOnClickListener(this);
        findViewById(R.id.mine_follow).setOnClickListener(this);
        findViewById(R.id.mine_fans).setOnClickListener(this);
        findViewById(R.id.mine_column).setOnClickListener(this);
        findViewById(R.id.mine_word).setOnClickListener(this);
        findViewById(R.id.mine_money).setOnClickListener(this);
        findViewById(R.id.mine_order).setOnClickListener(this);
        findViewById(R.id.mine_pay).setOnClickListener(this);
        findViewById(R.id.mine_ship).setOnClickListener(this);
        findViewById(R.id.mine_receipt).setOnClickListener(this);
        findViewById(R.id.mine_assess).setOnClickListener(this);
        findViewById(R.id.mine_tui_huo).setOnClickListener(this);
        findViewById(R.id.mine_fa_bu).setOnClickListener(this);
        findViewById(R.id.mine_mai_chu).setOnClickListener(this);
        findViewById(R.id.mine_mai_dao).setOnClickListener(this);
        findViewById(R.id.mine_set).setOnClickListener(this);
    }

    /**
     * 创建选项菜单
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        //显示需要菜单项，隐藏多余菜单项
        menu.findItem(R.id.menu_category).setVisible(false);
        menu.findItem(R.id.menu_code).setVisible(true);
        menu.findItem(R.id.menu_msg).setVisible(false);
        menu.findItem(R.id.menu_register).setVisible(false);
        menu.findItem(R.id.menu_set).setVisible(false);
        return true;
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
            case R.id.mine_avatar://头像
                startActivity(new Intent(MineCenterActivity.this, MinePageActivity.class));
                break;
            case R.id.mine_collect://收藏
                startActivity(new Intent(MineCenterActivity.this, MineCollectActivity.class));
                break;
            case R.id.mine_follow://关注

                break;
            case R.id.mine_fans://粉丝

                break;
            case R.id.mine_order://我的订单
                mIntentOrder.putExtra("data", 0);
                startActivity(mIntentOrder);
                break;
            case R.id.mine_pay://待付款
                mIntentOrder.putExtra("data", 1);
                startActivity(mIntentOrder);
                break;
            case R.id.mine_ship://待发货
                mIntentOrder.putExtra("data", 2);
                startActivity(mIntentOrder);
                break;
            case R.id.mine_receipt://待收货
                mIntentOrder.putExtra("data", 3);
                startActivity(mIntentOrder);
                break;
            case R.id.mine_assess://待评价
                mIntentOrder.putExtra("data", 4);
                startActivity(mIntentOrder);
                break;
            case R.id.mine_tui_huo://退货售后
                startActivity(new Intent(MineCenterActivity.this, ReturnGoodsActivity.class));
                break;
            case R.id.mine_word://社区

                break;
            case R.id.mine_money://钱包
                startActivity(new Intent(MineCenterActivity.this, MineMoneyActivity.class));
                break;
            case R.id.mine_mai_dao://买到
                mIntentTrading.putExtra("data", 0);
                startActivity(mIntentTrading);
                break;
            case R.id.mine_mai_chu://卖出
                mIntentTrading.putExtra("data", 1);
                startActivity(mIntentTrading);
                break;
            case R.id.mine_fa_bu://发布
                startActivity(new Intent(MineCenterActivity.this, MinePublishActivity.class));
                break;
            case R.id.mine_set://设置
                startActivity(new Intent(MineCenterActivity.this, MineSetActivity.class));
                break;
        }
    }

}



