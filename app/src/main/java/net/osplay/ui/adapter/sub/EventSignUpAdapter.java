package net.osplay.ui.adapter.sub;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import net.osplay.olacos.R;
import net.osplay.service.entity.LeagueBean;
import net.osplay.ui.adapter.base.BaseRecyclerViewAdapter;
import net.osplay.ui.adapter.base.BaseViewHolder;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by Administrator on 2017/9/5.
 * EventDetailsActivity类的报名人适配器
 */

public class EventSignUpAdapter extends BaseRecyclerViewAdapter<LeagueBean.TrailersBean>{
    private CircleImageView cir;
    public EventSignUpAdapter(Context context, List<LeagueBean.TrailersBean> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    protected void bindData(BaseViewHolder holder, LeagueBean.TrailersBean data, int position) {
        cir=holder.getView(R.id.item_sign_iv);
        Picasso.with(context).load(data.getCoverImg()).into(cir);

    }
}
