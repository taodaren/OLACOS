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
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseBussFragment {
    private TextView works_create_tv;

    @SuppressLint("ValidFragment")
    public MineFragment() {
    }

    @SuppressLint("ValidFragment")
    public MineFragment(Context mContext, int resId) {
        super(mContext, resId);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        works_create_tv= (TextView) view.findViewById(R.id.works_create_tv);

    }

    @Override
    protected void bindEvent() {
        works_create_tv.setOnClickListener(new View.OnClickListener() {
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
                    getActivity().finish();
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

}
