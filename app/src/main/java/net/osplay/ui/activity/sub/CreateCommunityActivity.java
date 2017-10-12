package net.osplay.ui.activity.sub;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建社团
 */
public class CreateCommunityActivity extends BaseActivity {


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
    @BindView(R.id.create_community_ed)//社团名称
            EditText createCommunityEd;
    @BindView(R.id.create_area_tv)//团长姓名
            EditText createAreaTv;
    @BindView(R.id.create_introduction_ed)//社团简介
            EditText createIntroductionEd;
    @BindView(R.id.create_signature_ed)//社团签名
            EditText createSignatureEd;
    @BindView(R.id.create_area_ed)//所在地区
            EditText createAreaEd;
    @BindView(R.id.create_reason_ed)//申请理由
            EditText createReasonEd;
    @BindView(R.id.create_avater_btn)
    TextView createAvaterBtn;
    @BindView(R.id.create_avater_iv)//社团头像
            ImageView createAvaterIv;
    @BindView(R.id.create_background_btn)
    TextView createBackgroundBtn;
    @BindView(R.id.create_background_iv)//社团背景
            ImageView createBackgroundIv;
    @BindView(R.id.create_submit_btn)
    Button createSubmitBtn;
    private List<LocalMedia> avaterList;
    private List<LocalMedia> backgroupList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_community);
        ButterKnife.bind(this);
        setToolbar("创建社团", View.VISIBLE);

    }

    @OnClick({R.id.create_avater_iv, R.id.create_background_iv, R.id.create_submit_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.create_avater_iv:
                PictureSelector.create(CreateCommunityActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(1)// 最大图片选择数量 int
                        .previewImage(true)//预览图片
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                break;
            case R.id.create_background_iv:
                PictureSelector.create(CreateCommunityActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(1)// 最大图片选择数量 int
                        .previewImage(true)//预览图片
                        .forResult(PictureConfig.REQUEST_CAMERA);
                break;
            case R.id.create_submit_btn:
                if (createCommunityEd.getText().toString().isEmpty()) {
                    Toast.makeText(CreateCommunityActivity.this, "请输入社团名称", Toast.LENGTH_SHORT).show();
                } else if (createAreaTv.getText().toString().isEmpty()) {
                    Toast.makeText(CreateCommunityActivity.this, "请输入团长姓名", Toast.LENGTH_SHORT).show();
                } else if (createIntroductionEd.getText().toString().isEmpty()) {
                    Toast.makeText(CreateCommunityActivity.this, "请输入社团简介", Toast.LENGTH_SHORT).show();
                }if (createSignatureEd.getText().toString().isEmpty()) {
                    Toast.makeText(CreateCommunityActivity.this, "请输入社团签名", Toast.LENGTH_SHORT).show();
                } else if (createAreaEd.getText().toString().isEmpty()) {
                    Toast.makeText(CreateCommunityActivity.this, "请输入地区", Toast.LENGTH_SHORT).show();
                } else if (createReasonEd.getText().toString().isEmpty()) {
                    Toast.makeText(CreateCommunityActivity.this, "请输入申请理由", Toast.LENGTH_SHORT).show();
                } else if (avaterList == null) {
                    Toast.makeText(CreateCommunityActivity.this, "请设置社团头像", Toast.LENGTH_SHORT).show();
                } else if (backgroupList == null) {
                    Toast.makeText(CreateCommunityActivity.this, "请设置社团背景", Toast.LENGTH_SHORT).show();
                } else {
                    establishHttp();//提交创建社团信息
                }
                break;
        }
    }

    private void establishHttp() {
        Log.e("JGB", "头像:" + avaterList);
        Log.e("JGB", "头像:" + backgroupList);
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest(I.ESTABLISH, RequestMethod.POST);
        request.add("name", createCommunityEd.getText().toString());
        request.add("region", createAreaEd.getText().toString());
        request.add("reason", createReasonEd.getText().toString());
        request.add("headId", AppHelper.getInstance().getUser().getID());
        request.add("photo", avaterList.get(0).getPath());
        request.add("background", backgroupList.get(0).getPath());
        request.add("introduction", createIntroductionEd.getText().toString());
        request.add("autograph", createSignatureEd.getText().toString());
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
                    Toast.makeText(CreateCommunityActivity.this, "提交成功等待审核", Toast.LENGTH_SHORT).show();
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

    //图片结果回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    avaterList = PictureSelector.obtainMultipleResult(data);
                    Glide.with(CreateCommunityActivity.this).load(avaterList.get(0).getPath()).into(createAvaterIv);
                    break;
                case PictureConfig.REQUEST_CAMERA:
                    backgroupList = PictureSelector.obtainMultipleResult(data);
                    Glide.with(CreateCommunityActivity.this).load(backgroupList.get(0).getPath()).into(createBackgroundIv);
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
}
