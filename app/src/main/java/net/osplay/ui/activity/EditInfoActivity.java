package net.osplay.ui.activity;

import android.os.Bundle;
import android.view.View;

import net.osplay.olacos.R;

public class EditInfoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);

        setToolbar("编辑资料", View.VISIBLE);
    }

}
