package net.osplay.ui.activity.sub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import net.osplay.olacos.R;
import net.osplay.ui.activity.base.BaseActivity;

/**
 * 钱包 → 提现界面
 */

public class MoneyWithdrawActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_withdraw);
        initView();
    }

    private void initView() {
        setToolbar("零钱提现", View.VISIBLE);
        findViewById(R.id.text_to_card).setOnClickListener(this);
        findViewById(R.id.btn_money_withdraw).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_to_card:
                startActivity(new Intent(MoneyWithdrawActivity.this, MoneySelectCardActivity.class));
                break;
            case R.id.btn_money_withdraw:
                Toast.makeText(this, "进入提现逻辑", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
    }

}
