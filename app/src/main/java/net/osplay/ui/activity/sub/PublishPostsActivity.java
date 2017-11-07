package net.osplay.ui.activity.sub;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.yanzhenjie.nohttp.FileBinary;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import net.osplay.app.I;
import net.osplay.app.SetOnClickListen;
import net.osplay.olacos.R;
import net.osplay.service.entity.PhotoBean;
import net.osplay.service.entity.WordTopicBean;
import net.osplay.service.entity.WordTopicTitleBean;
import net.osplay.ui.activity.base.BaseActivity;
import net.osplay.ui.adapter.PublishAreaAdapter;
import net.osplay.ui.adapter.PublishPostsAdapter;
import net.osplay.ui.adapter.PublishTwoAreaAdapter;

import java.io.File;
import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PublishPostsActivity extends BaseActivity {

    @BindView(R.id.text_city)
    TextView textCity;
    @BindView(R.id.layout_city)
    RelativeLayout layoutCity;
    @BindView(R.id.title_toolbar)
    TextView titleToolbar;
    @BindView(R.id.tab_layout_toolbar)
    TabLayout tabLayoutToolbar;
    @BindView(R.id.main_toolbar)
    Toolbar mainToolbar;
    @BindView(R.id.posts_title_tv)
    EditText postsTitleTv;
    @BindView(R.id.posts_content_tv)
    EditText postsContentTv;
    @BindView(R.id.posts_content_iv)
    RecyclerView postsContentIv;
    @BindView(R.id.posts_click_iv)
    ImageView postsClickIv;
    @BindView(R.id.posts_area_recy)
    RecyclerView postsAreaRecy;
    @BindView(R.id.posts_original_rb)
    RadioButton postsOriginalRb;
    @BindView(R.id.posts_reprint_rb)
    RadioButton postsReprintRb;
    @BindView(R.id.posts_tow_area_recy)
    RecyclerView postsTowAreaRecy;
    @BindView(R.id.posts_area_tv)
    TextView postsAreaTv;
    @BindView(R.id.posts_two_area_tv)
    TextView postsTwoAreaTv;
    private List<LocalMedia> localMedia;//图片集合
    private PublishPostsAdapter adapter;
    private PublishAreaAdapter paAdapter;
    private PublishTwoAreaAdapter ptAdapter;
    private int bs = 0;
    private int size;
    private Gson mGson = new Gson();
    private List<WordTopicBean> mTopicList;
    private String url;
    private String id;//选中大区的id
    private String id2;//选中分区的id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_posts);
        ButterKnife.bind(this);
        postsContentIv.setLayoutManager(new GridLayoutManager(PublishPostsActivity.this, 4));
        postsAreaRecy.setLayoutManager(new GridLayoutManager(PublishPostsActivity.this, 4));
        postsTowAreaRecy.setLayoutManager(new GridLayoutManager(PublishPostsActivity.this, 4));
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);//滑动样式
//        postsAreaRecy.setLayoutManager(linearLayoutManager);
        setToolbar("发布帖子", View.VISIBLE);
        getArea();//获取一级专区的数据

    }

    //点击事件
    @OnClick({R.id.posts_click_iv, R.id.posts_original_rb, R.id.posts_reprint_rb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.posts_click_iv:
                if (bs == 0) {
                    PictureSelector.create(PublishPostsActivity.this)
                            .openGallery(PictureMimeType.ofAll())
                            .maxSelectNum(9)
                            .compress(true)
                            .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
                    bs = 1;
                } else {
                    PictureSelector.create(PublishPostsActivity.this)
                            .openGallery(PictureMimeType.ofAll())
                            .maxSelectNum(9)
                            .compress(true)
                            .forResult(PictureConfig.REQUEST_CAMERA);//结果回调onActivityResult code
                }


                break;
            case R.id.posts_original_rb:
                break;
            case R.id.posts_reprint_rb:
                break;
        }
    }

    //选择图片的回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    localMedia = PictureSelector.obtainMultipleResult(data);
                    size = localMedia.size();
                    adapter = new PublishPostsAdapter(PublishPostsActivity.this, localMedia);
                    postsContentIv.setAdapter(adapter);
                    Log.e("JGB", "第一次图片集合长度：" + size);
                    SetOnClickListen Image = new SetOnClickListen() {
                        @Override
                        public void setOnClick(int position) {
                            localMedia.remove(position);
                            adapter.notifyItemRemoved(position);
                            adapter.notifyDataSetChanged();
                            size = localMedia.size();
                            Log.e("JGB", "删除图片集合长度：：" + size);
                        }

                        @Override
                        public void setOnClick(int position, TextView zanTv, TextView collecTv, TextView commentTv, ImageView zanIv, ImageView cllecIv) {

                        }
                    };
                    adapter.onClick(Image);
                    break;
                case PictureConfig.REQUEST_CAMERA:
                    List<LocalMedia> localMedias = PictureSelector.obtainMultipleResult(data);
                    for (int i = 0; i < localMedias.size(); i++) {
                        localMedia.add(new LocalMedia(localMedias.get(i).getPath(), 0, 0, ""));
                        adapter.notifyItemInserted(1);
                        adapter.notifyItemRangeChanged(2, localMedia.size() - 2);
                        size = localMedia.size();
                    }
            }
        }
    }

    //获取一级专区
    public void getArea() {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest(I.AREA, RequestMethod.POST);
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.e("JGB", "全部专区:" + json);
                if (json == null) {
                    return;
                } else {
                    //数据解析
                    Type type = new TypeToken<List<WordTopicBean>>() {
                    }.getType();
                    mTopicList = mGson.fromJson(json, type);
                    Log.e("JGB", "专区集合：" + mTopicList);
                    paAdapter = new PublishAreaAdapter(PublishPostsActivity.this, mTopicList);
                    postsAreaRecy.setAdapter(paAdapter);
                    adapterAreaclick(mTopicList);

                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }

            @Override
            public void onFinish(int what) {

            }
        });
    }

    //一级专区的点击事件
    private void adapterAreaclick(final List<WordTopicBean> mTopicList) {
        SetOnClickListen area = new SetOnClickListen() {
            @Override
            public void setOnClick(int position) {
                postsAreaTv.setText(mTopicList.get(position).getPART());
                getTwoArea(position);
                id = mTopicList.get(position).getID();
            }

            @Override
            public void setOnClick(int position, TextView zanTv, TextView collecTv, TextView commentTv, ImageView zanIv, ImageView cllecIv) {

            }
        };
        paAdapter.onClick(area);

    }

    //获取二级专区
    public void getTwoArea(int position) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest(I.AREA_SUB, RequestMethod.POST);
        request.add("partId", position + 1);
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.e("JGB", "二级专区数据:" + json);
                if (json == null) {
                    return;
                } else {
                    Type type = new TypeToken<List<WordTopicTitleBean>>() {
                    }.getType();
                    List<WordTopicTitleBean> titleBeanList = mGson.fromJson(json, type);
                    ptAdapter = new PublishTwoAreaAdapter(PublishPostsActivity.this, titleBeanList);
                    postsTowAreaRecy.setAdapter(ptAdapter);
                    adapterTwoArea(titleBeanList);
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }

            @Override
            public void onFinish(int what) {

            }
        });
    }

    //二级专区的点击事件
    private void adapterTwoArea(final List<WordTopicTitleBean> titleBeanList) {
        SetOnClickListen twoArea = new SetOnClickListen() {
            @Override
            public void setOnClick(int position) {
                postsTwoAreaTv.setText(titleBeanList.get(position).getPART());
                id2 = titleBeanList.get(position).getID();
            }

            @Override
            public void setOnClick(int position, TextView zanTv, TextView collecTv, TextView commentTv, ImageView zanIv, ImageView cllecIv) {

            }
        };
        ptAdapter.onClick(twoArea);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    public void click(View view) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://www.olacos.net/upload/uploadFile.do", RequestMethod.POST);
        request.add("url", new FileBinary(new File(localMedia.get(0).getPath())));//上传文件
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                if (json == null) {
                    return;
                } else {
                    Log.e("JGB", "图片1上传结果" + json);
                    PhotoBean photoBean = mGson.fromJson(json, PhotoBean.class);
                    url = photoBean.getFilelist().get(0).getURL();
                    Log.e("JGB","图片上传结果："+url);
                    StringBuffer sb2 = new StringBuffer();
                    StringBuffer imageurl= sb2.append("<p><img src=\"").append("http://www.olacos.net").append(url).append("\"/></p>");

                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {
            }

            @Override
            public void onFinish(int what) {

            }
        });
    }


}
