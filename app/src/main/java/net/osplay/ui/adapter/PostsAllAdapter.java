package net.osplay.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import net.osplay.olacos.R;
import net.osplay.service.entity.MeiZiBean;
import net.osplay.ui.activity.sub.MinePageOtherActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 热帖 → 全部 适配器
 */

public class PostsAllAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity mContext;
    private LayoutInflater mInflater;
    private List<MeiZiBean.ResultsBean> mPostsAllList;

    public PostsAllAdapter(Activity context, List<MeiZiBean.ResultsBean> postsAllList) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mPostsAllList = new ArrayList<>();
        this.mPostsAllList.addAll(postsAllList);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = mInflater.inflate(R.layout.item_details_posts_three, parent, false);
        PostsAllHolder holder = new PostsAllHolder(inflate);
        holder.setClickListener();
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((PostsAllHolder) holder).bindData(position);
    }

    @Override
    public int getItemCount() {
        return mPostsAllList == null ? 0 : mPostsAllList.size();
    }

    private class PostsAllHolder extends RecyclerView.ViewHolder {
        private View outView;//保存子项最外层布局的实例
        private ImageView imgAvatar, imgOne, imgTwo, imgThree;

        private PostsAllHolder(View itemView) {
            super(itemView);
            outView = itemView;
            imgAvatar = (ImageView) itemView.findViewById(R.id.details_posts_avatar);
            imgOne = (ImageView) itemView.findViewById(R.id.details_posts_one);
            imgTwo = (ImageView) itemView.findViewById(R.id.details_posts_two);
            imgThree = (ImageView) itemView.findViewById(R.id.details_posts_three);
        }

        public void setClickListener() {
            outView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "outView", Toast.LENGTH_SHORT).show();
                }
            });

            imgAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, MinePageOtherActivity.class));
                }
            });
        }

        public void bindData(int position) {
            MeiZiBean.ResultsBean meiZiBean = mPostsAllList.get(position);
            Glide.with(mContext).load(meiZiBean.getUrl()).into(imgAvatar);
            Glide.with(mContext).load(meiZiBean.getUrl()).into(imgTwo);
        }
    }

}
