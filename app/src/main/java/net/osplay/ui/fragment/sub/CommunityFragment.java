package net.osplay.ui.fragment.sub;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import net.osplay.app.SetOnClickListen;
import net.osplay.olacos.R;
import net.osplay.ui.activity.sub.EventDetailsActivity;
import net.osplay.ui.activity.sub.JoinCommunityDetailsActivity;
import net.osplay.ui.adapter.sub.CommunityAdapter;
import net.osplay.ui.fragment.base.BaseBussFragment;

/**
 * 社团活动帖
 */
public class CommunityFragment extends BaseBussFragment {
   private RecyclerView community_recycler;
    private CommunityAdapter cAdapter;

    @SuppressLint("ValidFragment")
    public CommunityFragment() {
    }

    @SuppressLint("ValidFragment")
    public CommunityFragment(Context mContext, int resId) {
        super(mContext, resId);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        community_recycler= (RecyclerView) view.findViewById(R.id.community_recycler);
        community_recycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        cAdapter=new CommunityAdapter(getActivity());
        community_recycler.setAdapter(cAdapter);
    }

    @Override
    protected void bindEvent() {
        setOnClickListen();
    }

    @Override
    protected void initData() {

    }

    public void setOnClickListen(){
        SetOnClickListen setOnClickListen = new SetOnClickListen() {
            @Override
            public void setOnClick(int position) {
                startActivity(new Intent(getActivity(), EventDetailsActivity.class));
            }
        };
        cAdapter.onClick(setOnClickListen);
    }
//    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()) {
//                case R.id.community_rl:
//                    startActivity(new Intent(getActivity(), EventDetailsActivity.class));
//                    break;
//            }
//        }
//    };
}
