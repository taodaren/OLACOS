package net.osplay.module_mine.activity;

import android.os.Bundle;
import android.view.View;

import net.osplay.olacos.R;
import net.osplay.olacos.base.BaseActivity;

public class ReturnGoodsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_goods);

        setToolbar("退货处理", View.VISIBLE);

    }
}
