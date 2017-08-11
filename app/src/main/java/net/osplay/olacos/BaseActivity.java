package net.osplay.olacos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Activity 基类
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //知晓当前是哪一个活动
        Log.d("BaseActivity", getClass().getSimpleName());
    }

    /**
     * 设置 Toolbar
     *
     * @param title           标题
     * @param titleVisibility 标题控件是否显示
     */
    public void setToolbar(String title, int titleVisibility) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //设置左上角后退按钮
            actionBar.setDisplayHomeAsUpEnabled(true);
            //设置导航按钮图标
            actionBar.setHomeAsUpIndicator(R.drawable.title_back);
            //隐藏 Toolbar 自带标题栏
            actionBar.setDisplayShowTitleEnabled(false);
            //隐藏城市选择控件
            RelativeLayout cityLayout = (RelativeLayout) findViewById(R.id.layout_city);
            cityLayout.setVisibility(View.GONE);
        }

        //设置标题
        TextView textTitle = (TextView) findViewById(R.id.title_toolbar);
        textTitle.setVisibility(titleVisibility);
        textTitle.setText(title);
    }
}
