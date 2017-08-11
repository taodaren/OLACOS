package net.osplay.module_post;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import net.osplay.olacos.BaseActivity;
import net.osplay.olacos.R;

public class PublishActivityActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_activity);
        setToolbar("发布活动", View.VISIBLE);
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
