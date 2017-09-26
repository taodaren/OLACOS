package net.osplay.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.osplay.app.MFGT;
import net.osplay.olacos.R;
import net.osplay.service.entity.WordTopicBean;
import net.osplay.ui.activity.sub.DetailsDouPictureActivity;
import net.osplay.ui.activity.sub.DetailsTopicActivity;

import java.util.List;

/**
 * 社区 → 热区 → 专题适配器
 */

public class WordHotTopicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity mContext;
    private LayoutInflater mInflater;

    private List<WordTopicBean> mTopicBeanList;

    public WordHotTopicAdapter(Activity mContext, List<WordTopicBean> mTopicBeanList) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        this.mTopicBeanList = mTopicBeanList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = mInflater.inflate(R.layout.item_hot_topic, parent, false);
        TopicViewHolder holder = new TopicViewHolder(inflate);
        holder.setClickListener(viewType);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((TopicViewHolder) holder).bindData(position);
    }

    @Override
    public int getItemCount() {
        return mTopicBeanList == null ? 0 : mTopicBeanList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mTopicBeanList.size() - 1 == position ? 1 : 0;
    }

    private class TopicViewHolder extends RecyclerView.ViewHolder {
        private View outView;//保存子项最外层布局的实例
        private ImageView imgTopic;
        private TextView textTopic;

        private TopicViewHolder(View itemView) {
            super(itemView);
            outView = itemView;
            imgTopic = (ImageView) itemView.findViewById(R.id.img_hot_topic);
            textTopic = (TextView) itemView.findViewById(R.id.text_hot_topic);
        }

        private void bindData(int position) {
            WordTopicBean topicBean = mTopicBeanList.get(position);
            Glide.with(mContext).load(topicBean.getImgId()).into(imgTopic);
            textTopic.setText(topicBean.getName());
        }

        public void setClickListener(final int viewType) {
            outView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (viewType) {
                        case 0://九大专区
                            int position = getAdapterPosition();
                            Intent intent = new Intent(mContext, DetailsTopicActivity.class);
                            intent.putExtra("partId", position + 1 + "");
                            mContext.startActivity(intent);
                            //MFGT.isLogin(mContext, DetailsTopicActivity.class, "loginTopic");
                            break;
                        case 1://斗图
                            MFGT.isLogin(mContext, DetailsDouPictureActivity.class, "loginDou");
                            break;
                        default:
                            break;
                    }
                }
            });

        }
    }

}
