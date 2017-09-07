package net.osplay.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import net.osplay.data.bean.HomeData;
import net.osplay.olacos.R;
import net.osplay.service.entity.HomeBannerBean;
import net.osplay.utils.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页适配器
 */

public class TabHomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "TabHomeAdapter";
    public static final int TYPE_BANNER = 1;
    public static final int TYPE_CATE = 2;
    public static final int TYPE_TAB = 3;
    public static final int TYPE_DETAIL = 4;

    private Context mContext;
    private LayoutInflater mInflater;
    private List<HomeData> mDataList;

    public TabHomeAdapter(Context context, List<HomeData> data) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mDataList = new ArrayList<>();
        mDataList.addAll(data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_BANNER:
                return new BannerViewHolder(mInflater.inflate(R.layout.layout_home_banner, parent, false));
            default:
                Log.e(TAG, "onCreateViewHolder: is null");
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (mDataList.get(position).getItemType()) {
            case TYPE_BANNER:
                ((BannerViewHolder) holder).bindData((List<HomeBannerBean>) mDataList.get(position).getData());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mDataList.get(position).getItemType();
    }

    //////////////////// view holder ////////////////////

    private static class BannerViewHolder extends RecyclerView.ViewHolder {
        Banner banner;

        BannerViewHolder(View itemView) {
            super(itemView);
            banner = (Banner) itemView.findViewById(R.id.banner_home);
        }

        void bindData(List<HomeBannerBean> data) {
            if (data != null && !data.isEmpty()) {
                List<String> images = new ArrayList<>();
                for (HomeBannerBean bean : data) {
                    images.add(bean.getImgUrl());
                }
                bindBanner(images);
            }
        }

        private void bindBanner(List<String> images) {
            //设置banner样式
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            //设置图片加载器
            banner.setImageLoader(new GlideImageLoader());
            //设置图片集合
            banner.setImages(images);
            //设置banner动画效果
            banner.setBannerAnimation(Transformer.DepthPage);
            //设置标题集合（当banner样式有显示title时）
//            banner.setBannerTitles(titles);
            //设置自动轮播，默认为true
            banner.isAutoPlay(true);
            //设置轮播时间
            banner.setDelayTime(3000);
            //设置指示器位置（当banner模式中有指示器时）
            banner.setIndicatorGravity(BannerConfig.CENTER);
            //banner设置方法全部调用完毕时最后调用
            banner.start();
        }
    }

}
