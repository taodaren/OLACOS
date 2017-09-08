package net.osplay.ui.adapter.sub;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.osplay.olacos.R;

/**
 * Created by Administrator on 2017/9/7.
 */

public class JoinCommunityDetailsAdapter extends RecyclerView.Adapter<JoinCommunityDetailsViewHolder> {
    private Context mContext;
    public JoinCommunityDetailsAdapter(Context mContext){
        this.mContext=mContext;
    }
    @Override
    public JoinCommunityDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_jcd, parent, false);
        JoinCommunityDetailsViewHolder viewHolder = new JoinCommunityDetailsViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(JoinCommunityDetailsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }
}
class JoinCommunityDetailsViewHolder extends RecyclerView.ViewHolder{

    public JoinCommunityDetailsViewHolder(View itemView) {
        super(itemView);
    }
}
