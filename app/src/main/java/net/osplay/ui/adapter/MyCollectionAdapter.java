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
import net.osplay.app.SetOnClickListen;
import net.osplay.olacos.R;
import net.osplay.service.entity.MyCollectionBean;
import net.osplay.ui.activity.sub.DetailsPostsActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 个人中心----我的收藏
 */

public class MyCollectionAdapter extends RecyclerView.Adapter<CollectionViewHolder> {
    private SetOnClickListen setOnClickListen;

    public void onClick(SetOnClickListen setOnClickListen) {
        this.setOnClickListen = setOnClickListen;
    }

    private Context context;
    private List<MyCollectionBean.RowsBean> rows;

    public MyCollectionAdapter(Context context, List<MyCollectionBean.RowsBean> rows) {
        this.context = context;
        this.rows = rows;
    }

    @Override
    public CollectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_word_topic_list, parent, false);
        CollectionViewHolder viewHolder = new CollectionViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CollectionViewHolder holder, final int position) {
        Glide.with(context).load(I.BASE_URL + rows.get(position).getHEAD_PATH()).error(R.drawable.avatar_default).into(holder.item_collection_avatar);
        Glide.with(context).load(I.BASE_URL + rows.get(position).getCOVERIMG()).into(holder.item_collection_icon);
        holder.item_collection_nick.setText(rows.get(position).getNICK_NAME());
        holder.item_collection_time.setText(rows.get(position).getCREATEDATE());
        holder.item_collection_title.setText(rows.get(position).getTITLE());
        holder.item_collection_good.setText(rows.get(position).getZAN_COUNT());
        holder.item_collection_collect.setText(rows.get(position).getCOLLECT_COUNT());
        holder.item_collection_comment.setText(rows.get(position).getPINGLUN_COUNT());
        holder.ll_topic_list_zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnClickListen.setOnClick(position, holder.item_collection_good, holder.item_collection_collect, holder.item_collection_comment, holder.img_topic_list_zan, holder.img_topic_list_collect);
            }
        });

        //holder.item_posts_good.setText(rows.get(position).get);
        holder.posts_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsPostsActivity.class);
                intent.putExtra("postsId", rows.get(position).getID());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rows.size();
    }
}

class CollectionViewHolder extends RecyclerView.ViewHolder {
    public CircleImageView item_collection_avatar;
    public TextView item_collection_time;//时间
    public TextView item_collection_title, item_collection_nick;
    public ImageView item_collection_icon;
    public TextView item_collection_good, item_collection_collect, item_collection_comment;
    public ImageView img_topic_list_zan, img_topic_list_collect;
    public LinearLayout ll_topic_list_zan;
    public LinearLayout posts_ll;

    public CollectionViewHolder(View itemView) {
        super(itemView);
        item_collection_avatar = (CircleImageView) itemView.findViewById(R.id.img_topic_list_avatar);
        item_collection_time = (TextView) itemView.findViewById(R.id.tv_topic_list_time);
        item_collection_title = (TextView) itemView.findViewById(R.id.tv_topic_list_title);
        item_collection_icon = (ImageView) itemView.findViewById(R.id.img_topic_list_icon);
        item_collection_good = (TextView) itemView.findViewById(R.id.tv_topic_list_zan);
        item_collection_collect = (TextView) itemView.findViewById(R.id.tv_topic_list_collect);
        item_collection_comment = (TextView) itemView.findViewById(R.id.tv_topic_list_comment);
        item_collection_nick = (TextView) itemView.findViewById(R.id.tv_topic_list_nick);
        ll_topic_list_zan = (LinearLayout) itemView.findViewById(R.id.ll_topic_list_zan);
        img_topic_list_zan = (ImageView) itemView.findViewById(R.id.img_topic_list_zan);
        img_topic_list_collect = (ImageView) itemView.findViewById(R.id.img_topic_list_collect);
        posts_ll = (LinearLayout) itemView.findViewById(R.id.posts_ll);
    }
}
