package net.osplay.ui.activity.sub;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import net.osplay.app.I;
import net.osplay.olacos.R;
import net.osplay.service.entity.UserLodinBean;
import net.osplay.ui.activity.base.BaseActivity;


/**
 * 登录
 */

public class LoginActivity extends BaseActivity {
    private Button btnLogin;
    private EditText editAccount, editPassword;
    private Gson gson=new Gson();
    private  UserLodinBean userLodinBean;
    private String ok;//登录成功与否判断


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setToolbar("登录", View.VISIBLE);
        initView();
    }



    private void initView() {
        btnLogin = (Button) findViewById(R.id.btn_login);
        editAccount = (EditText) findViewById(R.id.edit_account_login);
        editPassword = (EditText) findViewById(R.id.edit_password_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(editAccount.getText().toString())){
                    Toast.makeText(LoginActivity.this,"账号不能为空",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(editPassword.getText().toString())){
                    Toast.makeText(LoginActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                } else{
                    RequestQueue requestQueue = NoHttp.newRequestQueue();
                    Request<String> request = NoHttp.createStringRequest(I.LOGIN, RequestMethod.POST);
                    request.add("phone",editAccount.getText().toString());
                    request.add("password",editPassword.getText().toString());
                    requestQueue.add(0, request, new OnResponseListener<String>() {
                        @Override
                        public void onStart(int what) {

                        }
                        @Override
                        public void onSucceed(int what, Response<String> response) {
                            String json = response.get();
                            Log.e("TAG",json);
                            if(json!=null){
                                userLodinBean= gson.fromJson(json, UserLodinBean.class);
                                ok = userLodinBean.getOk();
                                if(ok.equals("false1")){
                                    Toast.makeText(LoginActivity.this,"账号不存在",Toast.LENGTH_SHORT).show();
                                }else if(ok.equals("false2")){
                                    Toast.makeText(LoginActivity.this,"密码不正确",Toast.LENGTH_SHORT).show();
                                }else{
                                    saveUser(editAccount.getText().toString(), editPassword.getText().toString());
                                    String loginId = getIntent().getStringExtra("loginId");
                                    switch (loginId) {
                                        case "loginTopic":
                                            startActivity(new Intent(LoginActivity.this,
                                                    DetailsTopicActivity.class));
                                            finish();
                                            break;
                                        case "loginDou":
                                            startActivity(new Intent(LoginActivity.this,
                                                    DetailsDouPictureActivity.class));
                                            finish();
                                            break;
                                        case "loginCOJ":
                                            startActivity(new Intent(LoginActivity.this,
                                                    CreateOrJoinActivity.class));
                                            finish();
                                            break;
                                        case "loginCOJ1":
                                            startActivity(new Intent(LoginActivity.this,
                                                    CreateOrJoinActivity.class));
                                            finish();
                                            break;
                                    }
                                }
                            }else{
                                return;
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
        });
    }



}

