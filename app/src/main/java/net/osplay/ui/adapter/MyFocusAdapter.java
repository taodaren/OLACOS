package net.osplay.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.osplay.app.I;
import net.osplay.olacos.R;
import net.osplay.service.entity.MyFocusBean;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by fanyanmin on 2017/9/30.
 */

public class MyFocusAdapter extends RecyclerView.Adapter<FocusViewHolder> {
    private Context context;
    private List<MyFocusBean.RowsBean> rows;

    public MyFocusAdapter(Context context, List<MyFocusBean.RowsBean> rows) {
        this.context = context;
        this.rows = rows;
    }

    @Override
    public FocusViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_focus, parent, false);
        FocusViewHolder viewHolder = new FocusViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FocusViewHolder holder, int position) {
        Glide.with(context).load(I.BASE_URL+rows.get(position).getHEAD_PATH()).placeholder(R.drawable.all4).into(holder.focus_user_icon);
        holder.focus_nick_name.setText(rows.get(position).getNICK_NAME());
    }

    @Override
    public int getItemCount() {
        return rows.size();
    }
}
class FocusViewHolder extends RecyclerView.ViewHolder{

    public CircleImageView focus_user_icon;
    public TextView focus_nick_name;
    public FocusViewHolder(View itemView) {
        super(itemView);
        focus_user_icon= (CircleImageView) itemView.findViewById(R.id.focus_user_icon);
        focus_nick_name= (TextView) itemView.findViewById(R.id.focus_nick_name);
    }
}
