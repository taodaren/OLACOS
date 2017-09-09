package net.osplay.ui.activity.sub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.wyt.searchbox.SearchFragment;
import com.wyt.searchbox.custom.IOnSearchClickListener;

import net.osplay.olacos.R;
import net.osplay.ui.activity.base.BaseActivity;

public class SelectCommunityActivity extends BaseActivity {

    private ImageView select_black_toolbar,select_search_toolbar;
    private SearchFragment searchFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_community);
        initView();
    }
    private void initView() {
        select_black_toolbar= (ImageView) findViewById(R.id.select_black_toolbar);
        select_search_toolbar= (ImageView) findViewById(R.id.select_search_toolbar);
        select_black_toolbar.setOnClickListener(mOnClickListener);
        select_search_toolbar.setOnClickListener(mOnClickListener);
        onSelect();
    }

    private void onSelect() {
        searchFragment= SearchFragment.newInstance();
        searchFragment.setOnSearchClickListener(new IOnSearchClickListener() {
            @Override
            public void OnSearchClick(String keyword) {
                //这里处理逻辑
                Toast.makeText(SelectCommunityActivity.this, keyword, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private View.OnClickListener mOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.select_black_toolbar:
                    break;
                case R.id.select_search_toolbar:
                    searchFragment.show(getSupportFragmentManager(),SearchFragment.TAG);
                    break;
            }
        }
    };
}
