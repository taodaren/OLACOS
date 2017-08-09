package net.osplay.olacos;

import android.app.Activity;
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

/**
 * Fragment 基类
 */

public abstract class BaseFragment extends Fragment {
    public Context mContext;

    /**
     * 当该类被系统创建的时候回调
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();

        // 加上这句话，menu才会显示出来
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 抽象类，由子类实现，实现不同的效果
     */
    public abstract View initView();

    /**
     * 当子类需要联网请求数据的时候，可以重写该方法，该方法中联网请求
     */
    public void initData() {
    }

    /**
     * 设置 Toolbar
     *
     * @param toolbarId      menu_toolbar ID
     * @param title          标题
     * @param cityVisibility 城市选择控件是否显示
     * @return menu_toolbar
     */
    public Toolbar setToolbar(int toolbarId, int title, int titleVisibility,int cityVisibility) {
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
//        cityLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                CityPicker cityPicker = new CityPicker.Builder(getContext()).textSize(20)
//                        .title("地址选择")
//                        .backgroundPop(0xa0000000)
//                        .titleBackgroundColor("#f72b36")
//                        .titleTextColor("#ffffff")
//                        .confirTextColor("#ffffff")
//                        .cancelTextColor("#ffffff")
//                        .province("北京市")
//                        .city("北京市")
//                        .district("昌平区")
//                        .textColor(Color.parseColor("#000000"))
//                        .provinceCyclic(true)
//                        .cityCyclic(false)
//                        .districtCyclic(false)
//                        .visibleItemsCount(7)
//                        .itemPadding(10)
//                        .onlyShowProvinceAndCity(false)
//                        .build();
//                cityPicker.show();
//
//                //监听方法，获取选择结果
//                cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
//                    @Override
//                    public void onSelected(String... citySelected) {
//                        //省份
//                        String province = citySelected[0];
//                        //城市
//                        String city = citySelected[1];
//                        //区县（如果设定了两级联动，那么该项返回空）
//                        String district = citySelected[2];
//                        //邮编
//                        String code = citySelected[3];
//
//                        TextView textCity = (TextView) getActivity().findViewById(R.id.text_city);
//                        textCity.setText(district);
//                    }
//
//                    @Override
//                    public void onCancel() {
//                        Toast.makeText(getContext(), "已取消", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });

        ActionBar actionBar = appCompatActivity.getSupportActionBar();
        if (actionBar != null) {
            //隐藏左上角图标（true 为显示）
            actionBar.setDisplayHomeAsUpEnabled(false);
            //隐藏 Toolbar 自带标题栏
            actionBar.setDisplayShowTitleEnabled(false);
        }
        return toolbar;
    }

}
