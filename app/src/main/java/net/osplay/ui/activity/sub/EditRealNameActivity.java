package net.osplay.ui.activity.sub;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import net.osplay.olacos.R;
import net.osplay.ui.activity.base.BaseActivity;

/**
 * 编辑资料 → 实名认证
 */

public class EditRealNameActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_real_name);

        setToolbar("实名认证", View.VISIBLE);
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
