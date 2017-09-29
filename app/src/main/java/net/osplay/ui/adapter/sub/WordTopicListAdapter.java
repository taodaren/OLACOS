package net.osplay.ui.adapter.sub;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import net.osplay.app.I;
import net.osplay.olacos.R;
import net.osplay.service.entity.WordTopicListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 社区帖子详情 → 全部/精品/同城
 */

public class WordTopicListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<WordTopicListBean.RowsBean> mRowsBeanList;

    public WordTopicListAdapter(Context mContext, List<WordTopicListBean.RowsBean> rowsBeanList) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        this.mRowsBeanList = new ArrayList<>();
        this.mRowsBeanList.addAll(rowsBeanList);
    }

    @Override
    public PostsInfoAllHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = mInflater.inflate(R.layout.item_word_topic_list, parent, false);
        PostsInfoAllHolder holder = new PostsInfoAllHolder(inflate);
        holder.setClickListener();
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((PostsInfoAllHolder) holder).bindData(position);
    }

    @Override
    public int getItemCount() {
        return mRowsBeanList == null ? 0 : mRowsBeanList.size();
    }

    public void setData(List<WordTopicListBean.RowsBean> data) {
        if (mRowsBeanList != null) {
            mRowsBeanList.clear();
        } else {
            mRowsBeanList = new ArrayList<>();
        }
        mRowsBeanList.addAll(data);
        notifyDataSetChanged();
    }

    private class PostsInfoAllHolder extends RecyclerView.ViewHolder {
        private View outView;
        private TextView tvNick, tvTime, tvTitle, tvZan, tvCollect, tvComment;
        private ImageView imgAvatar, imgBg;

        private PostsInfoAllHolder(View itemView) {
            super(itemView);
            outView = itemView;
            tvNick = (TextView) itemView.findViewById(R.id.item_topic_all_nick);
            tvTime = (TextView) itemView.findViewById(R.id.item_topic_all_time);
            tvTitle = (TextView) itemView.findViewById(R.id.item_topic_all_title);
            tvZan = (TextView) itemView.findViewById(R.id.item_topic_all_good);
            tvCollect = (TextView) itemView.findViewById(R.id.item_topic_all_collect);
            tvComment = (TextView) itemView.findViewById(R.id.item_topic_all_comment);
            imgAvatar = (ImageView) itemView.findViewById(R.id.item_topic_all_avatar);
            imgBg = (ImageView) itemView.findViewById(R.id.item_topic_all_icon);
        }

        public void bindData(int position) {
            WordTopicListBean.RowsBean rowsBean = mRowsBeanList.get(position);
            if (rowsBean.getHEAD_PATH() != null) {
                Glide.with(mContext).load(I.BASE_URL + rowsBean.getHEAD_PATH()).into(imgAvatar);
            } else {
                Glide.with(mContext).load(R.drawable.avatar_default).into(imgAvatar);
            }
            Glide.with(mContext).load(I.BASE_URL + rowsBean.getCOVERIMG()).into(imgBg);
            tvNick.setText(rowsBean.getNICK_NAME());
            tvTime.setText(rowsBean.getCREATEDATE());
            tvTitle.setText(rowsBean.getTITLE());
            tvZan.setText(rowsBean.getZAN_COUNT());
            tvCollect.setText(rowsBean.getCOLLECT_COUNT());
            tvComment.setText(rowsBean.getPINGLUN_COUNT());
        }

        public void setClickListener() {
            outView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "outView", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
