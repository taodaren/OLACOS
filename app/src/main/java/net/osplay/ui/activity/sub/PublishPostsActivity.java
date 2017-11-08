package net.osplay.ui.activity.sub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.wang.avi.AVLoadingIndicatorView;
import com.yanzhenjie.nohttp.FileBinary;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import net.osplay.app.AppHelper;
import net.osplay.app.I;
import net.osplay.app.SetOnClickListen;
import net.osplay.olacos.R;
import net.osplay.service.entity.PhotoBean;
import net.osplay.service.entity.PostBean;
import net.osplay.service.entity.WordTopicBean;
import net.osplay.service.entity.WordTopicTitleBean;
import net.osplay.ui.adapter.PublishAreaAdapter;
import net.osplay.ui.adapter.PublishPostsAdapter;
import net.osplay.ui.adapter.PublishTwoAreaAdapter;

import java.io.File;
import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ch.ielse.view.SwitchView;

public class PublishPostsActivity extends Activity {


    @BindView(R.id.league_toolbar)
    Toolbar leagueToolbar;
    @BindView(R.id.posts_title_tv)
    EditText postsTitleTv;
    @BindView(R.id.posts_content_tv)
    EditText postsContentTv;
    @BindView(R.id.posts_click_iv)
    ImageView postsClickIv;
    @BindView(R.id.posts_content_iv)
    RecyclerView postsContentIv;
    @BindView(R.id.posts_area_tv)
    TextView postsAreaTv;
    @BindView(R.id.posts_area_recy)
    RecyclerView postsAreaRecy;
    @BindView(R.id.posts_two_area_tv)
    TextView postsTwoAreaTv;
    @BindView(R.id.posts_tow_area_recy)
    RecyclerView postsTowAreaRecy;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.posts_back_iv)
    ImageView postsBackIv;
    @BindView(R.id.posts_release_iv)
    ImageView postsReleaseIv;
    @BindView(R.id.posts_original_switch)
    SwitchView postsOriginalSwitch;
    @BindView(R.id.posts_reprint_switch)
    SwitchView postsReprintSwitch;
    @BindView(R.id.avi)
    AVLoadingIndicatorView avi;
    private List<LocalMedia> localMedia;//图片集合
    private PublishPostsAdapter adapter;
    private PublishAreaAdapter paAdapter;
    private PublishTwoAreaAdapter ptAdapter;
    private int bs = 0;
    private int size;
    private Gson mGson = new Gson();
    private List<WordTopicBean> mTopicList;
    private String url;
    private String id = "";//选中大区的id
    private String id2 = "";//选中分区的id
    private String origint = "";//是否原创
    private String reprint = "";//是否可转载

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
        getArea();//获取一级专区的数据
        switchOnclick();

    }

    //开关的选择
    private void switchOnclick() {
        postsOriginalSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isOpened = postsOriginalSwitch.isOpened();
                if (isOpened == true) {
                    origint = "0";
                    Log.e("JGB", "是原创");
                }
            }
        });
        postsReprintSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isOpened = postsReprintSwitch.isOpened();
                if (isOpened == true) {
                    reprint = "0";
                }
            }
        });
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
                        localMedia.add(new LocalMedia(localMedias.get(i).getCompressPath(), 0, 0, ""));
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


    //上传选择
    private void input() {
        switch (size) {
            case 1:
                RequestQueue requestQueue = NoHttp.newRequestQueue();
                Request<String> request = NoHttp.createStringRequest("http://www.olacos.net/upload/uploadFile.do", RequestMethod.POST);
                request.add("url", new FileBinary(new File(localMedia.get(0).getCompressPath())));//上传文件
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
                            String url1 = photoBean.getFilelist().get(0).getURL();
                            StringBuffer sb1 = new StringBuffer();
                            StringBuffer append = sb1.append(postsContentTv.getText().toString() + "<p><img src=\"").append("http://www.olacos.net").append(url1).append("\"/></p>");
                            Log.e("JGB", "图片上传结果：" + append);
                            case1ReleaseHttp(append, url1);
                        }
                    }

                    @Override
                    public void onFailed(int what, Response<String> response) {
                    }

                    @Override
                    public void onFinish(int what) {

                    }
                });
                break;
            case 2:
                RequestQueue requestQueue2 = NoHttp.newRequestQueue();
                Request<String> request2 = NoHttp.createStringRequest("http://www.olacos.net/upload/uploadFile.do", RequestMethod.POST);
                request2.add("url", new FileBinary(new File(localMedia.get(0).getCompressPath())));//上传文件
                requestQueue2.add(0, request2, new OnResponseListener<String>() {
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
                            String url1 = photoBean.getFilelist().get(0).getURL();
                            case2imageHttp(url1);


                        }
                    }

                    @Override
                    public void onFailed(int what, Response<String> response) {
                    }

                    @Override
                    public void onFinish(int what) {

                    }
                });
                break;
            case 3:
                RequestQueue requestQueue3 = NoHttp.newRequestQueue();
                Request<String> request3 = NoHttp.createStringRequest("http://www.olacos.net/upload/uploadFile.do", RequestMethod.POST);
                request3.add("url", new FileBinary(new File(localMedia.get(0).getCompressPath())));//上传文件
                requestQueue3.add(0, request3, new OnResponseListener<String>() {
                    @Override
                    public void onStart(int what) {
                    }

                    @Override
                    public void onSucceed(int what, Response<String> response) {
                        String json = response.get();
                        if (json == null) {
                            return;
                        } else {
                            PhotoBean photoBean = mGson.fromJson(json, PhotoBean.class);
                            String image31 = photoBean.getFilelist().get(0).getURL();
                            case32imageHttp(image31);

                        }
                    }

                    @Override
                    public void onFailed(int what, Response<String> response) {
                    }

                    @Override
                    public void onFinish(int what) {

                    }
                });
                break;

            case 4:
                RequestQueue requestQueue4 = NoHttp.newRequestQueue();
                Request<String> request4 = NoHttp.createStringRequest("http://www.olacos.net/upload/uploadFile.do", RequestMethod.POST);
                request4.add("url", new FileBinary(new File(localMedia.get(0).getCompressPath())));//上传文件
                requestQueue4.add(0, request4, new OnResponseListener<String>() {
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
                            String image41 = photoBean.getFilelist().get(0).getURL();
                            case42imageHttp(image41);


                        }
                    }

                    @Override
                    public void onFailed(int what, Response<String> response) {
                    }

                    @Override
                    public void onFinish(int what) {

                    }
                });
                break;
            case 5:
                RequestQueue requestQueue5 = NoHttp.newRequestQueue();
                Request<String> request5 = NoHttp.createStringRequest("http://www.olacos.net/upload/uploadFile.do", RequestMethod.POST);
                request5.add("url", new FileBinary(new File(localMedia.get(0).getCompressPath())));//上传文件
                requestQueue5.add(0, request5, new OnResponseListener<String>() {
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
                            String image51 = photoBean.getFilelist().get(0).getURL();
                            case52imageHttp(image51);


                        }
                    }

                    @Override
                    public void onFailed(int what, Response<String> response) {
                    }

                    @Override
                    public void onFinish(int what) {

                    }
                });
                break;
            case 6:
                RequestQueue requestQueue6 = NoHttp.newRequestQueue();
                Request<String> request6 = NoHttp.createStringRequest("http://www.olacos.net/upload/uploadFile.do", RequestMethod.POST);
                request6.add("url", new FileBinary(new File(localMedia.get(0).getCompressPath())));//上传文件
                requestQueue6.add(0, request6, new OnResponseListener<String>() {
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
                            String image61 = photoBean.getFilelist().get(0).getURL();
                            case62imageHttp(image61);


                        }
                    }

                    @Override
                    public void onFailed(int what, Response<String> response) {
                    }

                    @Override
                    public void onFinish(int what) {

                    }
                });
                break;
            case 7:
                RequestQueue requestQueue7 = NoHttp.newRequestQueue();
                Request<String> request7 = NoHttp.createStringRequest("http://www.olacos.net/upload/uploadFile.do", RequestMethod.POST);
                request7.add("url", new FileBinary(new File(localMedia.get(0).getCompressPath())));//上传文件
                requestQueue7.add(0, request7, new OnResponseListener<String>() {
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
                            String image71 = photoBean.getFilelist().get(0).getURL();
                            case72imageHttp(image71);


                        }
                    }

                    @Override
                    public void onFailed(int what, Response<String> response) {
                    }

                    @Override
                    public void onFinish(int what) {

                    }
                });
                break;
            case 8:
                RequestQueue requestQueue8 = NoHttp.newRequestQueue();
                Request<String> request8 = NoHttp.createStringRequest("http://www.olacos.net/upload/uploadFile.do", RequestMethod.POST);
                request8.add("url", new FileBinary(new File(localMedia.get(0).getCompressPath())));//上传文件
                requestQueue8.add(0, request8, new OnResponseListener<String>() {
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
                            String image81 = photoBean.getFilelist().get(0).getURL();
                            case82imageHttp(image81);


                        }
                    }

                    @Override
                    public void onFailed(int what, Response<String> response) {
                    }

                    @Override
                    public void onFinish(int what) {

                    }
                });
                break;
            default:
                Toast.makeText(PublishPostsActivity.this, "目前最多只能上传8张图片呢", Toast.LENGTH_SHORT).show();
                avi.hide();
                avi.setVisibility(View.GONE);
                postsReleaseIv.setVisibility(View.VISIBLE);

        }
    }

    /**
     * 上传图片
     */
    private void case82imageHttp(final String image81) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://www.olacos.net/upload/uploadFile.do", RequestMethod.POST);
        request.add("url", new FileBinary(new File(localMedia.get(1).getCompressPath())));//上传文件
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
                    String image82 = photoBean.getFilelist().get(0).getURL();
                    case83imageHttp(image81, image82);


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

    private void case83imageHttp(final String image81, final String image82) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://www.olacos.net/upload/uploadFile.do", RequestMethod.POST);
        request.add("url", new FileBinary(new File(localMedia.get(2).getCompressPath())));//上传文件
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
                    String image83 = photoBean.getFilelist().get(0).getURL();
                    case84imageHttp(image81, image82, image83);


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

    private void case84imageHttp(final String image81, final String image82, final String image83) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://www.olacos.net/upload/uploadFile.do", RequestMethod.POST);
        request.add("url", new FileBinary(new File(localMedia.get(3).getCompressPath())));//上传文件
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
                    String image84 = photoBean.getFilelist().get(0).getURL();
                    case85imageHttp(image81, image82, image83, image84);


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

    private void case85imageHttp(final String image81, final String image82, final String image83, final String image84) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://www.olacos.net/upload/uploadFile.do", RequestMethod.POST);
        request.add("url", new FileBinary(new File(localMedia.get(4).getCompressPath())));//上传文件
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
                    String image85 = photoBean.getFilelist().get(0).getURL();
                    case86imageHttp(image81, image82, image83, image84, image85);


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

    private void case86imageHttp(final String image81, final String image82, final String image83, final String image84, final String image85) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://www.olacos.net/upload/uploadFile.do", RequestMethod.POST);
        request.add("url", new FileBinary(new File(localMedia.get(5).getCompressPath())));//上传文件
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
                    String image86 = photoBean.getFilelist().get(0).getURL();
                    case87imageHttp(image81, image82, image83, image84, image85, image86);


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

    private void case87imageHttp(final String image81, final String image82, final String image83, final String image84, final String image85, final String image86) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://www.olacos.net/upload/uploadFile.do", RequestMethod.POST);
        request.add("url", new FileBinary(new File(localMedia.get(6).getCompressPath())));//上传文件
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
                    String image87 = photoBean.getFilelist().get(0).getURL();
                    case88imageHttp(image81, image82, image83, image84, image85, image86, image87);


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

    private void case88imageHttp(final String image81, final String image82, final String image83, final String image84, final String image85, final String image86, final String image87) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://www.olacos.net/upload/uploadFile.do", RequestMethod.POST);
        request.add("url", new FileBinary(new File(localMedia.get(7).getCompressPath())));//上传文件
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
                    String image88 = photoBean.getFilelist().get(0).getURL();
                    StringBuffer sb8 = new StringBuffer();
                    StringBuffer append = sb8.append(postsContentTv.getText().toString() + "<p><img src=\"").append("http://www.olacos.net").append(image81).append("\"/></p>")
                            .append("<p><img src=\"").append("http://www.olacos.net").append(image82).append("\"/></p>")
                            .append("<p><img src=\"").append("http://www.olacos.net").append(image83).append("\"/></p>")
                            .append("<p><img src=\"").append("http://www.olacos.net").append(image84).append("\"/></p>")
                            .append("<p><img src=\"").append("http://www.olacos.net").append(image85).append("\"/></p>")
                            .append("<p><img src=\"").append("http://www.olacos.net").append(image86).append("\"/></p>")
                            .append("<p><img src=\"").append("http://www.olacos.net").append(image87).append("\"/></p>")
                            .append("<p><img src=\"").append("http://www.olacos.net").append(image88).append("\"/></p>");
                    Log.e("JGB", "图片上传结果：" + append);
                    case8ReleaseHttp(append, image88);


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

    private void case72imageHttp(final String image71) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://www.olacos.net/upload/uploadFile.do", RequestMethod.POST);
        request.add("url", new FileBinary(new File(localMedia.get(1).getCompressPath())));//上传文件
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
                    String image72 = photoBean.getFilelist().get(0).getURL();
                    case73imageHttp(image71, image72);


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

    private void case73imageHttp(final String image71, final String image72) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://www.olacos.net/upload/uploadFile.do", RequestMethod.POST);
        request.add("url", new FileBinary(new File(localMedia.get(2).getCompressPath())));//上传文件
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
                    String image73 = photoBean.getFilelist().get(0).getURL();
                    case74imageHttp(image71, image72, image73);


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

    private void case74imageHttp(final String image71, final String image72, final String image73) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://www.olacos.net/upload/uploadFile.do", RequestMethod.POST);
        request.add("url", new FileBinary(new File(localMedia.get(3).getCompressPath())));//上传文件
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
                    String image74 = photoBean.getFilelist().get(0).getURL();
                    case75imageHttp(image71, image72, image73, image74);


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

    private void case75imageHttp(final String image71, final String image72, final String image73, final String image74) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://www.olacos.net/upload/uploadFile.do", RequestMethod.POST);
        request.add("url", new FileBinary(new File(localMedia.get(4).getCompressPath())));//上传文件
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
                    String image75 = photoBean.getFilelist().get(0).getURL();
                    case76imageHttp(image71, image72, image73, image74, image75);


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

    private void case76imageHttp(final String image71, final String image72, final String image73, final String image74, final String image75) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://www.olacos.net/upload/uploadFile.do", RequestMethod.POST);
        request.add("url", new FileBinary(new File(localMedia.get(5).getCompressPath())));//上传文件
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
                    String image76 = photoBean.getFilelist().get(0).getURL();
                    case77imageHttp(image71, image72, image73, image74, image75, image76);


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

    private void case77imageHttp(final String image71, final String image72, final String image73, final String image74, final String image75, final String image76) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://www.olacos.net/upload/uploadFile.do", RequestMethod.POST);
        request.add("url", new FileBinary(new File(localMedia.get(6).getCompressPath())));//上传文件
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
                    String image77 = photoBean.getFilelist().get(0).getURL();
                    StringBuffer sb7 = new StringBuffer();
                    StringBuffer append = sb7.append(postsContentTv.getText().toString() + "<p><img src=\"").append("http://www.olacos.net").append(image71).append("\"/></p>")
                            .append("<p><img src=\"").append("http://www.olacos.net").append(image72).append("\"/></p>")
                            .append("<p><img src=\"").append("http://www.olacos.net").append(image73).append("\"/></p>")
                            .append("<p><img src=\"").append("http://www.olacos.net").append(image74).append("\"/></p>")
                            .append("<p><img src=\"").append("http://www.olacos.net").append(image75).append("\"/></p>")
                            .append("<p><img src=\"").append("http://www.olacos.net").append(image76).append("\"/></p>")
                            .append("<p><img src=\"").append("http://www.olacos.net").append(image77).append("\"/></p>");
                    Log.e("JGB", "图片上传结果：" + append);
                    case7ReleaseHttp(append, image77);
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

    private void case62imageHttp(final String image61) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://www.olacos.net/upload/uploadFile.do", RequestMethod.POST);
        request.add("url", new FileBinary(new File(localMedia.get(1).getCompressPath())));//上传文件
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
                    String image62 = photoBean.getFilelist().get(0).getURL();
                    case63imageHttp(image61, image62);


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

    private void case63imageHttp(final String image61, final String image62) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://www.olacos.net/upload/uploadFile.do", RequestMethod.POST);
        request.add("url", new FileBinary(new File(localMedia.get(2).getCompressPath())));//上传文件
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
                    String image63 = photoBean.getFilelist().get(0).getURL();
                    case64imageHttp(image61, image62, image63);


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

    private void case64imageHttp(final String image61, final String image62, final String image63) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://www.olacos.net/upload/uploadFile.do", RequestMethod.POST);
        request.add("url", new FileBinary(new File(localMedia.get(3).getCompressPath())));//上传文件
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
                    String image64 = photoBean.getFilelist().get(0).getURL();
                    case65imageHttp(image61, image62, image63, image64);


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

    private void case65imageHttp(final String image61, final String image62, final String image63, final String image64) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://www.olacos.net/upload/uploadFile.do", RequestMethod.POST);
        request.add("url", new FileBinary(new File(localMedia.get(4).getCompressPath())));//上传文件
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
                    String image65 = photoBean.getFilelist().get(0).getURL();
                    case66imageHttp(image61, image62, image63, image64, image65);
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

    private void case66imageHttp(final String image61, final String image62, final String image63, final String image64, final String image65) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://www.olacos.net/upload/uploadFile.do", RequestMethod.POST);
        request.add("url", new FileBinary(new File(localMedia.get(5).getCompressPath())));//上传文件
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
                    String image66 = photoBean.getFilelist().get(0).getURL();
                    StringBuffer sb6 = new StringBuffer();
                    StringBuffer append = sb6.append(postsContentTv.getText().toString() + "<p><img src=\"").append("http://www.olacos.net").append(image61).append("\"/></p>")
                            .append("<p><img src=\"").append("http://www.olacos.net").append(image62).append("\"/></p>")
                            .append("<p><img src=\"").append("http://www.olacos.net").append(image63).append("\"/></p>")
                            .append("<p><img src=\"").append("http://www.olacos.net").append(image64).append("\"/></p>")
                            .append("<p><img src=\"").append("http://www.olacos.net").append(image65).append("\"/></p>")
                            .append("<p><img src=\"").append("http://www.olacos.net").append(image66).append("\"/></p>");
                    Log.e("JGB", "图片上传结果：" + append);
                    case6ReleaseHttp(append, image66);
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

    private void case52imageHttp(final String image51) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://www.olacos.net/upload/uploadFile.do", RequestMethod.POST);
        request.add("url", new FileBinary(new File(localMedia.get(1).getCompressPath())));//上传文件
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
                    String image52 = photoBean.getFilelist().get(0).getURL();
                    case53imageHttp(image51, image52);


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

    private void case53imageHttp(final String image51, final String image52) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://www.olacos.net/upload/uploadFile.do", RequestMethod.POST);
        request.add("url", new FileBinary(new File(localMedia.get(2).getCompressPath())));//上传文件
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
                    String image53 = photoBean.getFilelist().get(0).getURL();
                    case54imageHttp(image51, image52, image53);


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

    private void case54imageHttp(final String image51, final String image52, final String image53) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://www.olacos.net/upload/uploadFile.do", RequestMethod.POST);
        request.add("url", new FileBinary(new File(localMedia.get(3).getCompressPath())));//上传文件
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
                    String image54 = photoBean.getFilelist().get(0).getURL();
                    case55imageHttp(image51, image52, image53, image54);


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

    private void case55imageHttp(final String image51, final String image52, final String image53, final String image54) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://www.olacos.net/upload/uploadFile.do", RequestMethod.POST);
        request.add("url", new FileBinary(new File(localMedia.get(4).getCompressPath())));//上传文件
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
                    String image55 = photoBean.getFilelist().get(0).getURL();
                    StringBuffer sb5 = new StringBuffer();
                    StringBuffer append = sb5.append(postsContentTv.getText().toString() + "<p><img src=\"").append("http://www.olacos.net").append(image51).append("\"/></p>")
                            .append("<p><img src=\"").append("http://www.olacos.net").append(image52).append("\"/></p>")
                            .append("<p><img src=\"").append("http://www.olacos.net").append(image53).append("\"/></p>")
                            .append("<p><img src=\"").append("http://www.olacos.net").append(image54).append("\"/></p>")
                            .append("<p><img src=\"").append("http://www.olacos.net").append(image55).append("\"/></p>");
                    Log.e("JGB", "图片上传结果：" + append);
                    case5ReleaseHttp(append, image55);
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

    private void case42imageHttp(final String image41) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://www.olacos.net/upload/uploadFile.do", RequestMethod.POST);
        request.add("url", new FileBinary(new File((localMedia.get(1).getCompressPath()))));//上传文件
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
                    PhotoBean photoBean = mGson.fromJson(json, PhotoBean.class);
                    String image42 = photoBean.getFilelist().get(0).getURL();
                    case43imageHttp(image41, image42);


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

    private void case43imageHttp(final String image41, final String image42) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://www.olacos.net/upload/uploadFile.do", RequestMethod.POST);
        request.add("url", new FileBinary(new File((localMedia.get(2).getCompressPath()))));//上传文件
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
                    PhotoBean photoBean = mGson.fromJson(json, PhotoBean.class);
                    String image43 = photoBean.getFilelist().get(0).getURL();
                    case44imageHttp(image41, image42, image43);


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

    private void case44imageHttp(final String image41, final String image42, final String image43) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://www.olacos.net/upload/uploadFile.do", RequestMethod.POST);
        request.add("url", new FileBinary(new File((localMedia.get(3).getCompressPath()))));//上传文件
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
                    PhotoBean photoBean = mGson.fromJson(json, PhotoBean.class);
                    String image44 = photoBean.getFilelist().get(0).getURL();
                    StringBuffer sb4 = new StringBuffer();
                    StringBuffer append = sb4.append(postsContentTv.getText().toString() + "<p><img src=\"").append("http://www.olacos.net").append(image41).append("\"/></p>")
                            .append("<p><img src=\"").append("http://www.olacos.net").append(image42).append("\"/></p>")
                            .append("<p><img src=\"").append("http://www.olacos.net").append(image43).append("\"/></p>")
                            .append("<p><img src=\"").append("http://www.olacos.net").append(image44).append("\"/></p>");
                    Log.e("JGB", "图片上传结果：" + append);
                    case4ReleaseHttp(append, image44);
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

    private void case32imageHttp(final String image31) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://www.olacos.net/upload/uploadFile.do", RequestMethod.POST);
        request.add("url", new FileBinary(new File((localMedia.get(1).getCompressPath()))));//上传文件
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

                    PhotoBean photoBean = mGson.fromJson(json, PhotoBean.class);
                    String image32 = photoBean.getFilelist().get(0).getURL();
                    case33imageHttp(image31, image32);


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

    private void case33imageHttp(final String image31, final String image32) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://www.olacos.net/upload/uploadFile.do", RequestMethod.POST);
        request.add("url", new FileBinary(new File((localMedia.get(2).getCompressPath()))));//上传文件
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
                    PhotoBean photoBean = mGson.fromJson(json, PhotoBean.class);
                    String image33 = photoBean.getFilelist().get(0).getURL();
                    StringBuffer sb3 = new StringBuffer();
                    StringBuffer append = sb3.append(postsContentTv.getText().toString() + "<p><img src=\"").append("http://www.olacos.net").append(image31).append("\"/></p>").append("<p><img src=\"")
                            .append("http://www.olacos.net").append(image32).append("\"/></p>")
                            .append("<p><img src=\"").append("http://www.olacos.net").append(image33).append("\"/></p>");
                    Log.e("JGB", "图片上传结果：" + append);
                    case3ReleaseHttp(append, image33);
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

    private void case2imageHttp(final String avaterurl) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://www.olacos.net/upload/uploadFile.do", RequestMethod.POST);
        request.add("url", new FileBinary(new File((localMedia.get(1).getCompressPath()))));//上传文件
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
                    Log.e("JGB", "图片2上传结果" + json);
                    PhotoBean photoBean = mGson.fromJson(json, PhotoBean.class);
                    String url = photoBean.getFilelist().get(0).getURL();
                    StringBuffer sb2 = new StringBuffer();
                    StringBuffer append = sb2.append(postsContentTv.getText().toString() + "<p><img src=\"").append("http://www.olacos.net").append(avaterurl).append("\"/></p>").append("<p><img src=\"").append("http://www.olacos.net").append(url).append("\"/></p>");
                    Log.e("JGB", "图片上传结果：" + append);
                    case2ReleaseHttp(append, url);
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


    /**
     * 发布帖子
     */

    private void ReleaseHttp() {//只有文字内容
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://www.olacos.net/topicMobile/publishTopic.do", RequestMethod.POST);
        request.add("bigPartId", id);
        request.add("partId", id2);
        request.add("coverImg", "");
        request.add("content", postsContentTv.getText().toString());
        request.add("title", postsTitleTv.getText().toString());
        request.add("userId", AppHelper.getInstance().getUser().getID());
        request.add("district", "北京");
        request.add("original", origint);
        request.add("reprint", reprint);
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
                    PostBean postBean = mGson.fromJson(json, PostBean.class);
                    if(postBean.isOk()==true){
                        avi.hide();
                        avi.setVisibility(View.GONE);
                        postsReleaseIv.setVisibility(View.VISIBLE);
                        Toast.makeText(PublishPostsActivity.this,"发帖成功!",Toast.LENGTH_SHORT).show();
                    }else{
                        avi.setVisibility(View.GONE);
                        postsReleaseIv.setVisibility(View.VISIBLE);
                        Toast.makeText(PublishPostsActivity.this,"发帖失败!",Toast.LENGTH_SHORT).show();
                    }
                    Log.e("JGB", "发布结果：" + json);

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

    private void case2ReleaseHttp(StringBuffer imageurl, String url) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://www.olacos.net/topicMobile/publishTopic.do", RequestMethod.POST);
        request.add("bigPartId", id);
        request.add("partId", id2);
        request.add("coverImg", url);
        request.add("content", imageurl + "");
        request.add("title", postsTitleTv.getText().toString());
        request.add("userId", AppHelper.getInstance().getUser().getID());
        request.add("district", "北京");
        request.add("original", origint);
        request.add("reprint", reprint);
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
                    PostBean postBean = mGson.fromJson(json, PostBean.class);
                    if(postBean.isOk()==true){
                        avi.hide();
                        avi.setVisibility(View.GONE);
                        postsReleaseIv.setVisibility(View.VISIBLE);
                        Toast.makeText(PublishPostsActivity.this,"发帖成功!",Toast.LENGTH_SHORT).show();
                    }else{
                        avi.setVisibility(View.GONE);
                        postsReleaseIv.setVisibility(View.VISIBLE);
                        Toast.makeText(PublishPostsActivity.this,"发帖失败!",Toast.LENGTH_SHORT).show();
                    }
                    Log.e("JGB", "发布结果：" + json);
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

    private void case3ReleaseHttp(StringBuffer append, String image33) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://www.olacos.net/topicMobile/publishTopic.do", RequestMethod.POST);
        request.add("bigPartId", id);
        request.add("partId", id2);
        request.add("coverImg", image33);
        request.add("content", append + "");
        request.add("title", postsTitleTv.getText().toString());
        request.add("userId", AppHelper.getInstance().getUser().getID());
        request.add("district", "");
        request.add("original", origint);
        request.add("reprint", reprint);
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
                    PostBean postBean = mGson.fromJson(json, PostBean.class);
                    if(postBean.isOk()==true){
                        avi.hide();
                        avi.setVisibility(View.GONE);
                        postsReleaseIv.setVisibility(View.VISIBLE);
                        Toast.makeText(PublishPostsActivity.this,"发帖成功!",Toast.LENGTH_SHORT).show();
                    }else{
                        avi.setVisibility(View.GONE);
                        postsReleaseIv.setVisibility(View.VISIBLE);
                        Toast.makeText(PublishPostsActivity.this,"发帖失败!",Toast.LENGTH_SHORT).show();
                    }
                    Log.e("JGB", "发布结果：" + json);
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

    private void case4ReleaseHttp(StringBuffer append, String image44) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://www.olacos.net/topicMobile/publishTopic.do", RequestMethod.POST);
        request.add("bigPartId", id);
        request.add("partId", id2);
        request.add("coverImg", image44);
        request.add("content", append + "");
        request.add("title", postsTitleTv.getText().toString());
        request.add("userId", AppHelper.getInstance().getUser().getID());
        request.add("district", "");
        request.add("original", origint);
        request.add("reprint", reprint);
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
                    PostBean postBean = mGson.fromJson(json, PostBean.class);
                    if(postBean.isOk()==true){
                        avi.hide();
                        avi.setVisibility(View.GONE);
                        postsReleaseIv.setVisibility(View.VISIBLE);
                        Toast.makeText(PublishPostsActivity.this,"发帖成功!",Toast.LENGTH_SHORT).show();
                    }else{
                        avi.setVisibility(View.GONE);
                        postsReleaseIv.setVisibility(View.VISIBLE);
                        Toast.makeText(PublishPostsActivity.this,"发帖失败!",Toast.LENGTH_SHORT).show();
                    }
                    Log.e("JGB", "发布结果：" + json);
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

    private void case5ReleaseHttp(StringBuffer append, String image55) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://www.olacos.net/topicMobile/publishTopic.do", RequestMethod.POST);
        request.add("bigPartId", id);
        request.add("partId", id2);
        request.add("coverImg", image55);
        request.add("content", append + "");
        request.add("title", postsTitleTv.getText().toString());
        request.add("userId", AppHelper.getInstance().getUser().getID());
        request.add("district", "");
        request.add("original", origint);
        request.add("reprint", reprint);
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
                    PostBean postBean = mGson.fromJson(json, PostBean.class);
                    if(postBean.isOk()==true){
                        avi.hide();
                        avi.setVisibility(View.GONE);
                        postsReleaseIv.setVisibility(View.VISIBLE);
                        Toast.makeText(PublishPostsActivity.this,"发帖成功!",Toast.LENGTH_SHORT).show();
                    }else{
                        avi.setVisibility(View.GONE);
                        postsReleaseIv.setVisibility(View.VISIBLE);
                        Toast.makeText(PublishPostsActivity.this,"发帖失败!",Toast.LENGTH_SHORT).show();
                    }
                    Log.e("JGB", "发布结果：" + json);
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

    private void case6ReleaseHttp(StringBuffer append, String image66) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://www.olacos.net/topicMobile/publishTopic.do", RequestMethod.POST);
        request.add("bigPartId", id);
        request.add("partId", id2);
        request.add("coverImg", image66);
        request.add("content", append + "");
        request.add("title", postsTitleTv.getText().toString());
        request.add("userId", AppHelper.getInstance().getUser().getID());
        request.add("district", "");
        request.add("original", origint);
        request.add("reprint", reprint);
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
                    PostBean postBean = mGson.fromJson(json, PostBean.class);
                    if(postBean.isOk()==true){
                        avi.hide();
                        avi.setVisibility(View.GONE);
                        postsReleaseIv.setVisibility(View.VISIBLE);
                        Toast.makeText(PublishPostsActivity.this,"发帖成功!",Toast.LENGTH_SHORT).show();
                    }else{
                        avi.setVisibility(View.GONE);
                        postsReleaseIv.setVisibility(View.VISIBLE);
                        Toast.makeText(PublishPostsActivity.this,"发帖失败!",Toast.LENGTH_SHORT).show();
                    }
                    Log.e("JGB", "发布结果：" + json);
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

    private void case7ReleaseHttp(StringBuffer append, String image77) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://www.olacos.net/topicMobile/publishTopic.do", RequestMethod.POST);
        request.add("bigPartId", id);
        request.add("partId", id2);
        request.add("coverImg", image77);
        request.add("content", append + "");
        request.add("title", postsTitleTv.getText().toString());
        request.add("userId", AppHelper.getInstance().getUser().getID());
        request.add("district", "");
        request.add("original", origint);
        request.add("reprint", reprint);
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
                    PostBean postBean = mGson.fromJson(json, PostBean.class);
                    if(postBean.isOk()==true){
                        avi.hide();
                        avi.setVisibility(View.GONE);
                        postsReleaseIv.setVisibility(View.VISIBLE);
                        Toast.makeText(PublishPostsActivity.this,"发帖成功!",Toast.LENGTH_SHORT).show();
                    }else{
                        avi.setVisibility(View.GONE);
                        postsReleaseIv.setVisibility(View.VISIBLE);
                        Toast.makeText(PublishPostsActivity.this,"发帖失败!",Toast.LENGTH_SHORT).show();
                    }
                    Log.e("JGB", "发布结果：" + json);
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

    private void case8ReleaseHttp(StringBuffer append, String image88) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://www.olacos.net/topicMobile/publishTopic.do", RequestMethod.POST);
        request.add("bigPartId", id);
        request.add("partId", id2);
        request.add("coverImg", image88);
        request.add("content", append + "");
        request.add("title", postsTitleTv.getText().toString());
        request.add("userId", AppHelper.getInstance().getUser().getID());
        request.add("district", "");
        request.add("original", origint);
        request.add("reprint", reprint);
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
                    PostBean postBean = mGson.fromJson(json, PostBean.class);
                    if(postBean.isOk()==true){
                        avi.hide();
                        avi.setVisibility(View.GONE);
                        postsReleaseIv.setVisibility(View.VISIBLE);
                        Toast.makeText(PublishPostsActivity.this,"发帖成功!",Toast.LENGTH_SHORT).show();
                    }else{
                        avi.setVisibility(View.GONE);
                        postsReleaseIv.setVisibility(View.VISIBLE);
                        Toast.makeText(PublishPostsActivity.this,"发帖失败!",Toast.LENGTH_SHORT).show();
                    }
                    Log.e("JGB", "发布结果：" + json);
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

    private void case1ReleaseHttp(StringBuffer append, String url1) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://www.olacos.net/topicMobile/publishTopic.do", RequestMethod.POST);
        request.add("bigPartId", id);
        request.add("partId", id2);
        request.add("coverImg", url1);
        request.add("content", append + "");
        request.add("title", postsTitleTv.getText().toString());
        request.add("userId", AppHelper.getInstance().getUser().getID());
        request.add("district", "");
        request.add("original", origint);
        request.add("reprint", reprint);
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
                    PostBean postBean = mGson.fromJson(json, PostBean.class);
                    if(postBean.isOk()==true){
                        avi.hide();
                        avi.setVisibility(View.GONE);
                        postsReleaseIv.setVisibility(View.VISIBLE);
                        Toast.makeText(PublishPostsActivity.this,"发帖成功!",Toast.LENGTH_SHORT).show();
                    }else{
                        avi.setVisibility(View.GONE);
                        postsReleaseIv.setVisibility(View.VISIBLE);
                        Toast.makeText(PublishPostsActivity.this,"发帖失败!",Toast.LENGTH_SHORT).show();
                    }
                    Log.e("JGB", "发布结果：" + json);
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

    @OnClick({R.id.posts_back_iv, R.id.posts_release_iv, R.id.posts_click_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.posts_back_iv:
                finish();
                break;
            case R.id.posts_release_iv:
                if (size == 0) {//如果图片集合为空代表直发文字
                    if (postsTitleTv.getText().toString().isEmpty()) {
                        Toast.makeText(PublishPostsActivity.this, "标题不能为空哦", Toast.LENGTH_SHORT).show();
                    } else if (postsContentTv.getText().toString().isEmpty()) {
                        Toast.makeText(PublishPostsActivity.this, "内容不能为空哦", Toast.LENGTH_SHORT).show();
                    } else if (id.equals("")) {
                        Toast.makeText(PublishPostsActivity.this, "还没有选中你要发布的专区哦", Toast.LENGTH_SHORT).show();
                    } else if (id2.equals("")) {
                        Toast.makeText(PublishPostsActivity.this, "还没有选中你要发布的二级专区哦", Toast.LENGTH_SHORT).show();
                    } else {
                        avi.setVisibility(View.VISIBLE);
                        postsReleaseIv.setVisibility(View.GONE);
                        ReleaseHttp();
                    }
                } else {
                    if (postsTitleTv.getText().toString().isEmpty()) {
                        Toast.makeText(PublishPostsActivity.this, "标题不能为空哦", Toast.LENGTH_SHORT).show();
                    } else if (id.equals("")) {
                        Toast.makeText(PublishPostsActivity.this, "还没有选中你要发布的专区哦", Toast.LENGTH_SHORT).show();
                    } else if (id2.equals("")) {
                        Toast.makeText(PublishPostsActivity.this, "还没有选中你要发布的二级专区哦", Toast.LENGTH_SHORT).show();
                    } else {
                        avi.setVisibility(View.VISIBLE);
                        postsReleaseIv.setVisibility(View.GONE);
                        input();
                    }
                }
                break;
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
        }
    }


}
