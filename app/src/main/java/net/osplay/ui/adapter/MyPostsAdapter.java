package net.osplay.ui.adapter;

import android.content.Context;
import android.content.Intent;
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
import net.osplay.ui.activity.sub.DetailsPostsActivity;

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
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_word_topic_list, parent, false);
        PostsViewHolder viewHolder = new PostsViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PostsViewHolder holder, final int position) {
        Glide.with(context).load(I.BASE_URL+rows.get(position).getHEAD_PATH()).error(R.drawable.avatar_default).into(holder.item_posts_avatar);
        Glide.with(context).load(I.BASE_URL+rows.get(position).getCOVERIMG()).into(holder.item_posts_icon);
        holder.item_topic_all_nick.setText(rows.get(position).getNICK_NAME());
        holder.item_posts_time.setText(rows.get(position).getCREATEDATE());
        holder.item_posts_title.setText(rows.get(position).getTITLE());
        //赞、收藏、评论没有值
        //holder.item_posts_good.setText(rows.get(position).get);
        holder.posts_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, DetailsPostsActivity.class);
                intent.putExtra("postsId",rows.get(position).getID());
                context.startActivity(intent);
            }
        });
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
    public LinearLayout posts_ll;
    public PostsViewHolder(View itemView) {
        super(itemView);
        item_posts_avatar= (CircleImageView) itemView.findViewById(R.id.img_topic_list_avatar);
        item_posts_time= (TextView) itemView.findViewById(R.id.tv_topic_list_time);
        item_posts_title= (TextView) itemView.findViewById(R.id.tv_topic_list_title);
        item_posts_icon= (ImageView) itemView.findViewById(R.id.img_topic_list_icon);
        item_posts_good= (TextView) itemView.findViewById(R.id.tv_topic_list_zan);
        item_posts_collect= (TextView) itemView.findViewById(R.id.tv_topic_list_collect);
        item_posts_comment= (TextView) itemView.findViewById(R.id.tv_topic_list_comment);
        item_topic_all_nick= (TextView) itemView.findViewById(R.id.tv_topic_list_nick);
        posts_ll = (LinearLayout) itemView.findViewById(R.id.posts_ll);
    }
}
