package net.osplay.ui.adapter.sub;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.osplay.olacos.R;
import net.osplay.service.entity.LeagueBean;
import net.osplay.service.entity.RecommendBean;
import net.osplay.ui.adapter.base.BaseRecyclerViewAdapter;
import net.osplay.ui.adapter.base.BaseViewHolder;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by acer-PC on 2017/9/1.
 * newsfragment的帖子显示适配器
 */

public class RecommendAdapter extends BaseRecyclerViewAdapter<LeagueBean.TrailersBean>{
    public JCVideoPlayerStandard jcVideoPlayer;
    public TextView tv;
    private CircleImageView civ;
    public RecommendAdapter(Context context, List<LeagueBean.TrailersBean> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    protected void bindData(final BaseViewHolder holder, LeagueBean.TrailersBean data, final int position) {
        jcVideoPlayer=holder.getView(R.id.league_jcVideoPlayer);
        tv=holder.getView(R.id.league_title_tv);
        civ=holder.getView(R.id.league_avatar_img);
        jcVideoPlayer.setUp(data.getHightUrl(), JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL);
        tv.setText(data.getVideoTitle());
        Picasso.with(context).load(data.getCoverImg()).into(jcVideoPlayer.thumbImageView);
        civ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListner != null) {
                    onItemClickListner.onItemClickListner(holder.getRootView(), position);
                }
            }
        });
    }

}
