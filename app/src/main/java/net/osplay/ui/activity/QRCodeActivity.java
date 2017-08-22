package net.osplay.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import net.osplay.olacos.R;

import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerActivity;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.QRCodeDecoder;

public class QRCodeActivity extends BaseActivity implements QRCodeView.Delegate {
    private static final String TAG = QRCodeActivity.class.getSimpleName();
    //从相册选取二维码图片
    private static final int REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY = 666;

    private QRCodeView qrCodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        qrCodeView = (QRCodeView) findViewById(R.id.zxingview);
        qrCodeView.setDelegate(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        qrCodeView.startCamera();
//        qrCodeView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT);

        qrCodeView.showScanRect();
    }

    @Override
    protected void onStop() {
        qrCodeView.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        qrCodeView.onDestroy();
        super.onDestroy();
    }

    /**
     * 处理扫描结果
     */
    @Override
    public void onScanQRCodeSuccess(String result) {
        Log.i(TAG, "result:" + result);
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        vibrate();
        qrCodeView.startSpot();
    }

    /**
     * 震动
     */
    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    /**
     * 打开相机出错
     */
    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.e(TAG, "打开相机出错");
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_spot:
                qrCodeView.startSpot();
                break;
            case R.id.stop_spot:
                qrCodeView.stopSpot();
                break;
            case R.id.start_spot_showrect:
                qrCodeView.startSpotAndShowRect();
                break;
            case R.id.stop_spot_hiddenrect:
                qrCodeView.stopSpotAndHiddenRect();
                break;
            case R.id.show_rect:
                qrCodeView.showScanRect();
                break;
            case R.id.hidden_rect:
                qrCodeView.hiddenScanRect();
                break;
            case R.id.start_preview:
                qrCodeView.startCamera();
                break;
            case R.id.stop_preview:
                qrCodeView.stopCamera();
                break;
            case R.id.open_flashlight:
                qrCodeView.openFlashlight();
                break;
            case R.id.close_flashlight:
                qrCodeView.closeFlashlight();
                break;
            case R.id.scan_barcode:
                qrCodeView.changeToScanBarcodeStyle();
                break;
            case R.id.scan_qrcode:
                qrCodeView.changeToScanQRCodeStyle();
                break;
            case R.id.choose_qrcde_from_gallery:
                /*
                从相册选取二维码图片，这里为了方便演示，使用的是
                https://github.com/bingoogolapple/BGAPhotoPicker-Android
                这个库来从图库中选择二维码图片，这个库不是必须的，你也可以通过自己的方式从图库中选择图片
                 */
                startActivityForResult(BGAPhotoPickerActivity.newIntent(this, null, 1, null, false), REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        qrCodeView.showScanRect();

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY) {
            final String picturePath = BGAPhotoPickerActivity.getSelectedImages(data).get(0);

            /*
            这里为了偷懒，就没有处理匿名 AsyncTask 内部类导致 Activity 泄漏的问题
            请开发在使用时自行处理匿名内部类导致Activity内存泄漏的问题，处理方式可参考 https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/Android%E5%86%85%E5%AD%98%E6%B3%84%E6%BC%8F%E6%80%BB%E7%BB%93.md
             */
            new AsyncTask<Void, Void, String>() {
                @Override
                protected String doInBackground(Void... params) {
                    return QRCodeDecoder.syncDecodeQRCode(picturePath);
                }

                @Override
                protected void onPostExecute(String result) {
                    if (TextUtils.isEmpty(result)) {
                        Toast.makeText(QRCodeActivity.this, "未发现二维码", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(QRCodeActivity.this, result, Toast.LENGTH_SHORT).show();
                    }
                }
            }.execute();
        }
    }

}