package net.osplay.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import net.osplay.olacos.R;

public class DouPictureDetailsActivity extends BaseActivity {

    private Button vote_send_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dou_picture_details);
        setToolbar("斗图详情", View.VISIBLE);
        initView();
        bindEvent();
    }

    private void initView() {
        vote_send_btn = (Button) findViewById(R.id.vote_send_btn);
    }

    private void bindEvent() {
        vote_send_btn.setOnClickListener(mOnClickListener);
    }
    private View.OnClickListener mOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.vote_send_btn:
                    startActivity(new Intent(DouPictureDetailsActivity.this,DouPictureVoteActivity.class));
                    break;
            }
        }
    };
}
