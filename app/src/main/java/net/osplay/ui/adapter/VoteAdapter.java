package net.osplay.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import net.osplay.olacos.R;

/**
 * Created by acer-PC on 2017/8/29.
 */

public class VoteAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context mContext;

    public VoteAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.vote_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}

class ViewHolder extends RecyclerView.ViewHolder {

    public ImageView icon;


    public ViewHolder(View itemView) {
        super(itemView);
        icon = (ImageView) itemView.findViewById(R.id.vote_item_img);
    }

}






