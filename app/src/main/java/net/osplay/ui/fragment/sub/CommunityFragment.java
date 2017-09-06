package net.osplay.ui.fragment.sub;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import net.osplay.olacos.R;
import net.osplay.ui.activity.sub.EventDetailsActivity;
import net.osplay.ui.activity.sub.MinePageActivity;
import net.osplay.ui.fragment.base.BaseBussFragment;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 社团活动帖
 */
public class CommunityFragment extends BaseBussFragment {
    private RelativeLayout rl;
    public CommunityFragment(Context mContext, int resId) {
        super(mContext, resId);
    }
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        rl= (RelativeLayout) view.findViewById(R.id.community_rl);
    }

    @Override
    protected void bindEvent() {
        rl.setOnClickListener(mOnClickListener);
    }

    @Override
    protected void initData() {

    }

    private View.OnClickListener mOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.community_rl:
                    startActivity(new Intent(getActivity(), EventDetailsActivity.class));
                    break;
            }
        }
    };
}
