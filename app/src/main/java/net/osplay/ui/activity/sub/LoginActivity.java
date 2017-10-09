package net.osplay.ui.activity.sub;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import net.osplay.app.AppHelper;
import net.osplay.app.I;
import net.osplay.data.bean.Account;
import net.osplay.olacos.R;
import net.osplay.service.entity.UserLoginBean;
import net.osplay.ui.activity.base.BaseActivity;

import java.util.List;

/**
 * 登录
 */

public class LoginActivity extends BaseActivity {
    private EditText editAccount, editPassword;
    private Gson gson = new Gson();
    private String isLoginOk;//登录成功与否判断


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

    }

    private void initView() {
        setToolbar("登录", View.VISIBLE);
        editAccount = (EditText) findViewById(R.id.edit_account_login);
        editPassword = (EditText) findViewById(R.id.edit_password_login);
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editAccount.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "账号不能为空", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(editPassword.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    getData();
                }
            }
        });
    }

    public void getData() {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest(I.LOGIN, RequestMethod.POST);
        request.add("phone", editAccount.getText().toString());
        request.add("password", editPassword.getText().toString());
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.e("JGB","login----------"+json);
                if (json != null) {
                    UserLoginBean userLoginBean = gson.fromJson(json, UserLoginBean.class);
                    isLoginOk = userLoginBean.getOk();
                    login(userLoginBean.getMember());
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

    public void login(List<Account> accounts) {
        switch (isLoginOk) {
            case "false1":
                Toast.makeText(LoginActivity.this, "账号不存在", Toast.LENGTH_SHORT).show();
                break;
            case "false2":
                Toast.makeText(LoginActivity.this, "密码不正确", Toast.LENGTH_SHORT).show();
                break;
            default:
                if (accounts != null && !accounts.isEmpty()) {
                    // save user
                    AppHelper.getInstance().setUser(accounts.get(0));
                    // set login state
                    AppHelper.getInstance().setLogined(true);
                }

                String loginId = getIntent().getStringExtra("loginId");
                switch (loginId) {
                    case "loginHeck"://签到
                        finish();
                        break;
                    case "loginAttention"://关注
                        finish();
                        break;
                    case "loginCOJ"://社团活动
                        startActivity(new Intent(LoginActivity.this,
                                CreateOrJoinActivity.class));
                        finish();
                        break;
                    case "loginCOJ1"://社团作品登录成功后跳转的目的地
                        finish();
                        break;
                    case "loginMine"://个人中心
                        startActivity(new Intent(LoginActivity.this,
                                MinePageSelfActivity.class));
                        finish();
                        break;
                }
                break;
        }
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

}