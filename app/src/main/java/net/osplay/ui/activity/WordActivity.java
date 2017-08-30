package net.osplay.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import net.osplay.olacos.R;

public class WordActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);
        initView();
    }

    private void initView() {
        setToolbar("我的社区", View.VISIBLE);
        findViewById(R.id.create_word_activity).setOnClickListener(this);
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
            case R.id.create_word_activity:
                startActivity(new Intent(WordActivity.this, PublishPostsActivity.class));
                break;
            default:
        }
    }
}
