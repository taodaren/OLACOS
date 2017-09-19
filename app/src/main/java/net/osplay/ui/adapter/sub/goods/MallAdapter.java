package net.osplay.ui.adapter.sub.goods;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

import net.osplay.app.SetOnClickListen;
import net.osplay.olacos.R;
import net.osplay.service.entity.goods.ResultBeanData;
import net.osplay.ui.activity.sub.MallInfoActivity;
import net.osplay.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/9/12.
 */

public class MallAdapter extends RecyclerView.Adapter {

    //五种布局类型
    public static final int BANNER = 0; //轮播
    public static final int CHANNEL = 1; //频道
    public static final int ACT = 2;//活动
    public static final int SECKILL = 3;//秒杀
    public static final int RECOMMEND = 4;//推荐
    public static final int HOT = 5;//热卖
    private Context mContext;
    private ResultBeanData.ResultBean resultBean;
    private LayoutInflater mLayoutInflater;//初始化布局
    //当前类型
    public int currentType = BANNER;

    public MallAdapter(Context mContext, ResultBeanData.ResultBean resultBean) {
        this.mContext = mContext;
        this.resultBean = resultBean;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    //加载不同类型布局
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(mContext, mLayoutInflater.inflate(R.layout.banner_viewpager, null));
        } else if (viewType == CHANNEL) {
            return new ChannelViewHolder(mContext, mLayoutInflater.inflate(R.layout.channel_item, null));
        } else if (viewType == ACT) {
            return new ActViewHolder(mContext, mLayoutInflater.inflate(R.layout.act_item, null));
        }else if(viewType == SECKILL){
            return new SeckillViewHolder(mContext, mLayoutInflater.inflate(R.layout.seckill_item, null));
        }else if(viewType==RECOMMEND){
            return new RecommendViewHolder(mContext, mLayoutInflater.inflate(R.layout.recommend_item, null));
        }else if(viewType==HOT){
            return new HotViewHolder(mContext, mLayoutInflater.inflate(R.layout.hot_item, null));
        }
        return null;
    }

    //绑定holder加载数据
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData(resultBean.getBanner_info());
        } else if (getItemViewType(position) == CHANNEL) {
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            channelViewHolder.setData(resultBean.getChannel_info());
        } else if (getItemViewType(position) == ACT) {
            ActViewHolder actViewHolder = (ActViewHolder) holder;
            actViewHolder.setData(resultBean.getAct_info());
        }else if (getItemViewType(position)== SECKILL){
            SeckillViewHolder seckillViewHolder= (SeckillViewHolder) holder;
            seckillViewHolder.setData(resultBean.getSeckill_info());
        }else if(getItemViewType(position)==RECOMMEND){
            RecommendViewHolder recommendViewHolder= (RecommendViewHolder) holder;
            recommendViewHolder.setData(resultBean.getRecommend_info());
        }else if(getItemViewType(position)==HOT){
            HotViewHolder hotViewHolder= (HotViewHolder) holder;
            hotViewHolder.setData(resultBean.getHot_info());
        }
    }


    class HotViewHolder extends RecyclerView.ViewHolder{
        private Context mContextr;
        private GridView gv_hot;
        private HotGridAdapter hAdapter;
        public HotViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContextr=mContext;
            gv_hot= (GridView) itemView.findViewById(R.id.gv_hot);
        }
        public void setData(List<ResultBeanData.ResultBean.HotInfoBean> hot_info) {
            hAdapter=new HotGridAdapter(mContextr,hot_info);
            gv_hot.setAdapter(hAdapter);
            gv_hot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
                    startMallInfo();
                }
            });
        }
    }

    class RecommendViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        private GridView gv_recommend;
        private RecommendGridAdapter rAdapter;
        public RecommendViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext=mContext;
            gv_recommend= (GridView) itemView.findViewById(R.id.gv_recommend);
        }
        public void setData(List<ResultBeanData.ResultBean.RecommendInfoBean> recommend_info) {
            rAdapter=new RecommendGridAdapter(mContext,recommend_info);
            gv_recommend.setAdapter(rAdapter);
            gv_recommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
                    startMallInfo();
                }
            });
        }
    }

    class SeckillViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        public TextView tv_time_seckill,tv_more_seckill;
        private RecyclerView rv_seckill;
        private SeckillAdapter adapter;
        //相差多少时间 毫秒
        private long dt=0;
        private Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                dt=dt-1000;
                //事件格式化
                SimpleDateFormat formater=new SimpleDateFormat("HH:mm:ss");
                String time = formater.format(new Date(dt));
                tv_time_seckill.setText(time);
                handler.removeMessages(0);
                handler.sendEmptyMessageDelayed(0,1000);
                if(dt<=0){
                    //把所有的消息移除
                    handler.removeCallbacksAndMessages(null);
                }
            }
        };
        public SeckillViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext=mContext;
            tv_time_seckill= (TextView) itemView.findViewById(R.id.tv_time_seckill);
            tv_more_seckill= (TextView) itemView.findViewById(R.id.tv_time_seckill);
            rv_seckill= (RecyclerView) itemView.findViewById(R.id.rv_seckill);
        }
        public void setData(ResultBeanData.ResultBean.SeckillInfoBean seckill_info) {
            //集合数据
            List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> list = seckill_info.getList();
            //设置recyclerview
            adapter=new SeckillAdapter(mContext,list);
            rv_seckill.setAdapter(adapter);
            rv_seckill.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
            //recyclerView的点击事件
            SetOnClickListen setOnClickListen = new SetOnClickListen() {
                @Override
                public void setOnClick(int position) {
                Toast.makeText(mContext,"position"+position,Toast.LENGTH_SHORT).show();
                    startMallInfo();
                }
            };
            adapter.onClick(setOnClickListen);
            //秒杀倒计时的计算
            dt = (int) (Integer.parseInt(seckill_info.getEnd_time()) - (Integer.parseInt(seckill_info.getStart_time())));
            //倒计时
            handler.sendEmptyMessageDelayed(0, 1000);

        }
    }

    class ActViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private ViewPager act_viewpager;

        public ActViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            act_viewpager = (ViewPager) itemView.findViewById(R.id.act_viewpager);
        }

        public void setData(final List<ResultBeanData.ResultBean.ActInfoBean> act_info) {
            act_viewpager.setPageMargin(50);
            act_viewpager.setOffscreenPageLimit(3);//>=3
            //setPageTransformer 决定动画效果
            act_viewpager.setPageTransformer(true, new ScaleInTransformer());
            //设置适配器
            act_viewpager.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return act_info.size();
                }

                //view=页面 object=item的值
                @Override
                public boolean isViewFromObject(View view, Object object) {
                    return view == object;
                }

                //container==viewpager  position==对应界面的位置
                @Override
                public Object instantiateItem(ViewGroup container, final int position) {
                    ImageView imageView = new ImageView(mContext);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    Glide.with(mContext).load(Constants.BASE_URl_IMAGE + act_info.get(position).getIcon_url()).into(imageView);
                    //添加到容器中
                    container.addView(imageView);
                    //设置点击事件
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
                        }
                    });
                    return imageView;
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    container.removeView((View) object);
                }
            });
        }
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private GridView gv_channel;
        private ChannelAdapter adapter;

        public ChannelViewHolder(final Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            gv_channel = (GridView) itemView.findViewById(R.id.gv_channel);
            //设置item的点击事件
            gv_channel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView parent, View view, int position, long id) {
                    Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void setData(List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
            //得到数据设置gv
            adapter = new ChannelAdapter(mContext, channel_info);
            gv_channel.setAdapter(adapter);
        }
    }

    //创建bannerholder
    class BannerViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private Banner banner;
        private List<ResultBeanData.ResultBean.BannerInfoBean> data;

        public BannerViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            this.banner = (Banner) itemView.findViewById(R.id.banner);
        }


        public void setData(List<ResultBeanData.ResultBean.BannerInfoBean> banner_info) {
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            List<String> imageUris = new ArrayList<>();
            for (int i = 0; i < resultBean.getBanner_info().size(); i++) {
                imageUris.add(resultBean.getBanner_info().get(i).getImage());
            }
            banner.setBannerAnimation(Transformer.Accordion);
            banner.setImages(imageUris);
            banner.setImageLoader(new GlideImageLoaders());
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
                    startMallInfo();
                }
            });
            banner.start();
        }
    }

    private void startMallInfo() {
        Intent intent=new Intent(mContext, MallInfoActivity.class);
        mContext.startActivity(intent);
    }

    //得到不同的条目类型
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

    //加载条目数量
    @Override
    public int getItemCount() {
        //开发过程中从1--》6
        return 6;
    }
}

//轮播的图片加载
class GlideImageLoaders extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context)
                .load(Constants.BASE_URl_IMAGE + path)
                .into(imageView);
    }

}

