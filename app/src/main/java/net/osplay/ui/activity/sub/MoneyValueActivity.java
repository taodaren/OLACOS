package net.osplay.ui.activity.sub;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import net.osplay.olacos.R;
import net.osplay.ui.activity.base.BaseActivity;

/**
 * 钱包 → 充值界面
 */

public class MoneyValueActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_value);
        initView();
    }

    private void initView() {
        setToolbar("零钱充值", View.VISIBLE);
        findViewById(R.id.text_new_card).setOnClickListener(this);
        findViewById(R.id.btn_money_value_next).setOnClickListener(this);
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
            case R.id.text_new_card:
                startActivity(new Intent(MoneyValueActivity.this, MoneyNewCardActivity.class));
                break;
            case R.id.btn_money_value_next:
                Toast.makeText(this, "进入充值逻辑", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
    }

}
