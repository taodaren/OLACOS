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
import net.osplay.service.entity.MyAreaBean;

import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by fanyanmin on 2017/9/29.
 */

public class MyAreaAdapter extends RecyclerView.Adapter<AreaViewHolder>{

    private Context context;
    private List<MyAreaBean.RowsBean> rows;
    public MyAreaAdapter(Context context, List<MyAreaBean.RowsBean> rows) {
        this.context = context;
        this.rows = rows;
    }

    @Override
    public AreaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_area, parent, false);
        AreaViewHolder viewHolder = new AreaViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AreaViewHolder holder, int position) {
        Glide.with(context).load(I.BASE_URL+rows.get(position).getPART_PATH()).into(holder.area_picture);
        holder.area_name.setText(rows.get(position).getPART());
    }

    @Override
    public int getItemCount() {
        return rows.size();
    }
}
class AreaViewHolder extends RecyclerView.ViewHolder{
    public ImageView area_picture;
    public TextView area_name;
    public LinearLayout myarea;
    public AreaViewHolder(View itemView) {
        super(itemView);
        area_picture= (ImageView) itemView.findViewById(R.id.area_picture);
        area_name= (TextView) itemView.findViewById(R.id.area_name);
        myarea= (LinearLayout) itemView.findViewById(R.id.myarea);
    }
}
