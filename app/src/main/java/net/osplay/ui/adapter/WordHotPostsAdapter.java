package net.osplay.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.osplay.app.I;
import net.osplay.app.MyApplication;
import net.osplay.olacos.R;
import net.osplay.service.entity.WordHotPostsBean;
import net.osplay.ui.activity.sub.DetailsPostsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 社区 → 热区 → 热帖适配器
 */

public class WordHotPostsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity mContext;
    private LayoutInflater mInflater;

    private List<WordHotPostsBean.PartBean> mPartList;//热帖列表所有大区的信息
    private List<WordHotPostsBean.DataBean> mDataList;//热帖列表各个大区的数据

    private HotPostingsOnClickListener listener;

    public void setListener(HotPostingsOnClickListener listener) {
        this.listener = listener;
    }

    public interface HotPostingsOnClickListener {
        void subareaOnClickRefresh(int parentPosition);
    }

    public WordHotPostsAdapter(Activity context, List<WordHotPostsBean.PartBean> partList, List<WordHotPostsBean.DataBean> dataList) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mPartList = new ArrayList<>();
        this.mDataList = new ArrayList<>();
        this.mPartList.addAll(partList);
        this.mDataList.addAll(dataList);
    }

    /**
     * 刷新指定热帖分区的方法
     *
     * @param mPartList    刷新分区的数据
     * @param mDataList    刷新分区内容的数据
     * @param partPosition 点击分区的位置
     */
    public void setSubareaData(List<WordHotPostsBean.PartBean> mPartList, List<WordHotPostsBean.DataBean> mDataList, int partPosition) {
        // update part data
        int partIndex = partPosition;
        this.mPartList.set(partIndex, mPartList.get(0));
        // update subareaData
        int subareaIndex = partPosition * 4;
        for (int i = subareaIndex, j = 0; j < mDataList.size(); i++, j++) {
            this.mDataList.set(i, mDataList.get(j));
        }
        // notify refresh data
        notifyItemRangeChanged(partPosition, 1);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PostsViewHolder holder = new PostsViewHolder(mInflater.inflate(R.layout.layout_word_hot_posts, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((PostsViewHolder) holder).bindData(position);
    }

    @Override
    public int getItemCount() {
        return mPartList == null && mDataList == null ? 0 : mPartList.size();
    }

    private class PostsViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView recyclerPosts;
        private PostsInfoAdapter adapter;
        private ImageView imgHead, imgRefresh;
        private TextView tvHead, tvNumber;

        private PostsViewHolder(View inflate) {
            super(inflate);
            recyclerPosts = (RecyclerView) inflate.findViewById(R.id.recycler_hot_posts);
            imgRefresh = (ImageView) inflate.findViewById(R.id.img_hot_posts_refresh);
            imgHead = (ImageView) inflate.findViewById(R.id.img_hot_posts_head);
            tvHead = (TextView) inflate.findViewById(R.id.tv_hot_posts_head);
            tvNumber = (TextView) inflate.findViewById(R.id.tv_posts_refresh_num);
        }

        public void bindData(final int position) {
            //模拟数据
            List<Integer> niTypes = new ArrayList<>();
            niTypes.add(R.drawable.hot_posts_cos);
            niTypes.add(R.drawable.hot_posts_clothing);
            niTypes.add(R.drawable.hot_posts_play);
            niTypes.add(R.drawable.hot_posts_game);
            niTypes.add(R.drawable.hot_posts_anime);
            niTypes.add(R.drawable.hot_posts_media);
            niTypes.add(R.drawable.hot_posts_people);
            niTypes.add(R.drawable.hot_posts_periphery);

            imgHead.setImageResource(niTypes.get(position));
            tvHead.setText(mPartList.get(position).getPART());
//            tvNumber.setText(mPartList.get(position).getTOPICK_COUNT() + "条新动态，点击刷新！");

            imgRefresh.setOnClickListener(new View.OnClickListener() {//刷新数据，动态数亦随之变化
                @Override
                public void onClick(View v) {
                    listener.subareaOnClickRefresh(position);
                }
            });

            GridLayoutManager layoutManager = new GridLayoutManager(MyApplication.getContext(), 2, LinearLayoutManager.VERTICAL, false);
            recyclerPosts.setLayoutManager(layoutManager);
            setAdapter(position);

        }

        private void setAdapter(int position) {
            List<WordHotPostsBean.DataBean> list = null;
            int startIndex = position * 4;
            list = new ArrayList<>(mDataList.subList(startIndex, startIndex + 4));
            adapter = new PostsInfoAdapter(mContext, list);
            recyclerPosts.setAdapter(adapter);
        }

        private class PostsInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
            private Activity context;
            private LayoutInflater inflater;
            private List<WordHotPostsBean.DataBean> dataList;

            private PostsInfoAdapter(Activity context, List<WordHotPostsBean.DataBean> dataList) {
                this.context = context;
                this.inflater = LayoutInflater.from(this.context);
                this.dataList = new ArrayList<>();
                this.dataList.addAll(dataList);
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                PostsInfoHolder holder = new PostsInfoHolder(inflater.inflate(R.layout.item_word_hot_posts, parent, false));
                holder.setClickListener();
                return holder;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ((PostsInfoHolder) holder).bindData(dataList.get(position));
            }

            @Override
            public int getItemCount() {
                return dataList == null ? 0 : 4;
            }

            private class PostsInfoHolder extends RecyclerView.ViewHolder {
                private View outView;
                private ImageView imgShow;
                private TextView tvInfo, tvType, tvComment;
                private String postsId;

                private PostsInfoHolder(View view) {
                    super(view);
                    outView = view;
                    imgShow = (ImageView) view.findViewById(R.id.img_hot_posts_list_bg);
                    tvInfo = (TextView) view.findViewById(R.id.tv_hot_posts_info_list);
                    tvType = (TextView) view.findViewById(R.id.tv_hot_posts_type_list);
                    tvComment = (TextView) view.findViewById(R.id.tv_hot_posts_comment_list);
                }

                public void setClickListener() {
                    outView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, DetailsPostsActivity.class);
                            intent.putExtra("postsId", postsId);//携带帖子ID
                            context.startActivity(intent);
                        }
                    });
                }

                public void bindData(WordHotPostsBean.DataBean dataBean) {
                    Glide.with(MyApplication.getContext()).load(I.BASE_URL + dataBean.getCOVERIMG()).into(imgShow);
                    tvInfo.setText(dataBean.getTITLE());
                    tvType.setText(dataBean.getPARTNAME());
                    tvComment.setText(dataBean.getPINGLUN_COUNT());
                    postsId = dataBean.getID();
                }
            }
        }
    }

}
