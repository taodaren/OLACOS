package net.osplay.ui.activity.sub;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.osplay.olacos.R;
import net.osplay.ui.activity.base.BaseActivity;
import net.osplay.utils.VerificationUtil;

import static net.osplay.olacos.R.id.text_wei_xin;

/**
 * 登录
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private Button btnLogin;
    private TextView textWeiXin, textQQ, textWeiBo, textForgetPwd;
    private EditText editAccount, editPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();//加载控件
        bindEvent();//绑定监听
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        //显示需要菜单项，隐藏多余菜单项
        menu.findItem(R.id.menu_register).setVisible(true);
        menu.findItem(R.id.menu_set).setVisible(false);
        menu.findItem(R.id.menu_category).setVisible(false);
        menu.findItem(R.id.menu_code).setVisible(false);
        menu.findItem(R.id.menu_search).setVisible(false);
        menu.findItem(R.id.menu_msg).setVisible(false);
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
                finish();
                break;
        }
        return true;
    }

    private void initView() {
        setToolbar("登录", View.VISIBLE);
        btnLogin = (Button) findViewById(R.id.btn_login);
        editAccount = (EditText) findViewById(R.id.edit_account_login);
        editPassword = (EditText) findViewById(R.id.edit_password_login);
        textForgetPwd = (TextView) findViewById(R.id.text_forget);
        textWeiXin = (TextView) findViewById(text_wei_xin);
        textQQ = (TextView) findViewById(R.id.text_qq);
        textWeiBo = (TextView) findViewById(R.id.text_wei_bo);
    }

    private void bindEvent() {
        textForgetPwd.setOnClickListener(this);
        textWeiXin.setOnClickListener(this);
        textQQ.setOnClickListener(this);
        textWeiBo.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    /**
     * 点击登陆监听时的验证
     */
    public void login() {
        final String loginId = getIntent().getStringExtra("loginId");
        String account = editAccount.getText().toString();
        String password = editPassword.getText().toString();

        if (!(VerificationUtil.isMobileNO(account))) {
            editAccount.setError("请输入一个有效的手机号");
        } else if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            editPassword.setError("4到10个字母、数字或字符");
        } else {//模拟登陆
            //保存用户登录信息
            saveUser(account, password);
            final ProgressDialog dialog = new ProgressDialog(LoginActivity.this);
            dialog.setIndeterminate(true);
            dialog.setMessage("登录中...");
            dialog.show();
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            switch (loginId) {
                                case "loginTopic":
                                    startActivity(new Intent(LoginActivity.this, DetailsTopicActivity.class));
                                    finish();
                                    break;
                                case "loginDou":
                                    startActivity(new Intent(LoginActivity.this, DetailsDouPictureActivity.class));
                                    finish();
                                    break;
                                case "loginCOJ":
                                    startActivity(new Intent(LoginActivity.this, CreateOrJoinActivity.class));
                                    finish();
                                    break;
                                case "loginCOJ1":
                                    startActivity(new Intent(LoginActivity.this, CreateOrJoinActivity.class));
                                    finish();
                                    break;
                            }
                            dialog.dismiss();
                        }
                    }, 1000);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.text_forget:
                startActivity(new Intent(LoginActivity.this, ForgetPwdActivity.class));
                finish();
                break;
            case text_wei_xin:
                Toast.makeText(LoginActivity.this, textWeiXin.getText().toString(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.text_qq:
                Toast.makeText(LoginActivity.this, textQQ.getText().toString(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.text_wei_bo:
                Toast.makeText(LoginActivity.this, textWeiBo.getText().toString(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

}

