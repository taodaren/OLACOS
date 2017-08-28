package net.osplay.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import net.osplay.olacos.R;

public class EditInfoActivity extends BaseActivity {
    //复用控件类别、名称
    private TextView textLB, textMC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);

        initView();
    }

    private void initView() {
        setToolbar("编辑资料", View.VISIBLE);
        textLB = (TextView) findViewById(R.id.text_layout_lb);
        textMC = (TextView) findViewById(R.id.text_layout_mc);
        switch (R.id.text_layout_lb) {
            case R.id.edit_info_name:
                textLB.setText("姓名");
                textMC.setVisibility(View.GONE);
                break;
        }
    }
}
