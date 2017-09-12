package net.osplay.ui.adapter.sub;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import net.osplay.service.entity.goods.ResultBeanData;

/**
 * Created by Administrator on 2017/9/12.
 */

public class MallAdapter extends RecyclerView.Adapter {

    /**
     * 五种类型
     */
    //轮播
    public static final int BANNER = 0;
    //频道
    public static final int CHANNEL = 1;
    //活动
    public static final int ACT = 2;
    //秒杀
    public static final int SECKILL = 3;
    //推荐
    public static final int RECOMMEND = 4;
    //热卖
    public static final int HOT = 5;
    private  Context mContext;
    private  ResultBeanData.ResultBean resultBean;
    private  LayoutInflater mLayoutInflater;//初始化布局
    //当前类型
    public int currentType = BANNER;

    public MallAdapter(Context mContext, ResultBeanData.ResultBean resultBean) {
        this.mContext=mContext;
        this.resultBean=resultBean;
         mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    //得到类型
    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case ACT:
                currentType = ACT;
                break;
            case SECKILL:
                currentType = SECKILL;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
            case HOT:
                currentType = HOT;
                break;
        }
        return currentType;
    }

    @Override
    public int getItemCount() {
        //开发过程中从1--》6
        return 1;
    }
}
