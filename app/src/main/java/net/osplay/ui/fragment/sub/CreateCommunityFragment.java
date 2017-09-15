package net.osplay.ui.fragment.sub;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;

import net.osplay.olacos.R;
import net.osplay.service.entity.VideoBean;
import net.osplay.ui.activity.sub.CreateOrJoinActivity;
import net.osplay.ui.activity.sub.DouPictureVoteActivity;
import net.osplay.ui.activity.sub.LoginActivity;
import net.osplay.ui.fragment.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by acer-PC on 2017/8/31.
 * 判断是否加入或创建过社团
 */

public class CreateCommunityFragment extends BaseFragment {


    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.fragment_create_community, null);
        View create = inflate.findViewById(R.id.create_tv);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //查看本地是否有用户的登录信息
                SharedPreferences sp = getActivity().getSharedPreferences("user_info", Context.MODE_PRIVATE);
                String name = sp.getString("name", "");
                if (TextUtils.isEmpty(name)) {
                    //本地没有保存过用户信息
                    startActivity(new Intent(getContext(), LoginActivity.class));
                }
                if (!TextUtils.isEmpty(name)){
                    startActivity(new Intent(getContext(),CreateOrJoinActivity.class));//
                }
            }
        });
        return inflate;
    }
}
