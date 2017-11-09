package net.osplay.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.luck.picture.lib.entity.LocalMedia;

import net.osplay.app.SetOnClickListen;
import net.osplay.olacos.R;
import net.osplay.service.entity.YSBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/7.
 */

public class PublishPostsAdapter extends  RecyclerView.Adapter<PublishPostsViewHolder>  {
    private SetOnClickListen setOnClickListen;

    public void onClick(SetOnClickListen setOnClickListen) {
        this.setOnClickListen = setOnClickListen;
    }


    private Context context;
    private  List<YSBean> ysList;

    public PublishPostsAdapter(Context context,  List<YSBean> ysList) {
        this.context = context;
        this.ysList = ysList;
    }

    @Override
    public PublishPostsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.publish_posts_item, parent, false);
        PublishPostsViewHolder viewHolder = new PublishPostsViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PublishPostsViewHolder holder, final int position) {
        Glide.with(context).load(ysList.get(position).getYs()).into(holder.publish_posts_item_iv);
        holder.publish_posts_delect_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOnClickListen.setOnClick(position);

            }
        });
        holder.publish_posts_item_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return ysList.size();
    }
}
class PublishPostsViewHolder extends RecyclerView.ViewHolder{
    public ImageView publish_posts_item_iv,publish_posts_delect_iv;
    public PublishPostsViewHolder(View itemView) {
        super(itemView);
        publish_posts_item_iv = (ImageView) itemView.findViewById(R.id.publish_posts_item_iv);
        publish_posts_delect_iv = (ImageView) itemView.findViewById(R.id.publish_posts_delect_iv);
    }
}
