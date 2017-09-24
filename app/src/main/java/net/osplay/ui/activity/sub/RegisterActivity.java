package net.osplay.ui.activity.sub;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.osplay.olacos.R;
import net.osplay.ui.activity.base.BaseActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends BaseActivity {
    private Button btn_over, btn_get_code;
    private EditText editAccount, editPhone, editPhoneCode, editPassword, editConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setToolbar("注册", View.VISIBLE);
        initView();
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
}
