package net.osplay.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.osplay.app.I;
import net.osplay.olacos.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by fanyanmin on 2017/9/29.
 */

public class MyPostsAdapter extends RecyclerView.Adapter<PostsViewHolder> {
    private Context context;
    private List<MyPostsBean.RowsBean> rows;

    public MyPostsAdapter(Context context, List<MyPostsBean.RowsBean> rows) {
        this.context = context;
        this.rows = rows;
    }

    @Override
    public PostsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_posts, parent, false);
        PostsViewHolder viewHolder = new PostsViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PostsViewHolder holder, int position) {
        Glide.with(context).load(I.BASE_URL+rows.get(position).getHEAD_PATH()).into(holder.item_posts_avatar);
        Glide.with(context).load(I.BASE_URL+rows.get(position).getCOVERIMG()).into(holder.item_posts_icon);
        holder.item_topic_all_nick.setText(rows.get(position).getNICK_NAME());
        holder.item_posts_time.setText(rows.get(position).getCREATEDATE());
        holder.item_posts_title.setText(rows.get(position).getTITLE());
      // holder.item_posts_good.setText(rows.get(position).);
    }

    @Override
    public int getItemCount() {
        return rows.size();
    }
}
class PostsViewHolder extends RecyclerView.ViewHolder{
    public CircleImageView item_posts_avatar;
    public TextView item_posts_time;//时间
    public TextView item_posts_title,item_topic_all_nick;
    public ImageView item_posts_icon;
    public TextView item_posts_good,item_posts_collect,item_posts_comment;
    public PostsViewHolder(View itemView) {
        super(itemView);
        item_posts_avatar= (CircleImageView) itemView.findViewById(R.id.item_posts_avatar);
        item_posts_time= (TextView) itemView.findViewById(R.id.item_posts_time);
        item_posts_title= (TextView) itemView.findViewById(R.id.item_posts_title);
        item_posts_icon= (ImageView) itemView.findViewById(R.id.item_posts_icon);
        item_posts_good= (TextView) itemView.findViewById(R.id.item_posts_good);
        item_posts_collect= (TextView) itemView.findViewById(R.id.item_posts_collect);
        item_posts_comment= (TextView) itemView.findViewById(R.id.item_posts_comment);
        item_topic_all_nick= (TextView) itemView.findViewById(R.id.item_topic_all_nick);
    }
}
