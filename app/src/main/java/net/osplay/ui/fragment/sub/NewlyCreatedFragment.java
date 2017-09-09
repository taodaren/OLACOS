package net.osplay.ui.fragment.sub;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.osplay.app.SetOnClickListen;
import net.osplay.olacos.R;
import net.osplay.ui.activity.sub.JoinCommunityDetailsActivity;
import net.osplay.ui.adapter.sub.NewlyCreatedAdapter;
import net.osplay.ui.fragment.base.BaseBussFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewlyCreatedFragment extends BaseBussFragment {
    private RecyclerView newly_recy;
    private NewlyCreatedAdapter nAdapter;
    public NewlyCreatedFragment(Context mContext, int resId) {
        super(mContext, resId);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        newly_recy= (RecyclerView) view.findViewById(R.id.newly_recy);
        newly_recy.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        nAdapter=new NewlyCreatedAdapter(getActivity());
        newly_recy.setAdapter(nAdapter);
        setOnClickListen();
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    protected void initData() {

    }
    private void setOnClickListen() {
        SetOnClickListen setOnClickListen=new SetOnClickListen() {
            @Override
            public void setOnClick(int position) {
                startActivity(new Intent(context,JoinCommunityDetailsActivity.class));
            }
        };
        nAdapter.onClick(setOnClickListen);
    }
}
