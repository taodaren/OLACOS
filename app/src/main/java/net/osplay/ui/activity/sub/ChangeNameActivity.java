package net.osplay.ui.activity.sub;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import net.osplay.app.AppHelper;
import net.osplay.app.I;
import net.osplay.olacos.R;
import net.osplay.service.entity.UserLoginBean;
import net.osplay.ui.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 修改个人姓名
 */
public class ChangeNameActivity extends BaseActivity {

    @BindView(R.id.change_name)
    EditText changeName;
    @BindView(R.id.change_save)
    Button changeSave;
    private String nameTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_name);
        ButterKnife.bind(this);
        setToolbar("更改姓名", View.VISIBLE);
        initData();
    }

    //得到修改前的姓名
    private void initData() {
        Intent intent1 = getIntent();
        nameTv = intent1.getStringExtra("nameTv");
        changeName.setText(nameTv);
    }


    //保存修改按钮
    @OnClick(R.id.change_save)
    public void onViewClicked() {
        Intent returnName = new Intent();
        returnName.putExtra("returnName",changeName.getText().toString());
        setResult(1,returnName);
        finish();
    }


}
