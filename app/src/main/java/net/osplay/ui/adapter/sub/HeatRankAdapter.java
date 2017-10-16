package net.osplay.ui.adapter.sub;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.osplay.app.I;
import net.osplay.app.SetOnClickListen;
import net.osplay.olacos.R;
import net.osplay.service.entity.HotRanKingBean;
import net.osplay.ui.activity.sub.JoinCommunityDetailsActivity;

import java.util.List;

/**
 * Created by Administrator on 2017/9/6.
 */

/**
 * 加入社团的适配器
 */
public class HeatRankAdapter extends RecyclerView.Adapter<HeatRankViewHolder> {
    private Context mContext;
    private List<HotRanKingBean.RowsBean> rows;
    public HeatRankAdapter(Context mContext, List<HotRanKingBean.RowsBean> rows) {
        this.mContext = mContext;
        this.rows=rows;
    }

    @Override
    public HeatRankViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_heat_community, parent, false);
        HeatRankViewHolder viewHolder = new HeatRankViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HeatRankViewHolder holder, final int position) {
        Glide.with(mContext).load(I.BASE_URL+rows.get(position).getPHOTO()).into(holder.heat_avatar_iv);
        holder.heat_title_tv.setText(rows.get(position).getNAME());
        holder.heat_jj_tv.setText(rows.get(position).getINTRODUCTION());
        holder.heat_members_tv.setText(""+rows.get(position).getMEMBERCOUNT());
        holder.heat_works_tv.setText(""+rows.get(position).getZPCOUNT());
        holder.heat_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,JoinCommunityDetailsActivity.class);
                intent.putExtra("corporationId",rows.get(position).getID());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rows.size();
    }
}
class HeatRankViewHolder extends RecyclerView.ViewHolder{

    public LinearLayout heat_item;
    public ImageView heat_avatar_iv;
    public TextView heat_title_tv,heat_number_tv,heat_jj_tv,heat_members_tv,heat_works_tv;
    public HeatRankViewHolder(View itemView) {
        super(itemView);
        heat_item= (LinearLayout) itemView.findViewById(R.id.heat_item);
        heat_avatar_iv = (ImageView) itemView.findViewById(R.id.heat_avatar_iv);
        heat_title_tv = (TextView) itemView.findViewById(R.id.heat_title_tv);
        heat_number_tv = (TextView) itemView.findViewById(R.id.heat_number_tv);
        heat_jj_tv = (TextView) itemView.findViewById(R.id.heat_jj_tv);
        heat_members_tv = (TextView) itemView.findViewById(R.id.heat_members_tv);
        heat_works_tv = (TextView) itemView.findViewById(R.id.heat_works_tv);
    }
}