package net.osplay.ui.fragment.sub;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import net.osplay.olacos.R;
import net.osplay.service.entity.WordTopicBean;
import net.osplay.ui.adapter.WordHotTopicAdapter;
import net.osplay.ui.fragment.base.BaseFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 社区：热区 → 专区
 */

public class WordHotTopicFragment extends BaseFragment {
    private static final String TAG = "WordHotTopicFragment";
    private RecyclerView mRvTopic;
    private List<WordTopicBean> topicList;

    @SuppressLint("ValidFragment")
    public WordHotTopicFragment() {
    }

    @SuppressLint("ValidFragment")
    public WordHotTopicFragment(Context context, int resId) {
        super(context, resId);
    }

    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.fragment_word_hot_topic, null);
        mRvTopic = (RecyclerView) inflate.findViewById(R.id.recycler_hot_topic);
        return inflate;
    }

    @Override
    public void initData() {
        super.initData();
        WordTopicBean[] topicBeen = new WordTopicBean[]{
                new WordTopicBean("COS专区", R.drawable.example01),
                new WordTopicBean("服装", R.drawable.example02),
                new WordTopicBean("模玩", R.drawable.example03),
                new WordTopicBean("游戏", R.drawable.example04),
                new WordTopicBean("动漫", R.drawable.example05),
                new WordTopicBean("影视", R.drawable.example06),
                new WordTopicBean("同人", R.drawable.example08),
                new WordTopicBean("周边", R.drawable.example09),
                new WordTopicBean("斗图", R.drawable.example12)
        };
        topicList = new ArrayList<>();
        Collections.addAll(topicList, topicBeen);
        initRecyclerView();
    }

    private void initRecyclerView() {
        if (topicList != null) {
            GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
            mRvTopic.setLayoutManager(mLayoutManager);
            mRvTopic.setHasFixedSize(true);

            WordHotTopicAdapter mAdapter = new WordHotTopicAdapter(getActivity(), topicList);
            Log.d(TAG, "initView: topicList======================" + topicList);
            mRvTopic.setAdapter(mAdapter);
        }
    }

}
