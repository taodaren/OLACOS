package net.osplay.ui.activity.sub;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

import net.osplay.app.AppHelper;
import net.osplay.app.I;
import net.osplay.olacos.R;
import net.osplay.ui.activity.base.BaseActivity;

/**
 * 创建社团
 */
public class CreateCommunityActivity extends BaseActivity {

    private EditText create_community_ed, create_area_ed, create_reason_ed;
    private Button create_submit_btn;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_community);
        preferences=getSharedPreferences("CreateCommunity",Activity.MODE_PRIVATE);
        editor=preferences.edit();
        setToolbar("创建社团", View.VISIBLE);
        initView();
        bindEvent();
    }

    private void initView() {
        create_community_ed = (EditText) findViewById(R.id.create_community_ed);
        create_area_ed = (EditText) findViewById(R.id.create_area_ed);
        create_reason_ed = (EditText) findViewById(R.id.create_reason_ed);

        create_submit_btn = (Button) findViewById(R.id.create_submit_btn);
    }

    private void bindEvent() {
        create_submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(create_community_ed.getText().toString().isEmpty()){
                    Toast.makeText(CreateCommunityActivity.this,"请输入社团名称",Toast.LENGTH_SHORT).show();
                }else if(create_area_ed.getText().toString().isEmpty()){
                    Toast.makeText(CreateCommunityActivity.this,"请输入地区",Toast.LENGTH_SHORT).show();
                }else if(create_reason_ed.getText().toString().isEmpty()){
                    Toast.makeText(CreateCommunityActivity.this,"请输入申团理由",Toast.LENGTH_SHORT).show();
                }else{
                    establishHttp();//提交创建社团信息


                   // startActivity(new Intent(CreateCommunityActivity.this,MainActivity.class));
//                    Intent show=new Intent(CreateCommunityActivity.this,MainActivity.class);
//                    show.putExtra("jgb",1);
//                    startActivity(show);
//                    editor.putString("Annotated", "olacos");
//                    editor.commit();
//                    finish();
                }
            }
        });
    }

    private void establishHttp() {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest(I.ESTABLISH, RequestMethod.POST);
        request.add("name", create_community_ed.getText().toString());
        request.add("region", create_area_ed.getText().toString());
        request.add("Reason", create_reason_ed.getText().toString());
        request.add("Headed", AppHelper.getInstance().getUser().getID());
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.e("JGB", "检查创建结果" + json);
                if (json == null) {
                    return;
                } else {
                    Toast.makeText(CreateCommunityActivity.this,"提交成功等待审核",Toast.LENGTH_SHORT).show();
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
