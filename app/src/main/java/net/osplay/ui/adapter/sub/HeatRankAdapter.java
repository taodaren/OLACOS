package net.osplay.ui.adapter.sub;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.osplay.app.SetOnClickListen;
import net.osplay.olacos.R;
import net.osplay.service.entity.LeagueBean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/6.
 */

/**
 * 加入社团的适配器
 */
public class HeatRankAdapter extends RecyclerView.Adapter<HeatViewHolder> {
    private SetOnClickListen setOnClickListen;
    private Context mContext;
    private List<LeagueBean.TrailersBean> list;

    public void onClick(SetOnClickListen setOnClickListen){
        this.setOnClickListen=setOnClickListen;
    }

    public HeatRankAdapter(Context mContext,  List<LeagueBean.TrailersBean> list) {
        this.mContext = mContext;
        this.list = list;
    }
    @Override
    public HeatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_heat_community, parent, false);
        HeatViewHolder viewHolder = new HeatViewHolder(inflate);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(HeatViewHolder holder, final int position) {
        Glide.with(mContext).load(list.get(position).getCoverImg()).dontAnimate().fitCenter().crossFade().into(holder.item_heat_community_icon);
        holder.item_heat_community_tv.setText(list.get(position).getMovieName());
        holder.item_heat_community_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnClickListen.setOnClick(position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return 20;
    }
}

class HeatViewHolder extends RecyclerView.ViewHolder{

    public ImageView item_heat_community_icon;
    public TextView item_heat_community_tv;
    public RelativeLayout item_heat_community_rl;
    public HeatViewHolder(View itemView) {
        super(itemView);
        item_heat_community_icon= (ImageView) itemView.findViewById(R.id.item_heat_community_icon);
        item_heat_community_tv= (TextView) itemView.findViewById(R.id.item_heat_community_tv);
        item_heat_community_rl= (RelativeLayout) itemView.findViewById(R.id.item_heat_community_rl);
    }
}