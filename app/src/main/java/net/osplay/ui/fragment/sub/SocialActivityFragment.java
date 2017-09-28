package net.osplay.ui.fragment.sub;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import net.osplay.app.MFGT;
import net.osplay.olacos.R;
import net.osplay.ui.activity.sub.CreateOrJoinActivity;
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
                MFGT.gotoLogin(getActivity(), CreateOrJoinActivity.class, "loginCOJ1");
            }
        });
    }

    @Override
    protected void initData() {

    }
}
