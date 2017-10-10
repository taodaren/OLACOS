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
import net.osplay.olacos.R;
import net.osplay.service.entity.AllCommunityBean;
import net.osplay.ui.activity.sub.JoinCommunityDetailsActivity;
import net.osplay.ui.activity.sub.LoginActivity;

import java.util.List;


public class AllCommunityAdapter extends RecyclerView.Adapter<AllCommunityViewHolder> {
    private Context context;
    private List<AllCommunityBean.RowsBean> rows;

    public AllCommunityAdapter(Context context, List<AllCommunityBean.RowsBean> rows) {
        this.context = context;
        this.rows = rows;
    }


    @Override
    public AllCommunityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_listview_ss, parent, false);
        AllCommunityViewHolder viewHolder = new AllCommunityViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AllCommunityViewHolder holder, int position) {
        Glide.with(context).load(I.BASE_URL+rows.get(position).getPHOTO()).error(R.drawable.avatar_default).into(holder.all_im);
        holder.all_tv.setText(rows.get(position).getNAME());
        holder.all_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, JoinCommunityDetailsActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return rows.size();
    }
}
class AllCommunityViewHolder extends RecyclerView.ViewHolder{

    public ImageView all_im;
    public TextView all_tv;
    public LinearLayout all_item;
    public AllCommunityViewHolder(View itemView) {
        super(itemView);
        all_im= (ImageView) itemView.findViewById(R.id.all_im);
        all_tv= (TextView) itemView.findViewById(R.id.all_tv);
        all_item= (LinearLayout) itemView.findViewById(R.id.all_item);
    }
}