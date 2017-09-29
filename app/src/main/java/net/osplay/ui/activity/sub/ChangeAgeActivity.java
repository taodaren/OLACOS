package net.osplay.ui.activity.sub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import net.osplay.olacos.R;
import net.osplay.ui.activity.base.BaseActivity;

public class ChangeAgeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_age);
        setToolbar("更改年龄", View.VISIBLE);
    }
}
