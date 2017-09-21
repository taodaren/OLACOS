package net.osplay.ui.activity.sub;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import net.osplay.olacos.R;
import net.osplay.ui.activity.base.BaseActivity;

import java.util.Calendar;

public class PublishActivityActivity extends BaseActivity {

    private RadioButton rbtn_notice,rbtn_activity,rbtn_works;
    private LinearLayout layout_visibility;
    private EditText notice_ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_activity);
        setToolbar("发布活动", View.VISIBLE);
        initView();
        BindListening();
        setTime();

    }

    private void BindListening() {
        rbtn_activity.setOnClickListener(onClickListener);
        rbtn_works.setOnClickListener(onClickListener);
        rbtn_notice.setOnClickListener(onClickListener);
    }


    private void initView() {
        rbtn_notice= (RadioButton) findViewById(R.id.rbtn_notice);
        layout_visibility= (LinearLayout) findViewById(R.id.layout_visibility);
        rbtn_activity= (RadioButton) findViewById(R.id.rbtn_activity);
        rbtn_works= (RadioButton) findViewById(R.id.rbtn_works);
        notice_ed= (EditText) findViewById(R.id.notice_ed);
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

    /**
     * 设置开始/结束时间
     */
    private void setTime() {
        final TextView textTimeStart = (TextView) findViewById(R.id.text_time_start);
        final TextView textTimeOver = (TextView) findViewById(R.id.text_time_over);

        //获取 Calendar 对象，用于获取当前时间
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        //设置默认活动开始/结束时间
        textTimeStart.setText(year + "年" + (month + 1) + "月" + day + "日");
        textTimeOver.setText(year + "年" + (month + 1) + "月" + day + "日");

        //选择活动开始时间
        textTimeStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerStart = new DatePickerDialog(PublishActivityActivity.this, new DatePickerDialog.OnDateSetListener() {
                    //选择完日期后会调用该回调函数
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        //因为 monthOfYear 会比实际月份少一月所以这边要加 1
                        textTimeStart.setText(year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日");
                    }
                }, year, month, day);
                //弹出选择日期对话框
                datePickerStart.show();
            }
        });

        //选择活动结束时间
        textTimeOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerOver = new DatePickerDialog(PublishActivityActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        textTimeOver.setText(year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日");
                    }
                }, year, month, day);
                datePickerOver.show();
            }
        });
    }

    private View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.rbtn_activity:
                    layout_visibility.setVisibility(View.VISIBLE);
                    notice_ed.setVisibility(View.GONE);
                    //阻止键盘弹出
                    InputMethodManager imm1 = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm1.hideSoftInputFromWindow(notice_ed.getWindowToken(),0);
                    break;
                case R.id.rbtn_works:
                    layout_visibility.setVisibility(View.VISIBLE);
                    notice_ed.setVisibility(View.GONE);
                    //阻止键盘弹出
                    InputMethodManager imm2 = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm2.hideSoftInputFromWindow(notice_ed.getWindowToken(),0);
                    break;
                case R.id.rbtn_notice:
                    layout_visibility.setVisibility(View.GONE);
                    notice_ed.setVisibility(View.VISIBLE);
                    //弹出键盘
                    ((InputMethodManager)getSystemService(INPUT_METHOD_SERVICE)).toggleSoftInput(0,InputMethodManager.HIDE_NOT_ALWAYS);
                    notice_ed.requestFocus();
                    break;
            }
        }
    };

}
