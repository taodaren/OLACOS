package net.osplay.ui.activity.sub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import net.osplay.olacos.R;
import net.osplay.ui.activity.base.BaseActivity;

public class ChangeAreaActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_local);
        setToolbar("更改地区", View.VISIBLE);
    }
}
