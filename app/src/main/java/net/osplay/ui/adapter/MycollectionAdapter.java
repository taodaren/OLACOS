package net.osplay.ui.adapter;

import android.content.Context;
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
import net.osplay.service.entity.MyAreaBean;
import net.osplay.service.entity.MycollectionBean;
import net.osplay.ui.activity.sub.MinePageOtherActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by fanyanmin on 2017/9/30.
 *
 */

public class MycollectionAdapter extends RecyclerView.Adapter<CollectionViewHolder> {
    private Context context;
    private List<MycollectionBean.RowsBean> rows;
    public MycollectionAdapter(Context context, List<MycollectionBean.RowsBean> rows) {
        this.context = context;
        this.rows = rows;
    }
    @Override
    public CollectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_collection, parent, false);
        CollectionViewHolder viewHolder = new CollectionViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CollectionViewHolder holder, final int position) {
        Glide.with(context).load(I.BASE_URL+rows.get(position).getHEAD_PATH()).into(holder.item_collection_avatar);
        Glide.with(context).load(I.BASE_URL+rows.get(position).getCOVERIMG()).into(holder.item_collection_icon);
        holder.item_collection_nick.setText(rows.get(position).getNICK_NAME());
        holder.item_collection_time.setText(rows.get(position).getCREATEDATE());
        holder.item_collection_title.setText(rows.get(position).getTITLE());
        holder.item_collection_good.setText(rows.get(position).getZAN_COUNT());
        holder.item_collection_collect.setText(rows.get(position).getCOLLECT_COUNT());
        holder.item_collection_comment.setText(rows.get(position).getPINGLUN_COUNT());
    }

    @Override
    public int getItemCount() {
        return rows.size();
    }
}
class CollectionViewHolder extends RecyclerView.ViewHolder{
    public CircleImageView item_collection_avatar;
    public TextView item_collection_time;//时间
    public TextView item_collection_title,item_collection_nick;
    public ImageView item_collection_icon;
    public TextView item_collection_good,item_collection_collect,item_collection_comment;

    public CollectionViewHolder(View itemView) {
        super(itemView);
        item_collection_avatar = (CircleImageView) itemView.findViewById(R.id.item_collection_avatar);
        item_collection_time = (TextView) itemView.findViewById(R.id.item_collection_time);
        item_collection_title = (TextView) itemView.findViewById(R.id.item_collection_title);
        item_collection_icon = (ImageView) itemView.findViewById(R.id.item_collection_icon);
        item_collection_good = (TextView) itemView.findViewById(R.id.item_collection_good);
        item_collection_collect = (TextView) itemView.findViewById(R.id.item_collection_collect);
        item_collection_comment = (TextView) itemView.findViewById(R.id.item_collection_comment);
        item_collection_nick = (TextView) itemView.findViewById(R.id.item_collection_nick);
    }
}
