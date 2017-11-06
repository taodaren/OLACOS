package net.osplay.ui.activity.sub;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import net.osplay.service.entity.UserCodeBean;
import net.osplay.service.entity.UserIsNickNameBean;
import net.osplay.service.entity.UserIsRegisterBean;
import net.osplay.service.entity.UserRegisterBean;
import net.osplay.ui.activity.base.BaseActivity;
import net.osplay.utils.TimeCountUtil;
import net.osplay.utils.VerificationUtil;

public class RegisterActivity extends BaseActivity {
    private Button btn_over, btn_get_code;
    private EditText editAccount, editPhone, editPhoneCode, editPassword, editConfirmPassword;
    private Gson mGson = new Gson();
    private RequestQueue requestQueue = NoHttp.newRequestQueue();
    private boolean total;//验证手机号是否注册过
    private String code1;
    private TimeCountUtil mTimeCountUtil;//验证码倒计时


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setToolbar("注册", View.VISIBLE);
        initView();
        bindListener();
    }

    private void bindListener() {
        btn_over.setOnClickListener(mOnClickListener);
        btn_get_code.setOnClickListener(mOnClickListener);
    }

    private void initView() {
        editAccount = (EditText) findViewById(R.id.edit_account_register);//用户名
        editPhone = (EditText) findViewById(R.id.edit_phone_register);//手机号
        editPhoneCode = (EditText) findViewById(R.id.edit_phone_code);//手机验证码
        editPassword = (EditText) findViewById(R.id.edit_password_register);//密码
        editConfirmPassword = (EditText) findViewById(R.id.edit_confirm_password_register);//确认密码
        btn_get_code = (Button) findViewById(R.id.btn_get_code);//发送验证码
        btn_over = (Button) findViewById(R.id.btn_over);//完成
        mTimeCountUtil = new TimeCountUtil(btn_get_code, 60000, 1000);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_over:
                    if (TextUtils.isEmpty(editAccount.getText().toString())) {
                        Toast.makeText(RegisterActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                    } else if (editAccount.getText().toString().length() > 10) {
                        Toast.makeText(RegisterActivity.this, "用户名长度不能超过10个字符", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(editPhone.getText().toString())) {
                        Toast.makeText(RegisterActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    } else if (!(VerificationUtil.isMobileNO(editPhone.getText().toString()))) {
                        Toast.makeText(RegisterActivity.this, "手机号格式不正确", Toast.LENGTH_SHORT).show();
                        editPhone.setText("");
                    } else if (TextUtils.isEmpty(editPhoneCode.getText().toString())) {
                        Toast.makeText(RegisterActivity.this, "手机验证码不能为空", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(editPassword.getText().toString())) {
                        Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    } else if (!(VerificationUtil.isRightPwd(editPassword.getText().toString()))) {
                        Toast.makeText(RegisterActivity.this, "密码为数字和英文字母组成，长度为6-16个字符", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(editConfirmPassword.getText().toString())) {
                        Toast.makeText(RegisterActivity.this, "确认密码不能为空", Toast.LENGTH_SHORT).show();
                    } else if (!(editPassword.getText().toString().equals(editConfirmPassword.getText().toString()))) {
                        Toast.makeText(RegisterActivity.this, "两次密码输入不正确请重新输入", Toast.LENGTH_SHORT).show();
                        editConfirmPassword.setText("");
                    } else {
                        getNichName();
                    }
                    break;
                case R.id.btn_get_code:
                    if (TextUtils.isEmpty(editPhone.getText().toString())) {
                        Toast.makeText(RegisterActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    } else if (!(VerificationUtil.isMobileNO(editPhone.getText().toString()))) {
                        Toast.makeText(RegisterActivity.this, "手机号格式不正确", Toast.LENGTH_SHORT).show();
                        editPhone.setText("");
                    } else {
                        mTimeCountUtil.start();
                        getCode();
                    }
                    break;
            }
        }
    };

    public void getNichName() {
        Request<String> request = NoHttp.createStringRequest(I.IS_NICKNAME, RequestMethod.POST);
        request.add("nickName", editAccount.getText().toString());
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String isName = response.get();

                Log.e("JGB", "-----------is:" + isName);
                if (isName != null) {
                    formatName(isName);
                } else {
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

    private void formatName(String isName) {
        UserIsNickNameBean userIsNickNameBean = mGson.fromJson(isName, UserIsNickNameBean.class);
        String code = userIsNickNameBean.getCode();
        if (code.equals("true")) {
            Toast.makeText(RegisterActivity.this, "用户名已存在", Toast.LENGTH_SHORT).show();
        } else {
            getData();
        }
    }

    //解析手机号是否注册
    public void getData() {
        Request<String> request = NoHttp.createStringRequest(I.IS_REGISTER, RequestMethod.POST);
        request.add("phone", editPhone.getText().toString());
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String isRegister = response.get();
                Log.e("TAG", "-----------" + isRegister);
                if (isRegister != null) {
                    formatIsRegisterJson(isRegister);
                } else {
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

    //解析当前手机号是否注册过
    private void formatIsRegisterJson(String isRegister) {
        UserIsRegisterBean userIsRegisterBean = mGson.fromJson(isRegister, UserIsRegisterBean.class);
        total = userIsRegisterBean.isTotal();
        if (total == true) {
            Toast.makeText(RegisterActivity.this, "手机号已经被注册", Toast.LENGTH_SHORT).show();
        } else {
            submitRegisterInfo();
        }
    }

    //提交用户信息
    private void submitRegisterInfo() {
        if (!(editPhoneCode.getText().toString().equals(code1))) {
            Toast.makeText(RegisterActivity.this, "验证码不正确", Toast.LENGTH_SHORT).show();
        } else {
            Request<String> request = NoHttp.createStringRequest(I.REGISTER, RequestMethod.POST);
            request.add("NICK_NAME", editAccount.getText().toString());
            request.add("PHONE", editPhone.getText().toString());
            request.add("PASSWORD", editPassword.getText().toString());
            requestQueue.add(0, request, new OnResponseListener<String>() {
                @Override
                public void onStart(int what) {
                }

                @Override
                public void onSucceed(int what, Response<String> response) {
                    String json = response.get();
                    Log.e("JGB", "submit----------" + json);
                    if (json != null) {
                        formatSubmit(json);
                    } else {
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

    private void formatSubmit(String json) {
        UserRegisterBean userRegisterBean = mGson.fromJson(json, UserRegisterBean.class);
        String registCode = userRegisterBean.getCode();
        if (registCode.equals("true")) {
            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
            Intent i = new Intent();
            i.putExtra("phone", editPhone.getText().toString());
            i.putExtra("password", editPassword.getText().toString());
            setResult(4, i);
            finish();
            // finish();
        }
    }

    public void getCode() {
        final Request<String> request = NoHttp.createStringRequest(I.VERIFICATION_CODE, RequestMethod.POST);
        request.add("phone", editPhone.getText().toString());
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String code = response.get();
                Log.e("JGB", "-----------" + code);
                if (code == null) {
                    return;
                } else {
                    formatCodeJson(code);
                    Toast.makeText(RegisterActivity.this, "验证码已发送，请注意查收!", Toast.LENGTH_SHORT).show();
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

    private void formatCodeJson(String code) {
        UserCodeBean userCodeBean = mGson.fromJson(code, UserCodeBean.class);
        code1 = userCodeBean.getCode();
    }

}
