package net.osplay.ui.activity;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import net.osplay.olacos.R;
import net.osplay.ui.adapter.VoteAdapter;

public class DouPictureVoteActivity extends BaseActivity {

    private NestedScrollView vote_scrollview;
    private RecyclerView vote_recycle;
    private VoteAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dou_picture_vote);
        setToolbar("发起斗图", View.VISIBLE);
        initView();
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

    private void initView() {
        vote_scrollview = (NestedScrollView) findViewById(R.id.vote_scrollview);
        vote_scrollview.smoothScrollTo(0,20);
        vote_recycle = (RecyclerView) findViewById(R.id.vote_recycle);
        vote_recycle.setLayoutManager(new GridLayoutManager(this,2));
        vote_recycle.setNestedScrollingEnabled(false);
        adapter=new VoteAdapter(this);
        vote_recycle.setAdapter(adapter);


    }
}
