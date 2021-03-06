package net.osplay.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import net.osplay.app.I;
import net.osplay.app.MyApplication;
import net.osplay.olacos.R;
import net.osplay.service.entity.ImgTvBean;
import net.osplay.service.entity.WordHotPostsBean;
import net.osplay.service.entity.base.HomeData;
import net.osplay.ui.activity.sub.DetailsPostsActivity;
import net.osplay.utils.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * 首页适配器
 */

public class TabBatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "TabHomeAdapter";
    public static final int TYPE_BANNER = 1;
    public static final int TYPE_TOPIC = 2;

    private Activity mContext;
    private LayoutInflater mInflater;
    private List<HomeData> mDataList;

    public TabBatAdapter(Activity context, List<HomeData> data) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDataList = new ArrayList<>();
        this.mDataList.addAll(data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_BANNER:
                return new BannerViewHolder(mInflater.inflate(R.layout.layout_home_banner, parent, false));
            case TYPE_TOPIC:
                return new TopicViewHolder(mInflater.inflate(R.layout.item_home_posts, parent, false));
            default:
                Log.e(TAG, "onCreateViewHolder: is null");
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (mDataList.get(position).getItemType()) {
            case TYPE_BANNER:
                //模拟数据
                ((BannerViewHolder) holder).bindData((List<ImgTvBean>) mDataList.get(position).getData());
                break;
            case TYPE_TOPIC:
                ((TopicViewHolder) holder).bindData((WordHotPostsBean.DataBean) mDataList.get(position).getData());
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

    @Override
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (isFullSpanType(position)) {
                        return gridLayoutManager.getSpanCount();
                    }
                    return 1;
                }
            });
        }
    }

    private boolean isFullSpanType(int pos) {
        return mDataList.get(pos).isSpan();
    }

    //////////////////// view holder ////////////////////

    private static class BannerViewHolder extends RecyclerView.ViewHolder {
        private Banner banner;

        private BannerViewHolder(View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner_home);
        }

        private void bindData(List<ImgTvBean> data) {
            if (data != null && !data.isEmpty()) {
                List<Integer> images = new ArrayList<>();
                List<String> postsId = new ArrayList<>();
                for (ImgTvBean bean : data) {
                    images.add(bean.getImgPicture());
                    postsId.add(bean.getTvText());
                }
                bindBanner(images, postsId);
            }
        }

        private void bindBanner(final List<Integer> images, final List<String> postsId) {
//            banner.setBannerTitles(titles);//设置标题集合（当 banner 样式有显示 title 时）
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);//设置 banner 样式
            banner.setImageLoader(new GlideImageLoader());//设置图片加载器
            banner.setImages(images);//设置图片集合
            banner.setBannerAnimation(Transformer.DepthPage);//设置 banner 动画效果
            banner.isAutoPlay(true);//设置自动轮播，默认为 true
            banner.setDelayTime(3000);//设置轮播时间
            banner.setIndicatorGravity(BannerConfig.CENTER);//设置指示器位置（当 banner 模式中有指示器时）
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Log.i(TAG, "OnBannerClick: postsId==" + postsId);
                    Intent intent = new Intent(banner.getContext(), DetailsPostsActivity.class);
                    intent.putExtra("postsId", postsId.get(position));//携带帖子ID
                    intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                    banner.getContext().startActivity(intent);
                }
            });
            banner.start();//banner 设置方法全部调用完毕时最后调用
        }
    }

    private class TopicViewHolder extends RecyclerView.ViewHolder {
        private WordHotPostsBean.DataBean dataBean;
        private View outView;
        private ImageView imgBg;
        private TextView tvInfo, tvType, tvComment;
        private String postsId;

        private TopicViewHolder(View itemView) {
            super(itemView);
            outView = itemView;
            imgBg = itemView.findViewById(R.id.img_hot_posts_list_bg);
            tvInfo = itemView.findViewById(R.id.tv_hot_posts_info_list);
            tvType = itemView.findViewById(R.id.tv_hot_posts_type_list);
            tvComment = itemView.findViewById(R.id.tv_hot_posts_comment_list);
        }

        public void bindData(WordHotPostsBean.DataBean data) {
            if (data != null) {
                this.dataBean = data;
                bindItem();
            }
        }

        private void bindItem() {
            Glide.with(MyApplication.getContext()).load(I.BASE_URL + dataBean.getCOVERIMG()).into(imgBg);
            tvInfo.setText(dataBean.getTITLE());
            tvType.setText(dataBean.getPARTNAME());
            tvComment.setText(dataBean.getPINGLUN_COUNT());
            postsId = dataBean.getID();

            outView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, DetailsPostsActivity.class);
                    intent.putExtra("postsId", postsId);//携带帖子ID
                    intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }
            });

        }

    }

}
