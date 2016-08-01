package com.library.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.library.R;
import com.library.dialog.DialogLoading;
import com.library.utils.SdCardUtil;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;


public abstract class BasicActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_CAMERA = 1001;
    public static final int REQUEST_CODE_PHOTO = 2001;
    public static final int REQUEST_CODE_PHOTO_DEAL = 3001;
    public int screenWidth = 0;
    public int screenHeight = 0;
    public Resources res;
    public DialogLoading loading;
    protected BasicActivity context;
    private Toast mToast;

    private boolean isShowKeyboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewId());
        context = this;
        loading = new DialogLoading(context);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        res = getResources();

        onGetBundle(getIntent().getExtras());

        init();
    }

    protected abstract void onGetBundle(Bundle bundle);


    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    /**
     * 初始化toolbar
     *
     * @param toolbar
     */
    public void setToolbar(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.drawable.nav_back);
        TextView titleTv = (TextView) toolbar.findViewById(R.id.title);
        if (titleTv != null) {
            titleTv.setText(getTitle());
        }
        toolbar.setMinimumWidth(screenWidth);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    public void setToolbar(Toolbar toolbar, String title) {
        toolbar.setNavigationIcon(R.drawable.nav_back);
        TextView titleTv = (TextView) toolbar.findViewById(R.id.title);
        if (titleTv != null) {
            titleTv.setText(title);
        }
        toolbar.setMinimumWidth(screenWidth);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    public void setUnBackToolBar(Toolbar toolbar) {
        toolbar.setMinimumWidth(screenWidth);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    public void setUnBackToolBar(Toolbar toolbar, String title) {
        toolbar.setMinimumWidth(screenWidth);
        TextView titleTv = (TextView) toolbar.findViewById(R.id.title);
        if (titleTv != null) {
            titleTv.setText(title);
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.inject(this);
    }

    public void setContentView(View view, LayoutParams params) {
        super.setContentView(view, params);
        ButterKnife.inject(this);
    }

    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.inject(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * 覆写finish方法，覆盖默认方法，加入切换动画
     */
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right_out);
    }

    public void finishResult(Intent intene) {
        setResult(RESULT_OK, intene);
        this.finish();
    }

    public void finishResult() {
        setResult(RESULT_OK);
        this.finish();
    }

    public void finishSimple() {
        super.finish();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 覆写startactivity方法，加入切换动画
     */
    public void startActivity(Bundle bundle, Class<?> target) {
        Intent intent = new Intent(this, target);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
    }

    /**
     * 带回调的跳转
     *
     * @param bundle
     * @param requestCode
     * @param target
     */
    public void startForResult(Bundle bundle, int requestCode, Class<?> target) {
        Intent intent = new Intent(this, target);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
    }

    /**
     * 打开照相
     */
    public void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.parse(SdCardUtil.TEMP));
        startActivityForResult(intent, REQUEST_CODE_CAMERA);
    }

    public void openCamera(int code) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.parse(SdCardUtil.TEMP));
        startActivityForResult(intent, code);
    }

    /**
     * 打开照相
     */
    public void openCamera(String path) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.parse("file:///" + path));
        startActivityForResult(intent, REQUEST_CODE_CAMERA);
    }

    /**
     * 打开照片选择
     */
    public void pickUpPhoto() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, REQUEST_CODE_PHOTO);
    }

    /**
     * 打开照片选择
     */
    public void pickUpPhoto(int code) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, code);
    }

    /*
     * 对图片进行剪裁，通过Intent来调用系统自带的图片剪裁API
     */
    protected void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        /* aspectX aspectY 是裁剪后图片的宽高比 */
        intent.putExtra("aspectX", 5);
        intent.putExtra("aspectY", 5);
        /* outputX outputY 是裁剪图片宽 */
        intent.putExtra("outputX", 256);
        intent.putExtra("outputY", 256);
        intent.putExtra("noFaceDetection", true);// 关闭人脸
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.parse(SdCardUtil.TEMP));
        startActivityForResult(intent, REQUEST_CODE_PHOTO_DEAL);
    }

    /*
     * 对图片进行剪裁，通过Intent来调用系统自带的图片剪裁API
     */
    protected void cropPhotoTop(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        /* aspectX aspectY 是裁剪后图片的宽高比 */
        intent.putExtra("aspectX", 64);
        intent.putExtra("aspectY", 45);
        /* outputX outputY 是裁剪图片宽 */
        intent.putExtra("outputX", 640);
        intent.putExtra("outputY", 450);
        intent.putExtra("noFaceDetection", true);// 关闭人脸
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.parse(SdCardUtil.TEMP));
        startActivityForResult(intent, REQUEST_CODE_PHOTO_DEAL);
    }

    public String getPath(Uri uri) {
        try {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();
            String file = cursor.getString(1);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    protected void replaceFragment(int containerViewId, String tag, Fragment fragment) {
        boolean isAdd = true;
        Fragment tempFragment = getSupportFragmentManager().findFragmentByTag(tag);
        if (tempFragment == null) {
            tempFragment = fragment;
            isAdd = false;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(containerViewId, tempFragment, tag);
        if (!isAdd) {
            transaction.addToBackStack(tag);
        }
        transaction.commitAllowingStateLoss();
    }

    public void showMessage(final Object message) {
        showToast(message);
    }

    private void showToast(final Object message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mToast == null) {
                    mToast = Toast.makeText(context, message + "", Toast.LENGTH_SHORT);
                } else {
                    mToast.setText(message + "");
                    mToast.setDuration(Toast.LENGTH_SHORT);
                }
                mToast.show();
            }
        });
    }

    protected abstract int getViewId();

    protected abstract void init();



}
