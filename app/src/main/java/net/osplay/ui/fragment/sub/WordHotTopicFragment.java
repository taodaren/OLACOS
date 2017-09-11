package net.osplay.ui.fragment.sub;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import net.osplay.olacos.R;
import net.osplay.service.entity.WordTopicBean;
import net.osplay.ui.adapter.WordHotTopicAdapter;
import net.osplay.ui.fragment.base.BaseFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 社区：热区 → 专题
 */

public class WordHotTopicFragment extends BaseFragment {
    private Context mContext;
    private List<WordTopicBean> topicList = new ArrayList<>();
    private RecyclerView recyclerTopic;
    private GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
    private WordHotTopicAdapter adapter;

    private WordTopicBean[] topicBeen = {
            new WordTopicBean("COS专区", R.drawable.example01),
            new WordTopicBean("服装", R.drawable.example02),
            new WordTopicBean("模玩", R.drawable.example03),
            new WordTopicBean("游戏", R.drawable.example04),
            new WordTopicBean("动漫", R.drawable.example05),
            new WordTopicBean("影视", R.drawable.example06),
            new WordTopicBean("同人", R.drawable.example07),
            new WordTopicBean("周边", R.drawable.example08),
    };

    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.fragment_word_hot_topic, null);
        recyclerTopic = (RecyclerView) inflate.findViewById(R.id.recycler_hot_topic);
        layoutManager = new GridLayoutManager(mContext, 2);
        recyclerTopic.setLayoutManager(layoutManager);
        adapter = new WordHotTopicAdapter(/*mContext, */topicList);
        recyclerTopic.setAdapter(adapter);
        return inflate;
    }

    @Override
    public void initData() {
        super.initData();
        Collections.addAll(topicList, topicBeen);
    }

}
