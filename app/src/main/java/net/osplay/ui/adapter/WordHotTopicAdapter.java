package net.osplay.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.osplay.olacos.R;
import net.osplay.service.entity.WordTopicBean;

import java.util.List;

/**
 * 社区 → 热区 → 专题适配器
 */

public class WordHotTopicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
//    private LayoutInflater mInflater;

    private List<WordTopicBean> mTopicBeanList;

    public WordHotTopicAdapter(/*Context mContext,*/ List<WordTopicBean> mTopicBeanList) {
//        this.mContext = mContext;
//        this.mInflater = LayoutInflater.from(mContext);
        this.mTopicBeanList = mTopicBeanList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        return new TopicViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.layout_word_add, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((TopicViewHolder) holder).bindData(position);
    }

    @Override
    public int getItemCount() {
        return mTopicBeanList == null ? 0 : mTopicBeanList.size();
    }

    private class TopicViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgTopic;
        private TextView textTopic;

        private TopicViewHolder(View itemView) {
            super(itemView);
            imgTopic = (ImageView) itemView.findViewById(R.id.img_hot_topic);
            textTopic = (TextView) itemView.findViewById(R.id.text_hot_topic);
        }

        private void bindData(int position) {
            WordTopicBean topicBean = mTopicBeanList.get(position);
            Glide.with(mContext).load(topicBean.getImgId()).into(imgTopic);
            textTopic.setText(topicBean.getName());
        }
    }

}
