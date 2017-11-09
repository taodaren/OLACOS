package net.osplay.ui.activity.sub;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.yanzhenjie.nohttp.FileBinary;
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
import net.osplay.service.entity.IsCheckBean;
import net.osplay.service.entity.PhotoBean;
import net.osplay.ui.activity.base.BaseActivity;
import net.osplay.utils.SharedPreferencesUtils;
import net.osplay.utils.VerificationUtil;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import scut.carson_ho.searchview.ICallBack;

/**
 * 编辑资料 → 实名认证
 */

public class EditRealNameActivity extends BaseActivity {


    @BindView(R.id.certification_name_ed)
    EditText certificationNameEd;//真实姓名
    @BindView(R.id.certification_id_card_ed)
    EditText certificationIdCardEd;//身份证号
    @BindView(R.id.certification_positive_iv)
    ImageView certificationPositiveIv;//身份证正面照
    @BindView(R.id.certification_anti_iv)
    ImageView certificationAntiIv;//身份证背面照
    @BindView(R.id.certification_yes_rb)
    RadioButton certificationYesRb;//是学生
    @BindView(R.id.certification_no_rb)
    RadioButton certificationNoRb;//不是学生
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.certification_student_iv)
    ImageView certificationStudentIv;//学生证照片
    @BindView(R.id.certification_student_ll)
    LinearLayout certificationStudentLl;//展开学生照片
    @BindView(R.id.certification_submit_bt)
    Button certificationSubmitBt;//提交
    @BindView(R.id.certification_school_ed)
    EditText certificationSchoolEd;//所在学校
    @BindView(R.id.certification_school_ll)
    LinearLayout certificationSchoolLl;//学校的显示与隐藏
    private List<LocalMedia> positiveList;
    private List<LocalMedia> antiList;
    private List<LocalMedia> studentList;
    private String posiveUrl;
    private String studentUrl;
    private Gson mGson = new Gson();
    int bs=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_real_name);
        ButterKnife.bind(this);
        setToolbar("实名认证", View.VISIBLE);
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

    @OnClick({R.id.certification_positive_iv, R.id.certification_anti_iv, R.id.certification_yes_rb, R.id.certification_no_rb, R.id.certification_student_iv, R.id.certification_submit_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.certification_positive_iv://身份证正面照
                PictureSelector.create(EditRealNameActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(1)// 最大图片选择数量 int
                        .previewImage(true)//预览图片
                        .compress(true)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                break;
            case R.id.certification_anti_iv://身份证背面照
                PictureSelector.create(EditRealNameActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(1)// 最大图片选择数量 int
                        .previewImage(true)//预览图片
                        .compress(true)
                        .forResult(PictureConfig.REQUEST_CAMERA);
                break;
            case R.id.certification_yes_rb://是学生
                certificationStudentLl.setVisibility(View.VISIBLE);
                certificationSchoolLl.setVisibility(View.VISIBLE);
                bs=1;
                break;
            case R.id.certification_no_rb://不是学生
                certificationStudentLl.setVisibility(View.GONE);
                certificationSchoolLl.setVisibility(View.GONE);
                break;
            case R.id.certification_student_iv://学生证照片
                PictureSelector.create(EditRealNameActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(1)// 最大图片选择数量 int
                        .previewImage(true)//预览图片
                        .compress(true)
                        .forResult(PictureConfig.CAMERA);
                break;
            case R.id.certification_submit_bt://提交
                if (certificationNameEd.getText().toString().isEmpty()) {
                    Toast.makeText(EditRealNameActivity.this, "姓名不能为空", Toast.LENGTH_SHORT).show();
                } else if (certificationIdCardEd.getText().toString().isEmpty()) {
                    Toast.makeText(EditRealNameActivity.this, "身份证号不能为空", Toast.LENGTH_SHORT).show();
                } else if (!VerificationUtil.checkIdCard(certificationIdCardEd.getText().toString())) {
                    Toast.makeText(EditRealNameActivity.this, "身份证号格式不正确", Toast.LENGTH_SHORT).show();
                    certificationIdCardEd.getText().clear();
                } else if (positiveList == null) {
                    Toast.makeText(EditRealNameActivity.this, "请上传身份证正面照片", Toast.LENGTH_SHORT).show();
                } else if (antiList == null) {
                    Toast.makeText(EditRealNameActivity.this, "请上传身份证反面照片", Toast.LENGTH_SHORT).show();
                } else if (!(certificationYesRb.isChecked() | certificationNoRb.isChecked())) {
                    Toast.makeText(EditRealNameActivity.this, "请选择是否为学生", Toast.LENGTH_SHORT).show();
                } else if (certificationYesRb.isChecked()) {
                    if (studentList == null) {
                        Toast.makeText(EditRealNameActivity.this, "请上传学生证照片", Toast.LENGTH_SHORT).show();
                    }else if(certificationSchoolEd.getText().toString().isEmpty()){
                        Toast.makeText(EditRealNameActivity.this, "学校名称不能为空", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        //studioIvHttp();
                        positiveIvHttp();
                       // antiIvHttp();
                    }
                } else {
                    positiveIvHttp();

                }
                break;
        }
    }

    //图片结果回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    positiveList = PictureSelector.obtainMultipleResult(data);
                    Glide.with(EditRealNameActivity.this).load(positiveList.get(0).getPath()).into(certificationPositiveIv);
                    break;
                case PictureConfig.REQUEST_CAMERA:
                    antiList = PictureSelector.obtainMultipleResult(data);
                    Glide.with(EditRealNameActivity.this).load(antiList.get(0).getPath()).into(certificationAntiIv);
                    break;
                case PictureConfig.CAMERA:
                    studentList = PictureSelector.obtainMultipleResult(data);
                    Glide.with(EditRealNameActivity.this).load(studentList.get(0).getPath()).into(certificationStudentIv);
                    break;

            }
        }
    }


    //上传背面身份证图片
    private void antiIvHttp(final String positive) {
        //得到图片路径后上传服务器
        RequestQueue requestQueue2 = NoHttp.newRequestQueue();
        Request<String> request2 = NoHttp.createStringRequest(I.PHOTO, RequestMethod.POST);
        request2.add("url", new FileBinary(new File(antiList.get(0).getCompressPath())));//上传文件
        requestQueue2.add(0, request2, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.e("JGB", "上传背面图片结果" + json);
                if (json == null) {
                    return;
                } else {
                    PhotoBean photoBean = mGson.fromJson(json, PhotoBean.class);
                    String back = photoBean.getFilelist().get(0).getURL();//背面照
                    if(bs==1){//bs==1说明是学生
                        studioIvHttp(positive,back);
                    }else{
                        notStudentploadHttp(positive,back);
                    }
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
    //上传正面身份证图片
    private void positiveIvHttp() {
        RequestQueue requestQueue1 = NoHttp.newRequestQueue();
        Request<String> request1 = NoHttp.createStringRequest(I.PHOTO, RequestMethod.POST);
        request1.add("url", new FileBinary(new File(this.positiveList.get(0).getCompressPath())));//上传文件
        requestQueue1.add(0, request1, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.e("JGB", "上传正面文件结果" + json);
                if (json == null) {
                    return;
                } else {
                    PhotoBean photoBean = mGson.fromJson(json, PhotoBean.class);
                    String positive = photoBean.getFilelist().get(0).getURL();//正面学生照
                    antiIvHttp(positive);
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
    //非学生上传
    private void notStudentploadHttp(String positive, String back) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest(I.VERIFIED, RequestMethod.POST);
        request.add("ID", AppHelper.getInstance().getUser().getID());
        request.add("CN",certificationNameEd.getText().toString() );
        request.add("CARD", certificationIdCardEd.getText().toString());
        request.add("CARD_F_PATH", positive);
        request.add("CARD_B_PATH", back);
        request.add("STU_PATH", "");
        request.add("SCHOOL", "");
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                IsCheckBean isCheckBean = mGson.fromJson(json, IsCheckBean.class);
                if(isCheckBean.getCode().equals("true")){
                    Log.e("JGB", "请求到这里" );
                    Log.e("JGB", "实名认证结果" + json);
                    Toast.makeText(EditRealNameActivity.this,"信息已提交请等待审核",Toast.LENGTH_SHORT).show();
                    SharedPreferencesUtils.setParam(EditRealNameActivity.this, "NOTSTUDENT","100" );
                    finish();
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

    //上传学生证图片
    private void studioIvHttp(final String positive, final String back) {
        RequestQueue requestQueue2 = NoHttp.newRequestQueue();
        Request<String> request2 = NoHttp.createStringRequest(I.PHOTO, RequestMethod.POST);
        request2.add("url", new FileBinary(new File(studentList.get(0).getCompressPath())));//上传文件
        requestQueue2.add(0, request2, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.e("JGB", "上传学生证图片结果" + json);
                if (json == null) {
                    return;
                } else {
                    PhotoBean photoBean = mGson.fromJson(json, PhotoBean.class);
                    String studentim = photoBean.getFilelist().get(0).getURL();
                    studentLoadHttp(positive,back,studentim);
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
    //学生上传
    private void studentLoadHttp(String positive, String back, String studentim) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest(I.VERIFIED, RequestMethod.POST);
        request.add("ID", AppHelper.getInstance().getUser().getID());
        request.add("CN", AppHelper.getInstance().getUser().getCN());
        request.add("CARD", certificationIdCardEd.getText().toString());
        request.add("CARD_F_PATH", positive);
        request.add("CARD_B_PATH", back);
        request.add("STU_PATH", studentim);
        request.add("SCHOOL", certificationSchoolEd.getText().toString());
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                IsCheckBean isCheckBean = mGson.fromJson(json, IsCheckBean.class);
                if(isCheckBean.getCode().equals("true")){
                    Log.e("JGB", "请求到这里" );
                    Log.e("JGB", "学生实名认证结果" + json);
                    SharedPreferencesUtils.setParam(EditRealNameActivity.this, "STUDENT","200" );
                    Toast.makeText(EditRealNameActivity.this,"信息已提交请等待审核",Toast.LENGTH_SHORT).show();
                    finish();
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
