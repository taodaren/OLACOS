package net.osplay.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.osplay.app.MyApplication;
import net.osplay.olacos.R;
import net.osplay.service.entity.VideoBean;
import net.osplay.ui.activity.sub.DetailsPostsActivity;
import net.osplay.ui.activity.sub.MinePageOtherActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 社区 → 热区 → 热帖适配器
 */

public class WordHotPostsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity mContext;
    private LayoutInflater mInflater;

    private List<VideoBean> mHotPostsList;

    public WordHotPostsAdapter(Activity context, List<VideoBean> data) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);

        this.mHotPostsList = new ArrayList<>();
        this.mHotPostsList.addAll(data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PostsViewHolder holder = new PostsViewHolder(mInflater.inflate(R.layout.item_hot_posts, parent, false));
        holder.setClickListener();
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((PostsViewHolder) holder).bindData(mHotPostsList.get(position));
    }

    @Override
    public int getItemCount() {
        return mHotPostsList == null ? 0 : mHotPostsList.size();
    }

    private class PostsViewHolder extends RecyclerView.ViewHolder {
        private View outView;//保存子项最外层布局的实例
        private CardView layout;
        private ImageView imgAvatar;
        private TextView tvTitle, tvNum, tvInfo, tvMember, tvPosts;

        private PostsViewHolder(View inflate) {
            super(inflate);
            outView = inflate;
            layout = (CardView) inflate.findViewById(R.id.card_hot_posts);
            imgAvatar = (ImageView) inflate.findViewById(R.id.img_hot_posts);
            tvTitle = (TextView) inflate.findViewById(R.id.text_title_hot_posts);
            tvNum = (TextView) inflate.findViewById(R.id.text_num_hot_posts);
            tvInfo = (TextView) inflate.findViewById(R.id.text_info_hot_posts);
            tvMember = (TextView) inflate.findViewById(R.id.text_member_hot_posts);
            tvPosts = (TextView) inflate.findViewById(R.id.text_posts_hot_posts);
        }

        public void bindData(VideoBean videoBean) {
            Glide.with(MyApplication.getContext()).load(videoBean.getCoverImg()).into(imgAvatar);
            tvTitle.setText(videoBean.getVideoTitle().substring(0, 3));
        }

        private void setClickListener() {
            outView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, DetailsPostsActivity.class));
                }
            });

            imgAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, MinePageOtherActivity.class));
                }
            });
        }
    }

}
