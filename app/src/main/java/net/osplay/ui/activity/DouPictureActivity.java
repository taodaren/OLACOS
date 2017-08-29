package net.osplay.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import net.osplay.olacos.R;

public class DouPictureActivity extends BaseActivity {

    private Button dou_start_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dou_picture);
        setToolbar("发起斗图", View.VISIBLE);
        initView();
        bindEvent();
    }

    private void initView() {
        dou_start_btn = (Button) findViewById(R.id.dou_start_btn);//开始按钮
    }

    private void bindEvent() {
        dou_start_btn.setOnClickListener(mOnclickListener);
    }

    private View.OnClickListener mOnclickListener= new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.dou_start_btn:
                    startActivity(new Intent(DouPictureActivity.this,DouPictureDetailsActivity.class));
                    break;
            }
        }
    };
}
