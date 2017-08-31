package net.osplay.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.osplay.olacos.R;
import net.osplay.service.entity.LeagueBean;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by acer-PC on 2017/8/30.
 */
public class LeagueAdapter extends RecyclerView.Adapter<LeagueViewHolder> {
    private Context mContext;
    private  List<LeagueBean.TrailersBean> list;

    public LeagueAdapter(Context mContext,  List<LeagueBean.TrailersBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public LeagueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_league, parent, false);
        LeagueViewHolder viewHolder = new LeagueViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LeagueViewHolder holder, int position) {
        holder.tv.setText(list.get(position).getVideoTitle());
        holder.jcVideoPlayer.setUp(list.get(position).getHightUrl(), JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL);
        Picasso.with(mContext).load(list.get(position).getCoverImg()).into(holder.jcVideoPlayer.thumbImageView);
    }



    @Override
    public int getItemCount() {
        return list.size();
    }
}
class LeagueViewHolder extends RecyclerView.ViewHolder{

    public JCVideoPlayerStandard jcVideoPlayer;
    public TextView tv;
    public LeagueViewHolder(View itemView) {
        super(itemView);
        jcVideoPlayer= (JCVideoPlayerStandard) itemView.findViewById(R.id.league_jcVideoPlayer);
        tv= (TextView) itemView.findViewById(R.id.league_title_tv);
    }
}
