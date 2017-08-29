package net.osplay.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import net.osplay.olacos.R;

/**
 * 个人中心
 */

public class MineCenterActivity extends BaseActivity implements View.OnClickListener {
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_center);
        initView();

    }

    private void initView() {
        setToolbar();
        setClickListener();
        intent = new Intent(MineCenterActivity.this, OrderActivity.class);
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
        findViewById(R.id.mine_league).setOnClickListener(this);
        findViewById(R.id.mine_money).setOnClickListener(this);
        findViewById(R.id.mine_bao_xiang).setOnClickListener(this);
        findViewById(R.id.mine_order).setOnClickListener(this);
        findViewById(R.id.mine_pay).setOnClickListener(this);
        findViewById(R.id.mine_ship).setOnClickListener(this);
        findViewById(R.id.mine_receipt).setOnClickListener(this);
        findViewById(R.id.mine_assess).setOnClickListener(this);
        findViewById(R.id.mine_tui_huo).setOnClickListener(this);
        findViewById(R.id.mine_fa_bu).setOnClickListener(this);
        findViewById(R.id.mine_mai_chu).setOnClickListener(this);
        findViewById(R.id.mine_mai_dao).setOnClickListener(this);
        findViewById(R.id.mine_praise).setOnClickListener(this);
        findViewById(R.id.mine_about).setOnClickListener(this);
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
                intent.putExtra("data", 0);
                startActivity(intent);
                break;
            case R.id.mine_pay://待付款
                intent.putExtra("data", 1);
                startActivity(intent);
                break;
            case R.id.mine_ship://待发货
                intent.putExtra("data", 2);
                startActivity(intent);
                break;
            case R.id.mine_receipt://待收货
                intent.putExtra("data", 3);
                startActivity(intent);
                break;
            case R.id.mine_assess://待评价
                intent.putExtra("data", 4);
                startActivity(intent);
                break;
            case R.id.mine_tui_huo://退货售后
                startActivity(new Intent(MineCenterActivity.this, ReturnGoodsActivity.class));
                break;
            case R.id.mine_column://专栏

                break;
            case R.id.mine_word://社区

                break;
            case R.id.mine_league://社团

                break;
            case R.id.mine_money://钱包

                break;
            case R.id.mine_bao_xiang://宅配宝箱

                break;
            case R.id.mine_fa_bu://发布

                break;
            case R.id.mine_mai_chu://卖出

                break;
            case R.id.mine_mai_dao://买到

                break;
            case R.id.mine_praise://赞

                break;
            case R.id.mine_about://关于
                Toast.makeText(this, "关于 OLACOS", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mine_set://设置
                startActivity(new Intent(MineCenterActivity.this, MineSetActivity.class));
                break;
        }
    }

}



