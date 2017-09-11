package net.osplay.ui.fragment.sub;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import net.osplay.olacos.R;
import net.osplay.ui.activity.sub.CreateOrJoinActivity;
import net.osplay.ui.activity.sub.LoginActivity;
import net.osplay.ui.fragment.base.BaseBussFragment;

/**
 * 还未加入社团的社团活动界面
 */
public class SocialActivityFragment extends BaseBussFragment {
    private TextView create_tv;

    @SuppressLint("ValidFragment")
    public SocialActivityFragment() {
    }

    @SuppressLint("ValidFragment")
    public SocialActivityFragment(Context mContext, int resId) {
        super(mContext, resId);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        create_tv = (TextView) view.findViewById(R.id.create_tv);
    }

    @Override
    protected void bindEvent() {
        create_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //查看本地是否有用户的登录信息
                SharedPreferences sp = getActivity().getSharedPreferences("user_info", Context.MODE_PRIVATE);
                String name = sp.getString("name", "");
                if (TextUtils.isEmpty(name)) {
                    //本地没有保存过用户信息
                    startActivity(new Intent(getContext(), LoginActivity.class));
                    getActivity().finish();
                } else {
                    startActivity(new Intent(getContext(), CreateOrJoinActivity.class));
                }
            }
        });
    }

    @Override
    protected void initData() {

    }
}
