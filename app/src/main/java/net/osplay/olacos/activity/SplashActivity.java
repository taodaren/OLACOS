package net.osplay.olacos.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import net.osplay.olacos.R;
import net.osplay.olacos.base.BaseActivity;

/**
 * 闪屏启动页
 */

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // get user info 以后可以添加验证用户信息等
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 2000);
    }
}
