package net.osplay.ui.adapter.sub;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import net.osplay.olacos.R;

/**
 * Created by Administrator on 2017/9/9.
 */

public class NewlyCreatedAdapter extends RecyclerView.Adapter<NewlyViewHolder> {
//    private SetOnClickListen setOnClickListen;
//    public void onClick(SetOnClickListen setOnClickListen){
//        this.setOnClickListen=setOnClickListen;
//    }
    private Context mContext;
    public NewlyCreatedAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public NewlyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_newly_created, parent, false);
        NewlyViewHolder viewHolder = new NewlyViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewlyViewHolder holder, final int position) {
        holder.newly_item.setOnClickListener(new View.OnClickListener() {
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
class NewlyViewHolder extends RecyclerView.ViewHolder{
    public LinearLayout newly_item;
    public NewlyViewHolder(View itemView) {
        super(itemView);
        newly_item= (LinearLayout) itemView.findViewById(R.id.newly_item);
    }
}
