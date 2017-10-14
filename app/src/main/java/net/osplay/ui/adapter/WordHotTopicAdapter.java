package net.osplay.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.osplay.app.I;
import net.osplay.olacos.R;
import net.osplay.service.entity.WordTopicBean;
import net.osplay.ui.activity.sub.DetailsTopicActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 社区 → 热区 → 专题适配器
 */

public class WordHotTopicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity mContext;
    private LayoutInflater mInflater;
    private List<WordTopicBean> mTopicList;

    public WordHotTopicAdapter(Activity mContext, List<WordTopicBean> mTopicList) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        this.mTopicList = new ArrayList<>();
        this.mTopicList.addAll(mTopicList);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = mInflater.inflate(R.layout.item_hot_topic, parent, false);
        TopicViewHolder holder = new TopicViewHolder(inflate);
        holder.setClickListener();
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((TopicViewHolder) holder).bindData(position);
    }

    @Override
    public int getItemCount() {
        return mTopicList == null ? 0 : mTopicList.size();
    }

    private class TopicViewHolder extends RecyclerView.ViewHolder {
        private View outView;//保存子项最外层布局的实例
        private ImageView imgTopic;
        private TextView textTopic;
        private WordTopicBean topicBean;

        private TopicViewHolder(View itemView) {
            super(itemView);
            outView = itemView;
            imgTopic = (ImageView) itemView.findViewById(R.id.img_hot_topic);
            textTopic = (TextView) itemView.findViewById(R.id.text_hot_topic);
        }

        private void bindData(int position) {
            topicBean = mTopicList.get(position);
            Glide.with(mContext).load(I.BASE_URL + topicBean.getPART_PATH()).into(imgTopic);
            textTopic.setText(topicBean.getPART());
        }

        public void setClickListener() {
            outView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent(mContext, DetailsTopicActivity.class);
                    intent.putExtra("partId", position + 1 + "");
                    intent.putExtra(I.Img.IMG_KEY, I.BASE_URL + topicBean.getPART_PATH());
                    intent.putExtra(I.Type.TYPE_NAME, topicBean.getPART());
                    mContext.startActivity(intent);
                }
            });

        }
    }

}
