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
        formatChangeName();
        finish();
    }

    private void formatChangeName() {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest(I.CHANGE_USER, RequestMethod.POST);
        request.add("ID",AppHelper.getInstance().getUser().getId());
        request.add("NICK_NAME",AppHelper.getInstance().getUser().getNICK_NAME());
        request.add("CN",changeName.getText().toString());
        request.add("BIRTHDAY",AppHelper.getInstance().getUser().getBIRTHDAY());
        request.add("TARGET",AppHelper.getInstance().getUser().getTARGET());
        request.add("XINGZUO",AppHelper.getInstance().getUser().getXINGZUO());
        request.add("INTRODUCE",AppHelper.getInstance().getUser().getINTRODUCE());
        request.add("LOCAL_DRESS",AppHelper.getInstance().getUser().getLOCAL_DRESS());
      //  http://www.olacos.net//memberMobile/updateMember.do?ID=69f1badc98cc441c838310561d11bcb7&NICK_NAME=呆子&CN=朱呵呵&BIRTHDAY=1994-11-06&TARGET=1&XINGZUO=天蝎座&INTRODUCE=啊累累&LOCAL_DRESS=北京
//        request.add("ID","69f1badc98cc441c838310561d11bcb7");
//        request.add("NICK_NAME","呆子");
//        request.add("CN","朱呵呵");
//        request.add("BIRTHDAY","1994-11-06");
//        request.add("TARGET","1");
//        request.add("XINGZUO","天蝎座");
//        request.add("INTRODUCE","啊累累");
//        request.add("LOCAL_DRESS","北京");

        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.e("JGB","change----------"+json);
                if (json != null) {

                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }

            @Override
            public void onFinish(int what) {

            }
        });
    }

}
