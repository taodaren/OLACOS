package net.osplay.ui.adapter;

import android.app.Activity;
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

import net.osplay.app.AppHelper;
import net.osplay.app.I;
import net.osplay.olacos.R;
import net.osplay.service.entity.MyFansBean;
import net.osplay.ui.activity.sub.MinePageOtherActivity;

import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by fanyanmin on 2017/9/29.
 */

public class MyFansAdapter extends RecyclerView.Adapter<FansViewHolder>{

    private Activity context;
    private List<MyFansBean.RowsBean> rows;
    public MyFansAdapter(Activity context, List<MyFansBean.RowsBean> rows) {
        this.context = context;
        this.rows = rows;
    }

    @Override
    public FansViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_fans, parent, false);
        FansViewHolder viewHolder = new FansViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FansViewHolder holder, final int position) {
        Glide.with(context).load(I.BASE_URL+rows.get(position).getHEAD_PATH()).error(R.drawable.avatar_default).into(holder.fans_picture);
        holder.fans_name.setText(rows.get(position).getNICK_NAME());


            holder.fans_ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(rows.get(position).getID().equals(AppHelper.getInstance().getUser().getID())) {
                        context.finish();
                    }else{
                        Intent intent=new Intent(context, MinePageOtherActivity.class);
                        intent.putExtra("memberId",rows.get(position).getID());
                        context.startActivity(intent);
                    }

                }
            });
        }


    @Override
    public int getItemCount() {
        return rows.size();
    }
}
class FansViewHolder extends RecyclerView.ViewHolder{
    public ImageView fans_picture;
    public TextView fans_name;
    public LinearLayout fans_ll;
    public FansViewHolder(View itemView) {
        super(itemView);
        fans_picture= (ImageView) itemView.findViewById(R.id.fans_user_icon);
        fans_name= (TextView) itemView.findViewById(R.id.fans_nick_name);
        fans_ll= (LinearLayout) itemView.findViewById(R.id.fans_ll);
    }
}
