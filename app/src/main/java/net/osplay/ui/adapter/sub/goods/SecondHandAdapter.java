package net.osplay.ui.adapter.sub.goods;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import net.osplay.olacos.R;
import net.osplay.service.entity.goods.ResultBeanData;
import net.osplay.utils.Constants;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/12.
 * 二手商城适配器
 */

public class SecondHandAdapter extends RecyclerView.Adapter {
    public static final int BANNER = 0; //轮播
    public static final int CATEGORY = 1; //轮播
    private Context mContext;
    private ResultBeanData.ResultBean resultBean;
    private LayoutInflater mLayoutInflater;//初始化布局
    //当前类型
    public int currentType = BANNER;

    public SecondHandAdapter(Context mContext, ResultBeanData.ResultBean resultBean) {
        this.mContext = mContext;
        this.resultBean = resultBean;
        mLayoutInflater = LayoutInflater.from(mContext);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==BANNER){
            return new BannerViewHolder(mContext, mLayoutInflater.inflate(R.layout.hand_banner_viewpager, null));
        }if(viewType==CATEGORY){
            return new CategoryviewHolder(mContext, mLayoutInflater.inflate(R.layout.hand_category_gridview, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData(resultBean.getBanner_info());
        }else if(getItemViewType(position)==CATEGORY){
            CategoryviewHolder categoryviewHolder= (CategoryviewHolder) holder;
            categoryviewHolder.setData(resultBean.getChannel_info());
        }
    }


    class BannerViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        private Banner hand_banner;
        private List<ResultBeanData.ResultBean.BannerInfoBean> data;

        public BannerViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext=mContext;
            hand_banner= (Banner) itemView.findViewById(R.id.hand_banner);

        }

        public void setData(List<ResultBeanData.ResultBean.BannerInfoBean> data) {
            hand_banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            List<String> imageUris = new ArrayList<>();
            for (int i = 0; i < resultBean.getBanner_info().size(); i++) {
                imageUris.add(resultBean.getBanner_info().get(i).getImage());
            }
            hand_banner.setBannerAnimation(Transformer.Accordion);
            hand_banner.setImages(imageUris);
            hand_banner.setImageLoader(new HandGlideImageLoaders());
            hand_banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
                }
            });
            hand_banner.start();
        }
    }

    class CategoryviewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private GridView hand_gridview;
        private CategoryGridAdapter agAdapter;
        public CategoryviewHolder(Context mContext, View viewItem) {
            super(viewItem);
            this.mContext=mContext;
            hand_gridview= (GridView) itemView.findViewById(R.id.hand_gridview);
        }
        public void setData(List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
            agAdapter=new CategoryGridAdapter(mContext,channel_info);
            hand_gridview.setAdapter(agAdapter);
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (position){
            case BANNER:
                currentType=BANNER;
                break;
            case CATEGORY:
                currentType=CATEGORY;
                break;
        }
        return currentType;
    }

    @Override
    public int getItemCount() {
        return 2;
    }


}
//轮播的图片加载
class HandGlideImageLoaders extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context)
                .load(Constants.BASE_URl_IMAGE + path)
                .into(imageView);
    }
}
