package net.osplay.ui.activity.sub;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import net.osplay.app.I;
import net.osplay.olacos.R;
import net.osplay.ui.activity.base.BaseActivity;
import net.osplay.utils.VerificationUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends BaseActivity {
    private Button btn_over, btn_get_code;
    private EditText editAccount, editPhone, editPhoneCode, editPassword, editConfirmPassword;
    private RequestQueue requestQueue = NoHttp.newRequestQueue();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setToolbar("注册", View.VISIBLE);
        initView();
        bindListener();
        getData();

    }

    private void bindListener() {
        btn_over.setOnClickListener(mOnClickListener);
    }

    private void initView() {
        editAccount = (EditText) findViewById(R.id.edit_account_register);//用户名
        editPhone = (EditText) findViewById(R.id.edit_phone_register);//手机号
        editPhoneCode = (EditText) findViewById(R.id.edit_phone_code);//手机验证码
        editPassword = (EditText) findViewById(R.id.edit_password_register);//密码
        editConfirmPassword = (EditText) findViewById(R.id.edit_confirm_password_register);//确认密码
        btn_get_code = (Button) findViewById(R.id.btn_get_code);//发送验证码
        btn_over = (Button) findViewById(R.id.btn_over);//完成

    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (TextUtils.isEmpty(editAccount.getText().toString())) {
                Toast.makeText(RegisterActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            }else if(editAccount.getText().toString().length()>10){
                Toast.makeText(RegisterActivity.this, "用户名长度不能超过10个字符", Toast.LENGTH_SHORT).show();
            }else if (TextUtils.isEmpty(editPhone.getText().toString())) {
                Toast.makeText(RegisterActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
            } else if (!(VerificationUtil.isMobileNO(editPhone.getText().toString()))) {
                Toast.makeText(RegisterActivity.this, "手机号格式不正确", Toast.LENGTH_SHORT).show();
                editPhone.setText("");
           } else if (TextUtils.isEmpty(editPhoneCode.getText().toString())) {
                Toast.makeText(RegisterActivity.this, "手机验证码不能为空", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(editPassword.getText().toString())) {
                Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
            }else if(!(VerificationUtil.isRightPwd(editPassword.getText().toString()))){
                Toast.makeText(RegisterActivity.this, "密码为数字和英文字母组成，长度为6-16个字符", Toast.LENGTH_SHORT).show();
            }
            else if (TextUtils.isEmpty(editConfirmPassword.getText().toString())) {
                Toast.makeText(RegisterActivity.this, "确认密码不能为空", Toast.LENGTH_SHORT).show();
            } else if (!(editPassword.getText().toString().equals(editConfirmPassword.getText().toString()))){
                Toast.makeText(RegisterActivity.this, "两次密码输入不正确请重新输入", Toast.LENGTH_SHORT).show();
                editConfirmPassword.setText("");
            }

        }
    };


    public void getData() {
        Request<String> request = NoHttp.createStringRequest(I.REGISTER, RequestMethod.POST);
        request.add("phone","17600396592");
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String loginJson = response.get();
                Log.e("ABC","----------------"+loginJson);
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
