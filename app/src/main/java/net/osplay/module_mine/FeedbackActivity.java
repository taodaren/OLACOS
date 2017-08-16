package net.osplay.module_mine;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import net.osplay.olacos.R;
import net.osplay.olacos.base.BaseActivity;

public class FeedbackActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        setToolbar("意见反馈", View.VISIBLE);

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
