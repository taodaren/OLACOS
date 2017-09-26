package net.osplay.ui.activity.sub;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hankkin.library.GradationScrollView;
import com.hankkin.library.MyImageLoader;
import com.hankkin.library.NoScrollListView;


import com.hankkin.library.ScrollViewContainer;
import com.hankkin.library.StatusBarUtil;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

import net.osplay.olacos.R;


import net.osplay.service.entity.goods.GoodsBean;
import net.osplay.ui.adapter.sub.goods.MallAdapter;
import net.osplay.utils.Constants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;



public class MallInfoActivity extends AppCompatActivity implements GradationScrollView.ScrollViewListener{
    @BindView(R.id.scrollview)
    GradationScrollView scrollView;
    @BindView(R.id.iv_good_detai_img)
    ImageView iv;
    @BindView(R.id.ll_good_detail)
    RelativeLayout llTitle;
    @BindView(R.id.ll_offset)
    LinearLayout llOffset;
    @BindView(R.id.iv_good_detai_collect_select)
    ImageView ivCollectSelect;//收藏选中
    @BindView(R.id.iv_good_detai_collect_unselect)
    ImageView ivCollectUnSelect;//收藏未选中
    @BindView(R.id.sv_container)
    ScrollViewContainer container;
    @BindView(R.id.tv_good_detail_title_good)
    TextView tvGoodTitle;
    @BindView(R.id.nlv_good_detial_imgs)
    NoScrollListView nlvImgs;//图片详情
    private QuickAdapter<String> imgAdapter;
    private List<String> imgsUrl;

    private int height;
    private int width;

    private GoodsBean goodsBean;
    private TextView mall_name,mall_price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall_info);
        ButterKnife.bind(this);
        //透明状态栏
        StatusBarUtil.setTranslucentForImageView(this,llOffset);
        LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) llOffset.getLayoutParams();
        params1.setMargins(0,-StatusBarUtil.getStatusBarHeight(this)/4,0,0);
        llOffset.setLayoutParams(params1);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) iv.getLayoutParams();
        //图片显示的高度
        params.height = getScreenHeight(this)*2/4;
        iv.setLayoutParams(params);
        container = new ScrollViewContainer(getApplicationContext());
        initImgDatas();
        initListeners();


        mall_name= (TextView) findViewById(R.id.mall_name);
        mall_price= (TextView) findViewById(R.id.mall_price);
        //得到商品详情对象 接收商品详情数据 goodsBean为详情的实体类
        goodsBean= (GoodsBean) getIntent().getSerializableExtra("goodsBean");
        if(goodsBean!=null){
            setDataForView(goodsBean);
        }
    }

    //设置数据
    private void setDataForView(GoodsBean goodsBean) {
        //设置商品图片
        Glide.with(this).load(Constants.BASE_URl_IMAGE+goodsBean.getFigure()).into(iv);
        mall_name.setText(goodsBean.getName());
        mall_price.setText(goodsBean.getCover_price());
    }

    public  int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }
    // 商品图文详情的展示 直接传入网址就可以
    private void initImgDatas(){
        width = getScreenWidth(getApplicationContext());
        imgsUrl = new ArrayList<>();
        imgsUrl.add("https://img.alicdn.com/imgextra/i4/1024013322/TB2GMZad.cKL1JjSZFzXXcfJXXa_!!1024013322.jpg");
        imgsUrl.add("https://img.alicdn.com/imgextra/i1/1024013322/TB2laHPd43IL1JjSZPfXXcrUVXa_!!1024013322.jpg");
        imgAdapter = new QuickAdapter<String>(this,R.layout.adapter_good_detail_imgs) {
            @Override
            protected void convert(BaseAdapterHelper helper, String item) {
                ImageView iv = helper.getView(R.id.iv_adapter_good_detail_img);
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) iv.getLayoutParams();
                params.width = width;
                params.height = width*2;
                iv.setLayoutParams(params);
                MyImageLoader.getInstance().displayImageCen(getApplicationContext(),item,iv,width,width*2);
            }
        };
        imgAdapter.addAll(imgsUrl);
        nlvImgs.setAdapter(imgAdapter);
    }

    private void initListeners() {
        ViewTreeObserver vto = iv.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                llTitle.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                height = iv.getHeight();

                scrollView.setScrollViewListener(MallInfoActivity.this);
            }
        });
    }
    //滑动监听
    @Override
    public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy) {
        // TODO Auto-generated method stub
        if (y <= 0) {   //设置标题的背景颜色
            llTitle.setBackgroundColor(Color.argb((int) 0, 255,255,255));
        } else if (y > 0 && y <= height) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) y / height;
            float alpha = (255 * scale);
            tvGoodTitle.setTextColor(Color.argb((int) alpha, 1,24,28));
            llTitle.setBackgroundColor(Color.argb((int) alpha, 255,255,255));
        } else {    //滑动到banner下面设置普通颜色
            llTitle.setBackgroundColor(Color.argb((int) 255, 255,255,255));
        }
    }
}
