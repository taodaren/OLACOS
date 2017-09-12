package net.osplay.ui.adapter.sub;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import net.osplay.app.SetOnClickListen;
import net.osplay.olacos.R;

/**
 * Created by Administrator on 2017/9/11.
 */

public class CommunityAdapter extends RecyclerView.Adapter<CommunityViewHolder> {
    private SetOnClickListen setOnClickListen;
    public void onClick(SetOnClickListen setOnClickListen){
        this.setOnClickListen=setOnClickListen;
    }
    private Context context;
    public CommunityAdapter(Context context) {
        this.context = context;
    }

    @Override
    public CommunityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_community, parent, false);
        CommunityViewHolder viewHolder=new CommunityViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommunityViewHolder holder, final int position) {
        holder.community_item.setOnClickListener(new View.OnClickListener() {
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
class CommunityViewHolder extends RecyclerView.ViewHolder{

    public LinearLayout community_item;
    public CommunityViewHolder(View itemView) {
        super(itemView);
        community_item= (LinearLayout) itemView.findViewById(R.id.community_item);
    }
}
