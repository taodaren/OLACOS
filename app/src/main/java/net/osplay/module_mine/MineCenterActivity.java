package net.osplay.module_mine;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import net.osplay.module_mine.fragment.OrderPayFragment;
import net.osplay.olacos.R;
import net.osplay.olacos.base.BaseActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 个人中心
 */

public class MineCenterActivity extends BaseActivity implements View.OnClickListener {
    private CircleImageView mIvAvatar;

    //图库
    private static final int PICTURE = 100;
    //相机
    private static final int CAMERA = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_center);
        initView();
        setToolbar();
        setClickListener();
    }

    private void initView() {
        mIvAvatar = (CircleImageView) findViewById(R.id.mine_avatar);
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_mine_center);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            //隐藏 Toolbar 自带标题栏
            actionBar.setDisplayShowTitleEnabled(false);
            //设置侧滑导航按钮图标
            actionBar.setHomeAsUpIndicator(R.drawable.title_back);
        }
        //隐藏 CollapsingToolbarLayout 标题
        collapsingToolbar.setTitleEnabled(false);
    }

    private void setClickListener() {
        findViewById(R.id.mine_avatar).setOnClickListener(this);
        findViewById(R.id.mine_praise).setOnClickListener(this);
        findViewById(R.id.mine_follow).setOnClickListener(this);
        findViewById(R.id.mine_fans).setOnClickListener(this);
        findViewById(R.id.mine_column).setOnClickListener(this);
        findViewById(R.id.mine_word).setOnClickListener(this);
        findViewById(R.id.mine_league).setOnClickListener(this);
        findViewById(R.id.mine_money).setOnClickListener(this);
        findViewById(R.id.mine_bao_xiang).setOnClickListener(this);
        findViewById(R.id.mine_order).setOnClickListener(this);
        findViewById(R.id.mine_pay).setOnClickListener(this);
        findViewById(R.id.mine_ship).setOnClickListener(this);
        findViewById(R.id.mine_receipt).setOnClickListener(this);
        findViewById(R.id.mine_assess).setOnClickListener(this);
        findViewById(R.id.mine_tui_huo).setOnClickListener(this);
        findViewById(R.id.mine_fa_bu).setOnClickListener(this);
        findViewById(R.id.mine_mai_chu).setOnClickListener(this);
        findViewById(R.id.mine_mai_dao).setOnClickListener(this);
        findViewById(R.id.mine_zan_guo).setOnClickListener(this);
        findViewById(R.id.mine_about).setOnClickListener(this);
        findViewById(R.id.mine_set).setOnClickListener(this);
    }

    /**
     * 创建选项菜单
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        //显示需要菜单项，隐藏多余菜单项
        menu.findItem(R.id.menu_category).setVisible(false);
        menu.findItem(R.id.menu_code).setVisible(true);
        menu.findItem(R.id.menu_msg).setVisible(false);
        menu.findItem(R.id.menu_register).setVisible(false);
        menu.findItem(R.id.menu_set).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
//            case R.id.menu_set:
//                startActivity(new Intent(MineCenterActivity.this, MineSetActivity.class));
//                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_avatar:
                String[] items = new String[]{"图库", "相机"};
                //提供一个AlertDialog
                new AlertDialog.Builder(MineCenterActivity.this)
                        .setTitle("选择来源")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0://图库
                                        startActivityForResult(new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), PICTURE);
                                        break;
                                    case 1://相机
                                        startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), CAMERA);
                                        break;
                                }
                            }
                        })
                        .setCancelable(false)
                        .show();
                break;
            case R.id.mine_praise:

                break;
            case R.id.mine_follow:

                break;
            case R.id.mine_fans:

                break;
            case R.id.mine_column:

                break;
            case R.id.mine_word:

                break;
            case R.id.mine_league:

                break;
            case R.id.mine_money:

                break;
            case R.id.mine_bao_xiang:

                break;
            case R.id.mine_order:
                startActivity(new Intent(MineCenterActivity.this, OrderActivity.class));
                break;
            case R.id.mine_pay:
                OrderPayFragment orderPayFragment = (OrderPayFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_order_par);
                startActivity(new Intent(MineCenterActivity.this, OrderActivity.class));
                break;
            case R.id.mine_ship:

                break;
            case R.id.mine_receipt:

                break;
            case R.id.mine_assess:

                break;
            case R.id.mine_tui_huo:

                break;
            case R.id.mine_fa_bu:

                break;
            case R.id.mine_mai_chu:

                break;
            case R.id.mine_mai_dao:

                break;
            case R.id.mine_zan_guo:

                break;
            case R.id.mine_about:
                Toast.makeText(this, "关于 OLACOS", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mine_set:
                startActivity(new Intent(MineCenterActivity.this, MineSetActivity.class));
                break;
        }
    }

    //回调方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA && resultCode == RESULT_OK && data != null) {//相机
            //获取intent中的图片对象
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");
            //加载显示
            mIvAvatar.setImageBitmap(bitmap);
            //上传到服务器（省略）

            //保存到本地
            saveImage(bitmap);
        } else if (requestCode == PICTURE && resultCode == RESULT_OK && data != null) {//图库
            //图库
            Uri selectedImage = data.getData();
            //android各个不同的系统版本,对于获取外部存储上的资源，返回的Uri对象都可能各不一样,
            //所以要保证无论是哪个系统版本都能正确获取到图片资源的话就需要针对各种情况进行一个处理了
            //这里返回的uri情况就有点多了
            //在4.4.2之前返回的uri是:content://media/external/images/media/3951或者file://....
            //在4.4.2返回的是content://com.android.providers.media.documents/document/image
            String pathResult = getPath(selectedImage);
            //存储 → 内存
            Bitmap decodeFile = BitmapFactory.decodeFile(pathResult);

            //加载显示
            mIvAvatar.setImageBitmap(decodeFile);
            //上传到服务器（省略）

            //保存到本地
            saveImage(decodeFile);
        }
    }

    //

    /**
     * 将Bitmap保存到本地的操作
     * <p>
     * 数据的存储。（5种）
     * Bitmap:内存层面的图片对象。
     * <p>
     * 存储 → 内存：
     * BitmapFactory.decodeFile(String filePath);
     * BitmapFactory.decodeStream(InputStream is);
     * 内存 → 存储：
     * bitmap.compress(Bitmap.CompressFormat.PNG,100,OutputStream os);
     */
    private void saveImage(Bitmap bitmap) {
        File filesDir;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {//判断sd卡是否挂载
            //路径1：storage/sdcard/Android/data/包名/files
            filesDir = this.getExternalFilesDir("");
        } else {//手机内部存储
            //路径：data/data/包名/files
            filesDir = this.getFilesDir();
        }
        FileOutputStream fos = null;
        try {
            File file = new File(filesDir, "icon.png");
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 根据系统相册选择的文件获取路径
     */
    @SuppressLint("NewApi")
    private String getPath(Uri uri) {
        int sdkVersion = Build.VERSION.SDK_INT;

        if (sdkVersion >= 19) {//高于4.4.2的版本
            Log.e("TAG", "uri auth: " + uri.getAuthority());
            if (isExternalStorageDocument(uri)) {
                String docId = DocumentsContract.getDocumentId(uri);
                String[] split = docId.split(":");
                String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris
                        .withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(this, contentUri, null, null);
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(this, contentUri, selection, selectionArgs);
            } else if (isMedia(uri)) {
                String[] proj = {MediaStore.Images.Media.DATA};
                Cursor actualimagecursor = this.managedQuery(uri, proj, null, null, null);
                int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                actualimagecursor.moveToFirst();
                return actualimagecursor.getString(actual_image_column_index);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            //返回远程地址
            if (isGooglePhotosUri(uri)) {
                return uri.getLastPathSegment();
            }
            return getDataColumn(this, uri, null, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {// File
            return uri.getPath();
        }
        return null;
    }

    /**
     * uri 路径查询字段
     */
    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    private boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isMedia(Uri uri) {
        return "media".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Uri 权限是否是Google照片
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

}



