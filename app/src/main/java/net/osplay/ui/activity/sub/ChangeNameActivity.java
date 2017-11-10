package net.osplay.ui.activity.sub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import net.osplay.olacos.R;
import net.osplay.ui.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 修改个人姓名
 */
public class ChangeNameActivity extends Activity {

    @BindView(R.id.change_name)
    EditText changeName;
    @BindView(R.id.change_back_iv)
    ImageView changeBackIv;
    @BindView(R.id.change_save)
    TextView changeSave;
    private String nameTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_name);
        ButterKnife.bind(this);
        initData();
    }

    //得到修改前的姓名
    private void initData() {
        Intent intent1 = getIntent();
        nameTv = intent1.getStringExtra("nameTv");
        changeName.setHint(nameTv);
    }
    @OnClick({R.id.change_back_iv, R.id.change_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change_back_iv:
                finish();
                break;
            case R.id.change_save:
                Intent returnName = new Intent();
                returnName.putExtra("returnName", changeName.getText().toString());
                setResult(1, returnName);
                finish();
                break;
        }
    }
}
