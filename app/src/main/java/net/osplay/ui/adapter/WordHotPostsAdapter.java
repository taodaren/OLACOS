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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import net.osplay.app.MyApplication;
import net.osplay.olacos.R;
import net.osplay.service.entity.VideoBean;
import net.osplay.ui.activity.sub.DetailsPostsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 社区 → 热区 → 热帖适配器
 */

public class WordHotPostsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity mContext;
    private LayoutInflater mInflater;

    private List<VideoBean> mHotPostsList;

    public WordHotPostsAdapter(Activity context, List<VideoBean> data) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);

        this.mHotPostsList = new ArrayList<>();
        this.mHotPostsList.addAll(data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PostsViewHolder holder = new PostsViewHolder(mInflater.inflate(R.layout.layout_word_hot_posts, parent, false));
        holder.setClickListener();
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((PostsViewHolder) holder).bindData(mHotPostsList.get(position), position, mHotPostsList);
    }

    @Override
    public int getItemCount() {
        return mHotPostsList == null ? 0 : mHotPostsList.size();
    }

    private class PostsViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView recyclerPosts;
        private List<VideoBean> postsList;
        private PostsInfoAdapter adapter;

        private LinearLayout layout;
        //        private Button btnClick, btnMore;
        private ImageView imgHead, imgRefresh;
        private TextView tvHead, tvNumber;

        private PostsViewHolder(View inflate) {
            super(inflate);
            layout = (LinearLayout) inflate.findViewById(R.id.layout_hot_posts_title);
            recyclerPosts = (RecyclerView) inflate.findViewById(R.id.recycler_hot_posts);
//            btnClick = (Button) inflate.findViewById(R.id.btn_hot_posts_click);
//            btnMore = (Button) inflate.findViewById(R.id.btn_hot_posts_more);
            imgRefresh = (ImageView) inflate.findViewById(R.id.img_hot_posts_refresh);
            imgHead = (ImageView) inflate.findViewById(R.id.img_hot_posts_head);
            tvHead = (TextView) inflate.findViewById(R.id.tv_hot_posts_head);
            tvNumber = (TextView) inflate.findViewById(R.id.tv_posts_refresh_num);
        }

        public void bindData(VideoBean videoBean, int position, List<VideoBean> beanList) {
            //网络接口数据
//            Glide.with(MyApplication.getContext()).load(videoBean.getCoverImg()).into(imgHead);

            //模拟数据
            List<String> strTypes = new ArrayList<>();
            strTypes.add("COS专区");
            strTypes.add("服装区");
            strTypes.add("模玩区");
            strTypes.add("游戏区");
            strTypes.add("动漫区");
            strTypes.add("影视区");
            strTypes.add("同人区");
            strTypes.add("周边区");

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
            tvHead.setText(strTypes.get(position));
            tvNumber.setText(videoBean.getVideoLength() + "条新动态，点击刷新！");

            if (beanList != null && !beanList.isEmpty()) {
                this.postsList = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    postsList.add(beanList.get(i));
                }
//                this.postsList.addAll(beanList);

                GridLayoutManager layoutManager = new GridLayoutManager(MyApplication.getContext(), 2, LinearLayoutManager.VERTICAL, false);
                recyclerPosts.setLayoutManager(layoutManager);

                adapter = new PostsInfoAdapter(mContext, postsList);
                recyclerPosts.setAdapter(adapter);
            }
        }

        private void setClickListener() {
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "跳转到未知界面，开发中...", Toast.LENGTH_SHORT).show();
                }
            });

            imgRefresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "刷新数据，动态数亦随之变化", Toast.LENGTH_SHORT).show();
                }
            });

//            btnMore.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(mContext, "跳转到更多界面，开发中...", Toast.LENGTH_SHORT).show();
//                }
//            });
//            btnClick.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(mContext, "跳转到未知界面，开发中...", Toast.LENGTH_SHORT).show();
//                }
//            });
        }

        private class PostsInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
            private Activity context;
            private LayoutInflater inflater;
            private List<VideoBean> datas;

            private PostsInfoAdapter(Activity context, List<VideoBean> list) {
                this.context = context;
                this.inflater = LayoutInflater.from(this.context);
                this.datas = new ArrayList<>();
                this.datas.addAll(list);
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                PostsInfoHolder holder = new PostsInfoHolder(inflater.inflate(R.layout.item_word_hot_posts, parent, false));
                holder.setClickListener();
                return holder;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ((PostsInfoHolder) holder).bindData(datas.get(position));
            }

            @Override
            public int getItemCount() {
                return datas == null ? 0 : datas.size();
            }

            private class PostsInfoHolder extends RecyclerView.ViewHolder {
                private View outView;
                private ImageView imgShow;
                private TextView tvInfo, tvType, tvNick, tvComment;

                private PostsInfoHolder(View view) {
                    super(view);
                    outView = view;
                    imgShow = (ImageView) view.findViewById(R.id.img_hot_posts_bg);
                    tvInfo = (TextView) view.findViewById(R.id.tv_hot_posts_info);
                    tvType = (TextView) view.findViewById(R.id.tv_hot_posts_type);
//                    tvNick = (TextView) view.findViewById(R.id.tv_hot_posts_nick);
                    tvComment = (TextView) view.findViewById(R.id.tv_hot_posts_comment);
                }

                public void setClickListener() {
                    outView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            context.startActivity(new Intent(context, DetailsPostsActivity.class));
                        }
                    });
                }

                public void bindData(VideoBean videoBean) {
                    Glide.with(MyApplication.getContext()).load(videoBean.getCoverImg()).into(imgShow);
                    tvInfo.setText(videoBean.getSummary() + videoBean.getSummary());
//                    tvComment.setText(videoBean.getVideoLength());
                }
            }
        }
    }

}
