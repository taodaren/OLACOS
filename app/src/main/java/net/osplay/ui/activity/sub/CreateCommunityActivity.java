package net.osplay.ui.activity.sub;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.osplay.olacos.R;
import net.osplay.ui.activity.base.BaseActivity;

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
                   // startActivity(new Intent(CreateCommunityActivity.this,MainActivity.class));

                    Intent show=new Intent(CreateCommunityActivity.this,MainActivity.class);
                    show.putExtra("jgb",1);
                    startActivity(show);
                    editor.putString("Annotated", "olacos");
                    editor.commit();
                    finish();
                }
            }
        });
    }


}
