package net.osplay.ui.adapter.base;

/**
 * Created by acer-PC on 2017/9/1.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yanzhenjie.nohttp.rest.OnResponseListener;

import java.util.List;


public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    public Context context;
    private LayoutInflater inflater;
    private List<T> datas;
    private int layoutId;
    protected OnItemClickListner onItemClickListner;//单击事件
    protected OnItemLongClickListner onItemLongClickListner;//长按单击事件
    private boolean clickFlag = true;//单击事件和长单击事件的屏蔽标识

    public BaseRecyclerViewAdapter(Context context, List<T> datas, int layoutId) {
        this.context = context;
        this.datas = datas;
        this.layoutId = layoutId;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //使用holder绑定监听
        BaseViewHolder holder = new BaseViewHolder(inflater.inflate(layoutId, parent, false));
        //MaterialRippleLayout为点击时的效果，使用这种效果的话需要指定控件，一般情况不用，特殊情况打开，依赖为：compile 'com.balysv:material-ripple:1.0.2'
//        MaterialRippleLayout.on(holder.getView(R.id.ll_all))
//                .rippleOverlay(true)
//                .rippleAlpha(0.2f)
//                .rippleColor(context.getResources().getColor(R.color.colorAccent))
//                .rippleHover(true)
//                .create();
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        bindData(holder, datas.get(position), position);
    }


    /**
     * 点击事件分为长按和点击，可使用view.findviewbyid查找指定控件进行监听处理
     * @return
     */
    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    protected abstract void bindData(BaseViewHolder holder, T data, int position);

    public void setOnItemClickListner(OnItemClickListner onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }

    public void setOnItemLongClickListner(OnItemLongClickListner onItemLongClickListner) {
        this.onItemLongClickListner = onItemLongClickListner;
    }




    public interface OnItemClickListner {
        void onItemClickListner(View v, int position);
    }

    public interface OnItemLongClickListner {
        void onItemLongClickListner(View v, int position);
    }
}