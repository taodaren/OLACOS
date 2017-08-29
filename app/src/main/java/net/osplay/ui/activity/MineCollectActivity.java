package net.osplay.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import net.osplay.olacos.R;

/**
 * 个人中心 → 我的收藏
 */

public class MineCollectActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_collect);
        initView();
    }

    private void initView() {
        setToolbar("收藏列表", View.VISIBLE);
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
