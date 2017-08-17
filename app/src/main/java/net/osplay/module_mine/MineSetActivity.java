package net.osplay.module_mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import net.osplay.olacos.R;
import net.osplay.olacos.base.BaseActivity;

/**
 * 个人中心 → 设置
 */

public class MineSetActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_set);

        setToolbar("个人设置", View.VISIBLE);
        setView();
    }

    private void setView() {
        //登录密码修改
        findViewById(R.id.text_modify_pwd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MineSetActivity.this, "跳转到登录密码修改", Toast.LENGTH_SHORT).show();
            }
        });

        //手机号更改
        findViewById(R.id.text_modify_phone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MineSetActivity.this, "跳转到手机号更改", Toast.LENGTH_SHORT).show();
            }
        });

        //清理缓存
        findViewById(R.id.text_clean_cache).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MineSetActivity.this, "跳转到清理缓存", Toast.LENGTH_SHORT).show();
            }
        });

        //意见反馈
        findViewById(R.id.mine_set_feedback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MineSetActivity.this, FeedbackActivity.class));
            }
        });

        //退出按钮
        findViewById(R.id.btn_mine_set).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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

}
