package net.osplay.ui.activity.sub;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lljjcoder.city_20170724.CityPickerView;
import com.lljjcoder.city_20170724.bean.CityBean;
import com.lljjcoder.city_20170724.bean.DistrictBean;
import com.lljjcoder.city_20170724.bean.ProvinceBean;
import com.lljjcoder.citylist.CityListSelectActivity;
import com.lljjcoder.citylist.bean.CityInfoBean;
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

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 全部个人信息
 */
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
            Glide.with(EditInfoActivity.this).load(I.BASE_URL + AppHelper.getInstance().getUser().getHEAD_PATH()).into(mineAvatar);
            nameTv.setText(AppHelper.getInstance().getUser().getCN());
            ageTv.setText(AppHelper.getInstance().getUser().getBIRTHDAY());
            xingxuoTv.setText(AppHelper.getInstance().getUser().getXINGZUO());
            areaTv.setText(AppHelper.getInstance().getUser().getLOCAL_DRESS());
            shenhe = AppHelper.getInstance().getUser().getSHENHE();
            Log.e("JGb","认证结果：："+shenhe);
            if(shenhe==null){
                Certification.setText("待审核");
            }else{
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



    @OnClick({R.id.name_tv, R.id.age_tv, R.id.area_tv,R.id.Certification,R.id.edit_info_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.name_tv:
                Intent intent =new  Intent(EditInfoActivity.this,ChangeNameActivity.class);
                intent.putExtra("nameTv",nameTv.getText().toString());
                startActivityForResult(intent,1);
                break;
            case R.id.age_tv:
                getDate();
                break;
            case R.id.area_tv:
                Intent i = new Intent(EditInfoActivity.this, CityListSelectActivity.class);
                startActivityForResult(i, CityListSelectActivity.CITY_SELECT_RESULT_FRAG);
                break;
            case R.id.Certification:
                if(shenhe==null){
                    startActivity(new Intent(EditInfoActivity.this, EditRealNameActivity.class));
                }else if(Certification.getText().toString().equals("审核未通过")|Certification.getText().toString().equals("待审核")){
                    startActivity(new Intent(EditInfoActivity.this, EditRealNameActivity.class));
                }else{
                   Toast.makeText(EditInfoActivity.this,"您已实名认证过",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.edit_info_add:
                startActivity(new Intent(EditInfoActivity.this,LabelActivity.class));
                break;
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //姓名的回调
        if(requestCode == 1){
            if (data == null)
                return;
           if(data.getStringExtra("returnName")!=null){
               nameTv.setText(data.getStringExtra("returnName"));
               formatChangeName();
           }
        }
        //地区的回调
        if (requestCode == CityListSelectActivity.CITY_SELECT_RESULT_FRAG) {
            if (resultCode == RESULT_OK) {
                if (data == null) {
                    return;
                }
                Bundle bundle = data.getExtras();
                CityInfoBean cityInfoBean = (CityInfoBean) bundle.getParcelable("cityinfo");

                if (null == cityInfoBean)
                    return;
                //城市名称
                String cityName = cityInfoBean.getName();
                areaTv.setText(cityName);
                getAreaHttp(cityName);

            }
        }
    }

    //修改地区
    private void getAreaHttp(String cityName) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest(I.CHANGE_USER, RequestMethod.POST);
        request.add("ID",AppHelper.getInstance().getUser().getID());
        request.add("NICK_NAME",AppHelper.getInstance().getUser().getNICK_NAME());
        request.add("CN",nameTv.getText().toString());
        request.add("BIRTHDAY",ageTv.getText().toString());
        request.add("TARGET",AppHelper.getInstance().getUser().getTARGET());
        request.add("XINGZUO",xingxuoTv.getText().toString());
        request.add("INTRODUCE",AppHelper.getInstance().getUser().getINTRODUCE());
        request.add("LOCAL_DRESS",areaTv.getText().toString());
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.e("JGB","修改地区的结果：："+json);
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }

            @Override
            public void onFinish(int what) {

            }
        });
    }

    //修改时间
    public void getDate() {
        //获取 Calendar 对象，用于获取当前时间
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerStart = new DatePickerDialog(EditInfoActivity.this, new DatePickerDialog.OnDateSetListener() {
                    //选择完日期后会调用该回调函数
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        //因为 monthOfYear 会比实际月份少一月所以这边要加 1
                        ageTv.setText(year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日");
                        getAstro((monthOfYear + 1),dayOfMonth);
                        String age = ageTv.getText().toString();
                        modifyAgeHeep(age);
                    }
                }, year, month, day);
                //弹出选择日期对话框
                datePickerStart.show();
        }

   //修改年龄的网络请求(ok)
    private void modifyAgeHeep(String age) {
        String s = xingxuoTv.getText().toString();
        Log.e("JGB","星座："+s);
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest(I.CHANGE_USER, RequestMethod.POST);
        request.add("ID",AppHelper.getInstance().getUser().getID());
        request.add("NICK_NAME",AppHelper.getInstance().getUser().getNICK_NAME());
        request.add("CN",nameTv.getText().toString());
        request.add("BIRTHDAY",age);
        request.add("TARGET",AppHelper.getInstance().getUser().getTARGET());
        request.add("XINGZUO",xingxuoTv.getText().toString());
        request.add("INTRODUCE",AppHelper.getInstance().getUser().getINTRODUCE());
        request.add("LOCAL_DRESS",areaTv.getText().toString());
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.e("JGB","修改年龄的结果：："+json);
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }

            @Override
            public void onFinish(int what) {

            }
        });
    }

    //根据日期指定星座
    private String getAstro(int month, int day) {
        String[] astro = new String[]{"摩羯座", "水瓶座", "双鱼座", "白羊座", "金牛座",
                "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座"};
        int[] arr = new int[]{20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22};// 两个星座分割日
        int index = month;
        // 所查询日期在分割日之前，索引-1，否则不变
        if (day < arr[month - 1]) {
            index = index - 1;
        }
        xingxuoTv.setText(astro[index]);
        // 返回索引指向的星座string
        return astro[index];
    }

    //修改姓名
    private void formatChangeName() {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest(I.CHANGE_USER, RequestMethod.POST);
        request.add("ID",AppHelper.getInstance().getUser().getID());
        request.add("NICK_NAME",AppHelper.getInstance().getUser().getNICK_NAME());
        request.add("CN",nameTv.getText().toString());
        request.add("BIRTHDAY",ageTv.getText().toString());
        request.add("TARGET",AppHelper.getInstance().getUser().getTARGET());
        request.add("XINGZUO",xingxuoTv.getText().toString());
        request.add("INTRODUCE",AppHelper.getInstance().getUser().getINTRODUCE());
        request.add("LOCAL_DRESS",areaTv.getText().toString());
        //  http://www.olacos.net//memberMobile/updateMember.do?ID=69f1badc98cc441c838310561d11bcb7&NICK_NAME=呆子&CN=朱呵呵&BIRTHDAY=1994-11-06&TARGET=1&XINGZUO=天蝎座&INTRODUCE=啊累累&LOCAL_DRESS=北京
//        request.add("ID","69f1badc98cc441c838310561d11bcb7");
//        request.add("NICK_NAME","呆子");
//        request.add("CN","朱呵呵");
//        request.add("BIRTHDAY","1994-11-06");
//        request.add("TARGET","1");
//        request.add("XINGZUO","天蝎座");
//        request.add("INTRODUCE","啊累累");
//        request.add("LOCAL_DRESS","北京");

        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.e("JGB","change----------"+json);
                if (json != null) {

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

