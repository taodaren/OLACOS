package net.osplay.ui.adapter;

import android.app.Activity;
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
import net.osplay.service.entity.WordDetailsCommentBean;

/**
 * 帖子详情评论适配器
 */

public class DetailsPostsCommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "DetailsPostsCommentAdapter";
    private Activity mContext;
    private LayoutInflater mInflater;
    private WordDetailsCommentBean mCommentBean;

    public DetailsPostsCommentAdapter(Activity context, WordDetailsCommentBean commentBean) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mCommentBean = commentBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = mInflater.inflate(R.layout.item_comment, parent, false);
        PostsCommentHolder holder = new PostsCommentHolder(inflate);
        holder.setClickListener();
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((PostsCommentHolder) holder).bindData(position);
    }

    @Override
    public int getItemCount() {
        return mCommentBean == null ? 0 : mCommentBean.getOne().size();
    }

    private class PostsCommentHolder extends RecyclerView.ViewHolder {
        private View outView;//保存子项最外层布局的实例
        private ImageView imgAvatar;
        private TextView tvName, tvContent, tvTime;

        private PostsCommentHolder(View itemView) {
            super(itemView);
            outView = itemView;
            imgAvatar = (ImageView) itemView.findViewById(R.id.dtl_posts_comment_avatar);
            tvName = (TextView) itemView.findViewById(R.id.dtl_posts_comment_name);
            tvContent = (TextView) itemView.findViewById(R.id.dtl_posts_comment_content);
            tvTime = (TextView) itemView.findViewById(R.id.dtl_posts_comment_time);
        }

        public void setClickListener() {
            outView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "outView", Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void bindData(int position) {
            Glide.with(mContext).load(I.BASE_URL + mCommentBean.getOne().get(position).getHEAD_PATH()).into(imgAvatar);
            tvName.setText(mCommentBean.getOne().get(position).getNICK_NAME());
            tvContent.setText(mCommentBean.getOne().get(position).getCONTENT());
            tvTime.setText(mCommentBean.getOne().get(position).getCREATEDATE());
        }
    }
}
