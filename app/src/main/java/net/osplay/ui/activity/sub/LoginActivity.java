package net.osplay.ui.activity.sub;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.osplay.olacos.R;
import net.osplay.ui.activity.base.BaseActivity;
import net.osplay.utils.Verification;

import static net.osplay.olacos.R.id.text_wei_xin;

public class LoginActivity extends BaseActivity {
    private Button btnLogin;
    private TextView textWeiXin, textQQ, textWeiBo, textForgetPwd;
    private EditText editAccount, editPassword;
    private CheckBox cb_remember_login, cb_gtlogin_login;
    private SharedPreferences sp;
    private SharedPreferences.Editor ed;
    private String account;
    private String password;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setToolbar("登录", View.VISIBLE);
        initView();
        check();
        bindEvent();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//创建选项菜单
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        //显示需要菜单项，隐藏多余菜单项
        menu.findItem(R.id.menu_set).setVisible(false);
        menu.findItem(R.id.menu_category).setVisible(false);
        menu.findItem(R.id.menu_code).setVisible(false);
        menu.findItem(R.id.menu_msg).setVisible(false);
        menu.findItem(R.id.menu_register).setVisible(true);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//菜单监听
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



    private void initView() {//加载控件
        btnLogin = (Button) findViewById(R.id.btn_login);
        editAccount = (EditText) findViewById(R.id.edit_account_login);
        editPassword = (EditText) findViewById(R.id.edit_password_login);
        textForgetPwd = (TextView) findViewById(R.id.text_forget);
        textWeiXin = (TextView) findViewById(text_wei_xin);
        textQQ = (TextView) findViewById(R.id.text_qq);
        textWeiBo = (TextView) findViewById(R.id.text_wei_bo);
        cb_remember_login = (CheckBox) findViewById(R.id.cb_remember_login);
        cb_gtlogin_login = (CheckBox) findViewById(R.id.cb_gtlogin_login);

    }
    private void bindEvent() {//绑定监听
        textForgetPwd.setOnClickListener(mOnClickListener);
        textWeiXin.setOnClickListener(mOnClickListener);
        textQQ.setOnClickListener(mOnClickListener);
        textWeiBo.setOnClickListener(mOnClickListener);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {//监听回调
        @Override
        public void onClick(View view) {
            switch (view.getId()){
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
    };

    public void login(){//点击登陆监听时的验证
        account = editAccount.getText().toString();
        password = editPassword.getText().toString();
        if(account.isEmpty() || !(account.length() == 11) || !(Verification.isMobileNO(account))){
            editAccount.setError("请输入一个有效的手机号");
        }else if(password.isEmpty() || password.length() < 4 || password.length() > 10){
            editPassword.setError("4到10个字母、数字或字符");
        }else{//模拟登陆
            //保存用户登录信息
            saveUser(account,password);
            final ProgressDialog pd = new ProgressDialog(LoginActivity.this);
            pd.setIndeterminate(true);
            pd.setMessage("登录中...");
            pd.show();
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            startActivity(new Intent(LoginActivity.this,CreateCommunityActivity.class));
                            finish();
                            ed.putString("Username",account);
                            ed.putString("password",password);
                            ed.commit();
                            pd.dismiss();
                        }
                    }, 3000);
        }
    }


    //只有点击复选框的监听时才会存储信息   如果在文本框中在添加信息 不点击的话 将不会保存
    void check(){
        sp=getSharedPreferences("user",Context.MODE_PRIVATE);
        ed=sp.edit();
        cb_gtlogin_login.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(cb_gtlogin_login.isChecked()){
                    ed.putString("name",account);
                    ed.putString("password",password);
                    ed.putBoolean("jizhu",true);
                    ed.putBoolean("denglu",true);
                    ed.commit();
                }else{
                    ed.putBoolean("denglu", false);
                    cb_remember_login.setChecked(false);
                    ed.commit();
                }
            }
        });

        cb_remember_login.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(cb_remember_login.isChecked()){
                    ed.putString("name",account);
                    ed.putString("password",password);
                    ed.putBoolean("jizhu",true);
                    ed.commit();
                }else{
                    ed.putBoolean("jizhu", false);
                    ed.commit();
                }
            }
        });
        if(sp.getBoolean("denglu",false)){
            String names=sp.getString("name", "");
            String pass=sp.getString("password", "");
            editAccount.setText(names);
            editPassword.setText(pass);
            cb_gtlogin_login.setChecked(true);
            cb_remember_login.setChecked(true);
//				Toast.makeText(LoginActivity.this, "正在登录中", 0).show();

            final ProgressDialog pd=new ProgressDialog(LoginActivity.this);
            pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pd.show();
            new Thread(new Runnable() {
                public void run() {
                    int count=20;
                    pd.setMax(count);
                    for(int i=0;i<20;i++){
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        pd.setProgress(pd.getProgress()+1);
                    }
                    pd.dismiss();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    finish();


                }

            }).start();
        }else if(sp.getBoolean("jizhu",false)){
            String names=sp.getString("name", "");
            String pass=sp.getString("password", "");
            editAccount.setText(names);
            editPassword.setText(pass);
            cb_remember_login.setChecked(true);
        }
    }



    //当退出当前程序时 判断复选框的状态 只要是勾选就执行 勾选中的功能
    protected void onStop() {
        super.onStop();
        if(cb_gtlogin_login.isChecked()){
            ed.putString("name",account);
            ed.putString("password",password);
            ed.putBoolean("jizhu",true);
            ed.putBoolean("denglu",true);
            ed.commit();
        }else{
            ed.putBoolean("denglu", false);

            ed.commit();
        }
        if(cb_remember_login.isChecked()){
            ed.putString("name",account);
            ed.putString("password",password);
            ed.putBoolean("jizhu",true);
            ed.commit();
        }else{
            ed.putBoolean("jizhu", false);
            ed.commit();
        }
    }
}

