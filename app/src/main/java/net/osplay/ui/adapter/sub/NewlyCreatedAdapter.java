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
import net.osplay.service.entity.HotRanKingBean;
import net.osplay.service.entity.goods.NewCreatedBean;
import net.osplay.service.entity.goods.ResultBeanData;
import net.osplay.ui.activity.sub.JoinCommunityDetailsActivity;

import java.util.List;

/**
 * Created by Administrator on 2017/9/9.
 */

public class NewlyCreatedAdapter extends RecyclerView.Adapter<NewlyViewHolder> {
    private Context mContext;
    private List<NewCreatedBean.RowsBean> rows;
    public NewlyCreatedAdapter(Context mContext, List<NewCreatedBean.RowsBean> rows) {
        this.mContext = mContext;
        this.rows=rows;
    }

    @Override
    public NewlyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_newly_created, parent, false);
        NewlyViewHolder viewHolder = new NewlyViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewlyViewHolder holder, final int position) {
            Glide.with(mContext).load(I.BASE_URL+rows.get(position).getPHOTO()).into(holder.newly_avatar_iv);
            holder.newly_title_tv.setText(rows.get(position).getNAME());
            holder.newly_jianjie_tv.setText(rows.get(position).getINTRODUCTION());
            holder.newly_members_tv.setText(""+rows.get(position).getMEMBERCOUNT());
            holder.newly_works_tv.setText(""+rows.get(position).getZPCOUNT());
        holder.newly_item.setOnClickListener(new View.OnClickListener() {
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
class NewlyViewHolder extends RecyclerView.ViewHolder{
    public LinearLayout newly_item;
    public ImageView newly_avatar_iv;//头像
    public TextView newly_title_tv,newly_jianjie_tv,newly_members_tv,newly_works_tv;//名称、简介，成员人数，作品数量
   public NewlyViewHolder(View itemView) {
        super(itemView);
        newly_item= (LinearLayout) itemView.findViewById(R.id.newly_item);
        newly_avatar_iv = (ImageView) itemView.findViewById(R.id.newly_avatar_iv);
       newly_title_tv = (TextView) itemView.findViewById(R.id.newly_title_tv);
       newly_jianjie_tv = (TextView) itemView.findViewById(R.id.newly_jianjie_tv);
       newly_members_tv = (TextView) itemView.findViewById(R.id.newly_members_tv);
       newly_works_tv = (TextView) itemView.findViewById(R.id.newly_works_tv);
    }
}
