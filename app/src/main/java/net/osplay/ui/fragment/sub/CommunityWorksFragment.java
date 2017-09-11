package net.osplay.ui.fragment.sub;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.osplay.olacos.R;
import net.osplay.ui.adapter.sub.CommunityWorksAdapter;
import net.osplay.ui.fragment.base.BaseBussFragment;
import net.osplay.ui.fragment.base.BaseFragment;

/**
 * 加入或创建后的社团作品
 */
public class CommunityWorksFragment extends BaseBussFragment {
    private RecyclerView works_recy;
    private CommunityWorksAdapter wAdapter;
    public CommunityWorksFragment(Context mContext, int resId) {
        super(mContext, resId);
    }
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        works_recy= (RecyclerView) view.findViewById(R.id.works_recy);
        works_recy.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        wAdapter=new CommunityWorksAdapter(getActivity());
        works_recy.setAdapter(wAdapter);
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    protected void initData() {

    }
}
