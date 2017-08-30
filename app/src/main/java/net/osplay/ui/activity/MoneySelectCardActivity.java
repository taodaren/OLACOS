package net.osplay.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import net.osplay.olacos.R;

/**
 * 钱包 → 提现 → 选择银行卡
 */

public class MoneySelectCardActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_select_card);
        initView();
    }

    private void initView() {
        setToolbar("选择银行卡", View.VISIBLE);
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
