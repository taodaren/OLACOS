package net.osplay.ui.fragment.sub;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.osplay.app.SetOnClickListen;
import net.osplay.olacos.R;
import net.osplay.ui.activity.sub.JoinCommunityDetailsActivity;
import net.osplay.ui.adapter.sub.HeatRankAdapter;
import net.osplay.ui.fragment.base.BaseBussFragment;

/**
 * 社团-热门排行
 */
public class HeatRankFragment extends BaseBussFragment {
    private RecyclerView heat_recy;
    private HeatRankAdapter hAdapter;

    @SuppressLint("ValidFragment")
    public HeatRankFragment() {
    }

    @SuppressLint("ValidFragment")
    public HeatRankFragment(Context mContext, int resId) {
        super(mContext, resId);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        heat_recy = (RecyclerView) view.findViewById(R.id.heat_recy);
        hAdapter = new HeatRankAdapter(getActivity());
        heat_recy.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        heat_recy.setAdapter(hAdapter);
        setOnClickListen();
    }

    @Override
    protected void bindEvent() {
    }

    @Override
    protected void initData() {
    }

    private void setOnClickListen() {
        SetOnClickListen setOnClickListen = new SetOnClickListen() {
            @Override
            public void setOnClick(int position) {

            }

            @Override
            public void setOnClick(int position, TextView zanTv, TextView collecTv, TextView commentTv, ImageView zanIv, ImageView cllecIv) {
                startActivity(new Intent(context, JoinCommunityDetailsActivity.class));
            }

        };
        hAdapter.onClick(setOnClickListen);
    }

}
