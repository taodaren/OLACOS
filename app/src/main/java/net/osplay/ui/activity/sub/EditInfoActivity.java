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

import com.bumptech.glide.Glide;

import net.osplay.app.AppHelper;
import net.osplay.app.I;
import net.osplay.olacos.R;
import net.osplay.ui.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class EditInfoActivity extends BaseActivity implements View.OnClickListener {


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);
        ButterKnife.bind(this);
        setToolbar("编辑资料", View.VISIBLE);
        findViewById(R.id.edit_info_real_name).setOnClickListener(this);
        setUserInfo();
    }
    private void setUserInfo() {
        if (AppHelper.getInstance().isLogined()) {
            Glide.with(EditInfoActivity.this).load(I.BASE_URL + AppHelper.getInstance().getUser().getHEAD_PATH()).into(mineAvatar);
            nameTv.setText(AppHelper.getInstance().getUser().getCN());
            ageTv.setText(AppHelper.getInstance().getUser().getBIRTHDAY());
            xingxuoTv.setText(AppHelper.getInstance().getUser().getXINGZUO());
            areaTv.setText(AppHelper.getInstance().getUser().getLOCAL_DRESS());
//            shimingTv.setText(AppHelper.getInstance().getUser().);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_info_real_name:
                startActivity(new Intent(EditInfoActivity.this, EditRealNameActivity.class));
                break;
        }
    }
}
