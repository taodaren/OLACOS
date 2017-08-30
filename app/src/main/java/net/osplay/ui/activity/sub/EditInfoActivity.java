package net.osplay.ui.activity.sub;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import net.osplay.olacos.R;
import net.osplay.ui.activity.base.BaseActivity;

public class EditInfoActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);

        setToolbar("编辑资料", View.VISIBLE);
        findViewById(R.id.edit_info_real_name).setOnClickListener(this);
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
            case R.id.edit_info_real_name:
                startActivity(new Intent(EditInfoActivity.this, EditRealNameActivity.class));
                break;
        }
    }
}
