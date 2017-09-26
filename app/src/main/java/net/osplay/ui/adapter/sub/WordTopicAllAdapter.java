package net.osplay.ui.adapter.sub;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hankkin.library.CircleImageView;

import net.osplay.app.I;
import net.osplay.olacos.R;
import net.osplay.service.entity.WordTopicAllBean;
import net.osplay.utils.Constants;

import java.util.List;

/**
 * 社区帖子详情--全部
 */

public class WordTopicAllAdapter extends RecyclerView.Adapter<WordTopicAllViewHolder> {
    private Context mContext;
    private List<WordTopicAllBean.RowsBean> rows;
    public WordTopicAllAdapter(Context mContext, List<WordTopicAllBean.RowsBean> rows) {
        this.mContext = mContext;
        this.rows = rows;
    }

    @Override
    public WordTopicAllViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_word_topic_all, parent, false);
        WordTopicAllViewHolder viewHolder = new WordTopicAllViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WordTopicAllViewHolder holder, int position) {
//        Glide.with(mContext).load(I.BASE_URL+rows.get(position).getCOVERIMG()).into(holder.item_topic_all_icon);
//        Glide.with(mContext).load(I.BASE_URL+rows.get(position).getHEAD_PATH()).into(holder.item_topic_all_avatar);
        holder.item_topic_all_nick.setText("昵称："+rows.get(position).getNICK_NAME());
        holder.item_topic_all_time.setText("时间"+rows.get(position).getCREATEDATE());
        holder.item_topic_all_title.setText("标题"+rows.get(position).getTITLE());
        holder.item_topic_all_good.setText("赞"+rows.get(position).getZAN_COUNT());
        holder.item_topic_all_collect.setText("收藏"+rows.get(position).getCOLLECT_COUNT());
        holder.item_topic_all_comment.setText("评论"+rows.get(position).getPINGLUN_COUNT());
    }

    @Override
    public int getItemCount() {
        return rows.size();
    }
}

class WordTopicAllViewHolder extends RecyclerView.ViewHolder {

    public TextView item_topic_all_nick,item_topic_all_time,item_topic_all_title,item_topic_all_good,item_topic_all_collect,item_topic_all_comment;
    public ImageView item_topic_all_icon;
    public CircleImageView item_topic_all_avatar;
    public WordTopicAllViewHolder(View itemView) {
        super(itemView);
        item_topic_all_nick= (TextView) itemView.findViewById(R.id.item_topic_all_nick);
        item_topic_all_time= (TextView) itemView.findViewById(R.id.item_topic_all_time);
        item_topic_all_title= (TextView) itemView.findViewById(R.id.item_topic_all_title);
        item_topic_all_good= (TextView) itemView.findViewById(R.id.item_topic_all_good);
        item_topic_all_collect= (TextView) itemView.findViewById(R.id.item_topic_all_collect);
        item_topic_all_comment= (TextView) itemView.findViewById(R.id.item_topic_all_comment);

    }
}
