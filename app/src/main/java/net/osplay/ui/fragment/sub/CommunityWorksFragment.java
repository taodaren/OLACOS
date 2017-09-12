package net.osplay.ui.fragment.sub;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import net.osplay.olacos.R;
import net.osplay.ui.adapter.sub.CommunityWorksAdapter;
import net.osplay.ui.fragment.base.BaseBussFragment;

/**
 * 加入或创建后的社团作品
 */
public class CommunityWorksFragment extends BaseBussFragment {
    private RecyclerView works_recy;
    private CommunityWorksAdapter wAdapter;

    @SuppressLint("ValidFragment")
    public CommunityWorksFragment() {
    }

    @SuppressLint("ValidFragment")
    public CommunityWorksFragment(Context mContext, int resId) {
        super(mContext, resId);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        works_recy = (RecyclerView) view.findViewById(R.id.works_recy);
        works_recy.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        wAdapter = new CommunityWorksAdapter(getActivity());
        works_recy.setAdapter(wAdapter);
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    protected void initData() {

    }

}
