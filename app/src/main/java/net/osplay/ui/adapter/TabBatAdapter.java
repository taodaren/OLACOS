package net.osplay.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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
                return new TopicViewHolder(mInflater.inflate(R.layout.layout_recycler_view, parent, false));
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
                ((TopicViewHolder) holder).bindData((List<WordHotPostsBean.DataBean>) mDataList.get(position).getData());
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
        private Banner banner;

        private BannerViewHolder(View itemView) {
            super(itemView);
            banner = (Banner) itemView.findViewById(R.id.banner_home);
        }

        private void bindData(List<ImgTvBean> data) {
            if (data != null && !data.isEmpty()) {
                List<Integer> images = new ArrayList<>();
                for (ImgTvBean bean : data) {
                    images.add(bean.getImgPicture());
                }
                bindBanner(images);
            }
        }

        private void bindBanner(List<Integer> images) {
//            banner.setBannerTitles(titles);//设置标题集合（当 banner 样式有显示 title 时）
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);//设置 banner 样式
            banner.setImageLoader(new GlideImageLoader());//设置图片加载器
            banner.setImages(images);//设置图片集合
            banner.setBannerAnimation(Transformer.DepthPage);//设置 banner 动画效果
            banner.isAutoPlay(true);//设置自动轮播，默认为 true
            banner.setDelayTime(3000);//设置轮播时间
            banner.setIndicatorGravity(BannerConfig.CENTER);//设置指示器位置（当 banner 模式中有指示器时）
            banner.start();//banner 设置方法全部调用完毕时最后调用
        }
    }

    private class TopicViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView rvItem;
        private List<WordHotPostsBean.DataBean> postsList;
        private GridLayoutManager manager;
        private TopicItemAdapter adapter;

        public TopicViewHolder(View itemView) {
            super(itemView);
            rvItem = (RecyclerView) itemView.findViewById(R.id.rv_layout_public);
        }

        public void bindData(List<WordHotPostsBean.DataBean> data) {
            if (data != null && !data.isEmpty()) {
                this.postsList = new ArrayList<>();
                this.postsList.addAll(data);
                bindItem();
            }
        }

        private void bindItem() {
//            manager = new LinearLayoutManager(MyApplication.getContext(), LinearLayoutManager.VERTICAL, false);
            manager = new GridLayoutManager(MyApplication.getContext(), 2, LinearLayoutManager.VERTICAL, false);
            rvItem.setLayoutManager(manager);
            adapter = new TopicItemAdapter(MyApplication.getContext(), postsList);
            rvItem.setAdapter(adapter);
        }

        private class TopicItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
            private Context context;
            private LayoutInflater inflater;
            private List<WordHotPostsBean.DataBean> datas;

            public TopicItemAdapter(Context context, List<WordHotPostsBean.DataBean> list) {
                this.context = context;
                this.inflater = LayoutInflater.from(MyApplication.getContext());
                this.datas = new ArrayList<>();
                this.datas.addAll(list);
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View inflate = inflater.inflate(R.layout.item_word_hot_posts, parent, false);
                TopicItemHolder holder = new TopicItemHolder(inflate);
                holder.setClickListener();
                return holder;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ((TopicItemHolder) holder).bindData(datas.get(position));
            }

            @Override
            public int getItemCount() {
                return datas == null ? 0 : datas.size();
            }

            private class TopicItemHolder extends RecyclerView.ViewHolder {
                private View outView;
                private ImageView imgBg;
                private TextView tvInfo, tvType, tvComment;
                private String postsId;

                public TopicItemHolder(View itemView) {
                    super(itemView);
                    outView = itemView;
                    imgBg = (ImageView) itemView.findViewById(R.id.img_hot_posts_list_bg);
                    tvInfo = (TextView) itemView.findViewById(R.id.tv_hot_posts_info_list);
                    tvType = (TextView) itemView.findViewById(R.id.tv_hot_posts_type_list);
                    tvComment = (TextView) itemView.findViewById(R.id.tv_hot_posts_comment_list);
                }

                public void setClickListener() {
                    outView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, DetailsPostsActivity.class);
                            intent.putExtra("postsId", postsId);//携带帖子ID
                            intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }
                    });
                }

                public void bindData(WordHotPostsBean.DataBean dataBean) {
                    Glide.with(MyApplication.getContext()).load(I.BASE_URL + dataBean.getCOVERIMG()).into(imgBg);
                    tvInfo.setText(dataBean.getTITLE());
                    tvType.setText(dataBean.getPARTNAME());
                    tvComment.setText(dataBean.getPINGLUN_COUNT());
                    postsId = dataBean.getID();
                }
            }
        }
    }

}
