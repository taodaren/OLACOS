package net.osplay.ui.activity.sub;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import net.osplay.app.AppHelper;
import net.osplay.app.I;
import net.osplay.olacos.R;
import net.osplay.ui.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class EditInfoActivity extends BaseActivity  {


    @BindView(R.id.text_city)
    TextView textCity;
    @BindView(R.id.layout_city)
    RelativeLayout layoutCity;
    @BindView(R.id.title_toolbar)
    TextView titleToolbar;
    @BindView(R.id.tab_layout_toolbar)
    TabLayout tabLayoutToolbar;
    @BindView(R.id.main_toolbar)
    Toolbar mainToolbar;
    @BindView(R.id.mine_avatar)
    CircleImageView mineAvatar;
    @BindView(R.id.edit_info_avatar)
    LinearLayout editInfoAvatar;
    @BindView(R.id.edit_info_tag)
    LinearLayout editInfoTag;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.edit_info_name)
    LinearLayout editInfoName;
    @BindView(R.id.age_tv)
    TextView ageTv;
    @BindView(R.id.edit_info_age)
    LinearLayout editInfoAge;
    @BindView(R.id.xingxuo_tv)
    TextView xingxuoTv;
    @BindView(R.id.edit_info_xing)
    LinearLayout editInfoXing;
    @BindView(R.id.area_tv)
    TextView areaTv;
    @BindView(R.id.edit_info_area)
    LinearLayout editInfoArea;
    @BindView(R.id.Certification)
    TextView Certification;
    @BindView(R.id.shiming_tv)
    ImageView shimingTv;
    @BindView(R.id.edit_info_real_name)
    LinearLayout editInfoRealName;
    private String shenhe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);
        ButterKnife.bind(this);
        setToolbar("编辑资料", View.VISIBLE);
        setUserInfo();

    }

    private void setUserInfo() {
        if (AppHelper.getInstance().isLogined()) {
            Glide.with(EditInfoActivity.this).load(I.BASE_URL + AppHelper.getInstance().getUser().getHEAD_PATH()).into(mineAvatar);
            nameTv.setText(AppHelper.getInstance().getUser().getCN());
            ageTv.setText(AppHelper.getInstance().getUser().getBIRTHDAY());
            xingxuoTv.setText(AppHelper.getInstance().getUser().getXINGZUO());
            areaTv.setText(AppHelper.getInstance().getUser().getLOCAL_DRESS());
            shenhe = AppHelper.getInstance().getUser().getSHENHE();
            switch (shenhe) {
                case "0":
                    Certification.setText("已审核");
                    break;
                case "1":
                    Certification.setText("审核未通过");
                    break;
                case "2":
                    Certification.setText("待审核");

                    break;
            }
        }
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



    @OnClick({R.id.name_tv, R.id.age_tv, R.id.xingxuo_tv, R.id.area_tv,R.id.Certification})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.name_tv:
                Intent intent =new  Intent(EditInfoActivity.this,ChangeNameActivity.class);
                intent.putExtra("nameTv",nameTv.getText().toString());
                startActivityForResult(intent,1);
                break;
            case R.id.age_tv:
                Intent intent2 =new  Intent(EditInfoActivity.this,ChangeAgeActivity.class);
                intent2.putExtra("ageTv",ageTv.getText().toString());
                startActivityForResult(intent2,2);
                break;
            case R.id.xingxuo_tv:
                Intent intent3 =new  Intent(EditInfoActivity.this,ChangeXingzuoActivity.class);
                intent3.putExtra("xingzuoTv",xingxuoTv.getText().toString());
                startActivityForResult(intent3,3);
                break;
            case R.id.area_tv:
                Intent intent4 =new  Intent(EditInfoActivity.this,ChangeAreaActivity.class);
                intent4.putExtra("area_tv",areaTv.getText().toString());
                startActivityForResult(intent4,4);
                break;
            case R.id.Certification:
                if(shenhe.equals(2)){
                    startActivity(new Intent(EditInfoActivity.this, EditRealNameActivity.class));
                }else{
                   Toast.makeText(EditInfoActivity.this,"您已实名认证过",Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1){
           if(data.getStringExtra("returnName")!=null){
               nameTv.setText(data.getStringExtra("returnName"));
           }

        }
    }
}
