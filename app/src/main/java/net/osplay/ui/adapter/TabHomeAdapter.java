package net.osplay.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youth.banner.Banner;

import net.osplay.olacos.R;
import net.osplay.service.entity.HomeBannerBean;
import net.osplay.service.entity.HomeCateBean;
import net.osplay.service.entity.HomeDetailBean;
import net.osplay.utils.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页适配器
 */

public class TabHomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "TabHomeAdapter";
    private static final int TYPE_BANNER = 1;
    private static final int TYPE_CATE = 2;
    private static final int TYPE_TAB = 3;
    private static final int TYPE_DETAIL = 4;

    private Context mContext;
    private LayoutInflater mInflater;
    private int mItemCount = 1;//recycler item 个数
    private int mCurrentType = 0;
    private List<HomeBannerBean> bannerBeanList;
    private List<HomeCateBean> cateBeanList;
    private List<HomeDetailBean> detailBeanList;

    public TabHomeAdapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
        bannerBeanList = new ArrayList<>();
    }

    public TabHomeAdapter(Context mContext, List<HomeBannerBean> bannerBeanList) {
        this.mContext = mContext;
        this.bannerBeanList = bannerBeanList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:
                return new BannerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_home_banner, parent, false));
            default:
                Log.e(TAG, "onCreateViewHolder: is null");
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BannerViewHolder) {
            bindBanner(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return mItemCount;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case TYPE_BANNER:
                mCurrentType = TYPE_BANNER;
                break;
            default:
        }
        return mCurrentType;
    }

    //////////////////// bind data ////////////////////

    private void bindBanner(RecyclerView.ViewHolder holder, int position) {
        BannerViewHolder viewHolder = (BannerViewHolder) holder;
        //设置图片加载器
        viewHolder.banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        viewHolder.banner.setImages(bannerBeanList);
        //banner 设置方法全部调用完毕时最后调用
        viewHolder.banner.start();
    }

    //////////////////// view holder ////////////////////

    static class BannerViewHolder extends RecyclerView.ViewHolder {
        Banner banner;

        public BannerViewHolder(View itemView) {
            super(itemView);
            banner = (Banner) itemView.findViewById(R.id.banner_home);
        }
    }

}
