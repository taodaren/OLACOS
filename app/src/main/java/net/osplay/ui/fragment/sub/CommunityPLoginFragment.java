package net.osplay.ui.fragment.sub;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.osplay.app.MFGT;
import net.osplay.olacos.R;
import net.osplay.ui.activity.sub.ChangeNameActivity;
import net.osplay.ui.activity.sub.CreateOrJoinActivity;
import net.osplay.ui.activity.sub.EditInfoActivity;
import net.osplay.ui.activity.sub.LoginActivity;

/**
 * 社团作品提醒登录
 */
public class CommunityPLoginFragment extends Fragment {

    private View view;
    private TextView community_atv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_community_plogin, container, false);
        community_atv= (TextView) view.findViewById(R.id.community_atv);
        community_atv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MFGT.gotoLogin(getActivity(), CreateOrJoinActivity.class, "loginCOJ1");
            }
        });
        return view;
    }


}
