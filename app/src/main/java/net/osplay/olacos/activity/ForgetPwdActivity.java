package net.osplay.olacos.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.osplay.olacos.R;
import net.osplay.olacos.base.BaseActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 修改密码
 */

public class ForgetPwdActivity extends BaseActivity {
    private static final String TAG = "ForgetPwdActivity";
    private Button btnGetCode, btnOver;
    private EditText editPhone, editPhoneCode, editPassword, editConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);

        setToolbar("修改密码", View.VISIBLE);
        setEditText();
        setClickListener();
    }

    private void setEditText() {
        editPhone = (EditText) findViewById(R.id.edit_phone_forget);
        editPhoneCode = (EditText) findViewById(R.id.edit_phone_code_forget);
        editPassword = (EditText) findViewById(R.id.edit_password_forget);
        editConfirmPassword = (EditText) findViewById(R.id.edit_confirm_password_forget);
    }

    private void setClickListener() {
        //获取验证码
        btnGetCode = (Button) findViewById(R.id.btn_get_code_forget);
        btnGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                getRegisterCodeData(String.valueOf(editPhone.getText()));
                Toast.makeText(ForgetPwdActivity.this, "获取验证码", Toast.LENGTH_SHORT).show();
            }
        });
        //注册完成
        btnOver = (Button) findViewById(R.id.btn_over_forget);
        btnOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                overForget();
            }
        });
    }

    private void overForget() {
        if (!validate()) {
            onRegisterFailed();
            return;
        }

        btnOver.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(ForgetPwdActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("修改密码中...");
        progressDialog.show();

        String phone = editPhone.getText().toString();
        String phoneCode = editPhoneCode.getText().toString();
        String password = editPassword.getText().toString();
        String confirmPassword = editConfirmPassword.getText().toString();

        // TODO: 在这里实现你自己的忘记密码逻辑
        //发送网络请求

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        //完全调用 onRegisterSuccess 或 onRegisterFailed
                        //这里取决于成功
                        onRegisterSuccess();
                        // onRegisterFailed();
                        progressDialog.dismiss();
                    }
                }, 2000);
    }

    /**
     * 验证方法
     */
    public boolean validate() {
        boolean valid = true;

        String phone = editPhone.getText().toString();
        String phoneCode = editPhoneCode.getText().toString();
        String password = editPassword.getText().toString();
        String confirmPassword = editConfirmPassword.getText().toString();

        //判断手机号
        if (phone.isEmpty() || !(phone.length() == 11) || !(isMobileNO(phone))) {
            editPhone.setError("请输入一个有效的手机号");
            valid = false;
        } else {
            editPhone.setError(null);
        }

        //判断验证码
        if (phoneCode.isEmpty() || !(phoneCode.length() == 6)) {
            editPhoneCode.setError("请输入正确验证码");
            valid = false;
        } else {
            editPhoneCode.setError(null);
        }

        //判断密码
        if (password.isEmpty()) {
            editPassword.setError("请输入正确密码");
        } else {
            editPassword.setError(null);
        }

        //判断验证密码
        if (confirmPassword.isEmpty()) {
            editConfirmPassword.setError("请输入正确确认密码");
        } else {
            editConfirmPassword.setError(null);
        }

        return valid;
    }

    /**
     * 验证手机号是否为正确手机号
     */
    public static boolean isMobileNO(String mobiles) {

        Pattern p = Pattern
                .compile("^(0|86|17951)?(13[0-9]|15[0-9]|17[0-9]|18[0-9]|14[0-9])[0-9]{8}$");
        Matcher m = p.matcher(mobiles);

        return m.matches();
    }


    /**
     * 修改密码成功
     */
    public void onRegisterSuccess() {
        btnOver.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    /**
     * 修改密码失败
     */
    public void onRegisterFailed() {
        Toast.makeText(getBaseContext(), "修改密码失败", Toast.LENGTH_LONG).show();
        btnOver.setEnabled(true);
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

}
