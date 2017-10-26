package net.osplay.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.osplay.app.SetOnClickListen;
import net.osplay.olacos.R;
import net.osplay.service.entity.RecommendBean;
import net.osplay.ui.activity.sub.MinePageSelfActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
//import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by acer-PC on 2017/8/30.
 */
public class RecommendAdapter extends RecyclerView.Adapter<RecommendViewHolder> {
    private Context mContext;
    private  List<RecommendBean.RowsBean> rows;

    public RecommendAdapter(Context mContext, List<RecommendBean.RowsBean> rows) {
        this.mContext = mContext;
        this.rows = rows;
    }

    @Override
    public RecommendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_league, parent, false);
        RecommendViewHolder viewHolder = new RecommendViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecommendViewHolder holder, final int position) {
//        holder.tv.setText(list.get(position).getVideoTitle());
//        holder.jcVideoPlayer.setUp(list.get(position).getHightUrl(), JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL);
//        Picasso.with(mContext).load(list.get(position).getCoverImg()).into(holder.jcVideoPlayer.thumbImageView);
//        holder.civ.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getActivity(), MinePageSelfActivity.class));
//                getActivity().finish();
//
//            }
//        });
    }



    @Override
    public int getItemCount() {
        return rows.size();
    }
}
class RecommendViewHolder extends RecyclerView.ViewHolder{

    //public JCVideoPlayerStandard jcVideoPlayer;
    public TextView tv;
    public CircleImageView civ;
    public RecommendViewHolder(View itemView) {
        super(itemView);
       // jcVideoPlayer= (JCVideoPlayerStandard) itemView.findViewById(R.id.league_jcVideoPlayer);
        tv= (TextView) itemView.findViewById(R.id.league_title_tv);
        civ= (CircleImageView) itemView.findViewById(R.id.league_avatar_img);
    }
}

