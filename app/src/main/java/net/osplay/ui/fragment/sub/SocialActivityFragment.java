package net.osplay.ui.fragment.sub;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import net.osplay.app.AppHelper;
import net.osplay.app.MFGT;
import net.osplay.olacos.R;
import net.osplay.ui.activity.sub.CreateOrJoinActivity;
import net.osplay.ui.activity.sub.DetailsDouPictureActivity;
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
                if (AppHelper.getInstance().isLogined()) {// 已登录状态

                } else { // 未登录时跳转登录界面
                    getActivity().startActivity(LoginActivity.getCallIntent(getContext()));
                }
            }
        });
    }

    @Override
    protected void initData() {

    }
}
