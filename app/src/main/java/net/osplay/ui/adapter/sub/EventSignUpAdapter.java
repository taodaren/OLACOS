package net.osplay.ui.adapter.sub;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import net.osplay.olacos.R;
import net.osplay.service.entity.LeagueBean;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by Administrator on 2017/9/5.
 * EventDetailsActivity类的报名人适配器
 */

public class EventSignUpAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context mContext;
    private  List<LeagueBean.TrailersBean> list;

    public EventSignUpAdapter(Context mContext,  List<LeagueBean.TrailersBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_sign, parent, false);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(mContext).load(list.get(position).getCoverImg()).into(holder.cir);
    }

    @Override
    public int getItemCount() {
        return 20;
    }
}
class ViewHolder extends RecyclerView.ViewHolder{

    public  CircleImageView cir;
    public ViewHolder(View itemView) {
        super(itemView);
        cir= (CircleImageView) itemView.findViewById(R.id.item_sign_iv);
    }
}
