package net.osplay.ui.adapter.sub;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import net.osplay.olacos.R;

/**
 * Created by Administrator on 2017/9/6.
 */

/**
 * 加入社团的适配器
 */
public class HeatRankAdapter extends RecyclerView.Adapter<HeatRankViewHolder> {
    private Context mContext;
//    private SetOnClickListen setOnClickListen;

    public HeatRankAdapter(Context mContext) {
        this.mContext = mContext;
    }

//    public void onClick(SetOnClickListen setOnClickListen) {
//        this.setOnClickListen = setOnClickListen;
//    }

    @Override
    public HeatRankViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_heat_community, parent, false);
        HeatRankViewHolder viewHolder = new HeatRankViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HeatRankViewHolder holder, final int position) {
        holder.heat_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                setOnClickListen.setOnClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 20;
    }
}

class HeatRankViewHolder extends RecyclerView.ViewHolder {

    public LinearLayout heat_item;

    public HeatRankViewHolder(View itemView) {
        super(itemView);
        heat_item = (LinearLayout) itemView.findViewById(R.id.heat_item);
    }
}