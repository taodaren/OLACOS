package net.osplay.ui.fragment.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lljjcoder.citypickerview.widget.CityPicker;

import net.osplay.olacos.R;

/**
 * Created by acer-PC on 2017/8/31.
 */

public abstract class BaseBussFragment extends Fragment {
    protected Context context;

    protected int resId;

    public BaseBussFragment() {
    }

    public BaseBussFragment(Context context, int resId) {
        this.context = context;
        this.resId = resId;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    /**
     */
    public abstract View initView();


    public Toolbar setToolbar(int toolbarId, int title, int titleVisibility, int cityVisibility, boolean isHomeBottom) {
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        Toolbar toolbar = (Toolbar) appCompatActivity.findViewById(toolbarId);
        appCompatActivity.setSupportActionBar(toolbar);

        //设置标题
        TextView textTitle = (TextView) getActivity().findViewById(R.id.title_toolbar);
        textTitle.setVisibility(titleVisibility);
        textTitle.setText(title);

        //城市选择
        RelativeLayout cityLayout = (RelativeLayout) getActivity().findViewById(R.id.layout_city);
        cityLayout.setVisibility(cityVisibility);
        cityLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CityPicker cityPicker = new CityPicker.Builder(getContext()).textSize(20)
                        .title("地址选择")
                        .backgroundPop(0xa0000000)
                        .titleBackgroundColor("#f7c936")
                        .titleTextColor("#ffffff")
                        .confirTextColor("#ffffff")
                        .cancelTextColor("#ffffff")
                        .province("北京市")
                        .city("北京市")
                        .district("昌平区")
                        .textColor(Color.parseColor("#000000"))
                        .provinceCyclic(true)
                        .cityCyclic(false)
                        .districtCyclic(false)
                        .visibleItemsCount(7)
                        .itemPadding(10)
                        .onlyShowProvinceAndCity(false)
                        .build();
                cityPicker.show();

                //监听方法，获取选择结果
                cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
                    @Override
                    public void onSelected(String... citySelected) {
                        //省份
                        String province = citySelected[0];
                        //城市
                        String city = citySelected[1];
                        //区县（如果设定了两级联动，那么该项返回空）
                        String district = citySelected[2];
                        //邮编
                        String code = citySelected[3];

                        TextView textCity = (TextView) getActivity().findViewById(R.id.text_city);
                        textCity.setText(district);
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(getContext(), "已取消", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        ActionBar actionBar = appCompatActivity.getSupportActionBar();
        if (actionBar != null) {
            //隐藏左上角图标（true 为显示）
            actionBar.setDisplayHomeAsUpEnabled(isHomeBottom);
            //设置侧滑导航按钮图标
            actionBar.setHomeAsUpIndicator(R.drawable.title_mi);
            //隐藏 Toolbar 自带标题栏
            actionBar.setDisplayShowTitleEnabled(false);
        }
        return toolbar;
    }
}
