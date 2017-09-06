package net.osplay.ui.adapter.sub;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import net.osplay.olacos.R;
import net.osplay.service.entity.LeagueBean;
import net.osplay.ui.adapter.base.BaseRecyclerViewAdapter;
import net.osplay.ui.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/9/5.
 * evevtDetalilsadapter的图片展示适配器  还不知怎么展示  暂时保留
 */

public class EventIconAdapter extends BaseRecyclerViewAdapter<LeagueBean.TrailersBean> {

    private ImageView iv;
    public EventIconAdapter(Context context, List<LeagueBean.TrailersBean> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    protected void bindData(BaseViewHolder holder, LeagueBean.TrailersBean data, int position) {
        iv=holder.getView(R.id.item_event_icon);
        Picasso.with(context).load(data.getCoverImg()).into(iv);
    }
}
