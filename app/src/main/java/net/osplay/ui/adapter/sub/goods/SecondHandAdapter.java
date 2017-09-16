package net.osplay.ui.adapter.sub.goods;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.osplay.olacos.R;
import net.osplay.service.entity.goods.SecondHandMallBean;
import net.osplay.service.entity.goods.TypeListBean;
import net.osplay.utils.Constants;

import java.util.List;

/**
 * Created by Administrator on 2017/9/12.
 */

public class SecondHandAdapter extends RecyclerView.Adapter<SecondHandViewHolder> {

    private Context context;
    private List<TypeListBean.ResultBean.PageDataBean> page_data;
    public SecondHandAdapter(Context context,List<TypeListBean.ResultBean.PageDataBean> page_data) {
        this.context = context;
        this.page_data = page_data;
    }

    @Override
    public SecondHandViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_mall_secondhand, parent, false);
        SecondHandViewHolder viewHolder=new SecondHandViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SecondHandViewHolder holder, int position) {
        Log.e("JGB",page_data.toString());
        Picasso.with(context).load(Constants.BASE_URl_IMAGE+page_data.get(position).getFigure()).into(holder.img_mall_secondhand);
        holder.text_title_mall_secondhand.setText(page_data.get(position).getName());
        String name = page_data.get(0).getName();
        Log.e("TAG",name);
    }

    @Override
    public int getItemCount() {
        return page_data.size();
    }
}
class SecondHandViewHolder extends RecyclerView.ViewHolder{
    public ImageView img_mall_secondhand;
    public TextView text_title_mall_secondhand,text_price_mall_secondhand;
    public SecondHandViewHolder(View itemView) {
        super(itemView);
        img_mall_secondhand= (ImageView) itemView.findViewById(R.id.img_mall_secondhand);
        text_title_mall_secondhand= (TextView) itemView.findViewById(R.id.text_title_mall_secondhand);
        text_price_mall_secondhand= (TextView) itemView.findViewById(R.id.text_price_mall_secondhand);
    }
}
