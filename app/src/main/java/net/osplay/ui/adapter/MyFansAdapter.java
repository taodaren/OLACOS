package net.osplay.ui.adapter;

import android.content.Context;
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
import net.osplay.service.entity.MyFansBean;

import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by fanyanmin on 2017/9/29.
 */

public class MyFansAdapter extends RecyclerView.Adapter<FansViewHolder>{

    private Context context;
    private List<MyFansBean.RowsBean> rows;
    public MyFansAdapter(Context context, List<MyFansBean.RowsBean> rows) {
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
    public void onBindViewHolder(FansViewHolder holder, int position) {
        Glide.with(context).load(I.BASE_URL+rows.get(position).getHEAD_PATH()).into(holder.fans_picture);
        holder.fans_name.setText(rows.get(position).getNICK_NAME());
    }

    @Override
    public int getItemCount() {
        return rows.size();
    }
}
class FansViewHolder extends RecyclerView.ViewHolder{
    public ImageView fans_picture;
    public TextView fans_name;
    public FansViewHolder(View itemView) {
        super(itemView);
        fans_picture= (ImageView) itemView.findViewById(R.id.fans_user_icon);
        fans_name= (TextView) itemView.findViewById(R.id.fans_nick_name);

    }
}
