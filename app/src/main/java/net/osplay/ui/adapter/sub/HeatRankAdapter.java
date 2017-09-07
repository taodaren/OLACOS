package net.osplay.ui.adapter.sub;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import net.osplay.olacos.R;
import net.osplay.service.entity.LeagueBean;
import net.osplay.ui.adapter.base.BaseRecyclerViewAdapter;
import net.osplay.ui.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/9/6.
 */
public class HeatRankAdapter extends BaseRecyclerViewAdapter<LeagueBean.TrailersBean> {
    private ImageView item_heat_community_icon;
    public HeatRankAdapter(Context context, final List<LeagueBean.TrailersBean> datas, int layoutId) {
        super(context, datas, layoutId);
    }
    @Override
    protected void bindData(BaseViewHolder holder, LeagueBean.TrailersBean data, int position) {
        item_heat_community_icon=holder.getView(R.id.item_heat_community_icon);
        Glide.with(context).load(datas.get(1).getCoverImg()).dontAnimate().fitCenter().crossFade().into(item_heat_community_icon);
    }
}
