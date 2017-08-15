package net.osplay.olacos.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.osplay.olacos.R;
import net.osplay.olacos.base.BaseActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends BaseActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_REGISTER = 0;

    private Button btnLogin;
    private TextView textWeiXin, textQQ, textWeiBo;
    private EditText editAccount, editPassword;
//    private LoginBean loginBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setToolbar("登录", View.VISIBLE);
        initView();
    }

    private void initView() {
//        loginBean = new LoginBean();

        btnLogin = (Button) findViewById(R.id.btn_login);
        editAccount = (EditText) findViewById(R.id.edit_account_login);
        editPassword = (EditText) findViewById(R.id.edit_password_login);
        textWeiXin = (TextView) findViewById(R.id.text_wei_xin);
        textQQ = (TextView) findViewById(R.id.text_qq);
        textWeiBo = (TextView) findViewById(R.id.text_wei_bo);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        textWeiXin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取用户信息
                Toast.makeText(LoginActivity.this, "textWeiXin", Toast.LENGTH_SHORT).show();
            }
        });

        textQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "textQQ", Toast.LENGTH_SHORT).show();
            }
        });

        textWeiBo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "textWeiBo", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void login() {
        //如果没有验证成功，登录失败
        if (!validate()) {
            onLoginFailed();
            return;
        }

        btnLogin.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("登录中...");
        progressDialog.show();

        String account = editAccount.getText().toString();
        String password = editPassword.getText().toString();

        // TODO: 此处处理登陆逻辑

        //获取登录网络请求
//        getLoginData(account, password);

//        Log.e(TAG, "login: code" + loginBean.getCode());

//        if (loginBean.getCode() == 0) {
//            onLoginSuccess();
//            progressDialog.dismiss();
//        } else {
//            onLoginFailed();
//        }

        //模拟登录，有数据时可注掉
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        //完成调用 onLoginSuccess 或 onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_REGISTER) {
            if (resultCode == RESULT_OK) {

                //TODO: 在这里实现成功的注册逻辑
//                UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
                //默认情况下，我们刚刚完成活动并自动登录
                this.finish();
            }
        }
    }

    public void onLoginSuccess() {
        btnLogin.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "登录失败", Toast.LENGTH_LONG).show();
        btnLogin.setEnabled(true);
    }

    /**
     * 验证用户信息
     *
     * @return 验证结果
     */
    public boolean validate() {
        //是否有效
        boolean valid = true;

        String account = editAccount.getText().toString();
        String password = editPassword.getText().toString();

        //判断手机号
        if (account.isEmpty() || !(account.length() == 11) || !(isMobileNO(account))) {
            editAccount.setError("请输入一个有效的手机号");
            valid = false;
        } else {
            editAccount.setError(null);
        }

        //判断密码
        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            editPassword.setError("4到10个字母、数字或字符");
            valid = false;
        } else {
            editPassword.setError(null);
        }

        return valid;
    }

    //验证手机号是否为正确手机号
    public static boolean isMobileNO(String mobiles) {

        Pattern p = Pattern
                .compile("^(0|86|17951)?(13[0-9]|15[0-9]|17[0-9]|18[0-9]|14[0-9])[0-9]{8}$");
        Matcher m = p.matcher(mobiles);

        return m.matches();
    }

    /**
     * 创建选项菜单
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        //显示需要菜单项，隐藏多余菜单项
        menu.findItem(R.id.menu_category).setVisible(false);
        menu.findItem(R.id.menu_code).setVisible(false);
        menu.findItem(R.id.menu_msg).setVisible(false);
        menu.findItem(R.id.menu_register).setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }
        return true;
    }

//    private void getLoginData(String phoneNum, String password) {
//        HttpMethods.getInstance().getLogin(new Observer<LoginBean>() {
//            @Override
//            public void onCompleted() {
//                Log.d(TAG, "onCompleted");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d(TAG, "onError: " + e.getMessage());
//            }
//
//            @Override
//            public void onNext(LoginBean loginBean) {
//                Log.d(TAG, "onNext: loginBean" + loginBean);
//            }
//        }, phoneNum, password);
//    }

}
