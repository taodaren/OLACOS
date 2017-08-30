package net.osplay.ui.activity.sub;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import net.osplay.olacos.R;
import net.osplay.ui.activity.base.BaseActivity;

/**
 * 钱包 → 充值 → 添加银行卡
 */

public class MoneyNewCardActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_new_card);
        initView();
    }

    private void initView() {
        setToolbar("添加银行卡", View.VISIBLE);
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
