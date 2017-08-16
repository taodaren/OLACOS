package net.osplay.module_mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import net.osplay.olacos.R;
import net.osplay.olacos.base.BaseActivity;

/**
 * 个人中心
 */

public class MineCenterActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_center);

        setToolbar();
        setClickListener();
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
        }
        //隐藏 CollapsingToolbarLayout 标题
        collapsingToolbar.setTitleEnabled(false);
    }

    private void setClickListener() {
        findViewById(R.id.mine_avatar).setOnClickListener(this);
        findViewById(R.id.mine_praise).setOnClickListener(this);
        findViewById(R.id.mine_follow).setOnClickListener(this);
        findViewById(R.id.mine_fans).setOnClickListener(this);
        findViewById(R.id.mine_column).setOnClickListener(this);
        findViewById(R.id.mine_word).setOnClickListener(this);
        findViewById(R.id.mine_league).setOnClickListener(this);
        findViewById(R.id.mine_money).setOnClickListener(this);
        findViewById(R.id.mine_bao_xiang).setOnClickListener(this);
        findViewById(R.id.mine_fa_huo).setOnClickListener(this);
        findViewById(R.id.mine_shou_huo).setOnClickListener(this);
        findViewById(R.id.mine_ping_jia).setOnClickListener(this);
        findViewById(R.id.mine_tui_huo).setOnClickListener(this);
        findViewById(R.id.mine_fa_bu).setOnClickListener(this);
        findViewById(R.id.mine_mai_chu).setOnClickListener(this);
        findViewById(R.id.mine_mai_dao).setOnClickListener(this);
        findViewById(R.id.mine_zan_guo).setOnClickListener(this);
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
        menu.findItem(R.id.menu_set).setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_set:
                startActivity(new Intent(MineCenterActivity.this, MineSetActivity.class));
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_avatar:

                break;
            case R.id.mine_praise:

                break;
            case R.id.mine_follow:

                break;
            case R.id.mine_fans:

                break;
            case R.id.mine_column:

                break;
            case R.id.mine_word:

                break;
            case R.id.mine_league:

                break;
            case R.id.mine_money:

                break;
            case R.id.mine_bao_xiang:

                break;
            case R.id.mine_fa_huo:

                break;
            case R.id.mine_shou_huo:

                break;
            case R.id.mine_ping_jia:

                break;
            case R.id.mine_tui_huo:

                break;
            case R.id.mine_fa_bu:

                break;
            case R.id.mine_mai_chu:

                break;
            case R.id.mine_mai_dao:

                break;
            case R.id.mine_zan_guo:

                break;
        }
    }
}
