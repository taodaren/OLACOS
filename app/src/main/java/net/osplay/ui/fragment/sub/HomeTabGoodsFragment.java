package net.osplay.ui.fragment.sub;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.osplay.olacos.R;
import net.osplay.service.entity.VideoBean;
import net.osplay.ui.adapter.HomeTabGoodsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页 → Tab → Goods
 */

public class HomeTabGoodsFragment extends Fragment {
    private static final String KEY_DATA = "HomeTabGoodsFragment.key.data";
    private List<VideoBean> dataList = new ArrayList<>();
    private LinearLayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;

    public static HomeTabGoodsFragment newInstance(List<VideoBean> datas) {
        HomeTabGoodsFragment fragment = new HomeTabGoodsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(KEY_DATA, (ArrayList<? extends Parcelable>) datas);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            ArrayList<VideoBean> list = bundle.getParcelableArrayList(KEY_DATA);
            if (list != null && !list.isEmpty()) {
                dataList.addAll(list);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.layout_recycler_view, container, false);
        mRecyclerView = (RecyclerView) fragmentView.findViewById(R.id.rv_layout_public);
        return fragmentView;
    }

    @Override
    public void onStart() {
        super.onStart();
        initRecyclerView();
    }

    private void initRecyclerView() {
        if (checkData()) {
            mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(mLayoutManager);
            HomeTabGoodsAdapter adapter = new HomeTabGoodsAdapter(getContext(), dataList);
            mRecyclerView.setAdapter(adapter);
        }

    }

    private boolean checkData() {
        return dataList != null && !dataList.isEmpty();
    }

}
