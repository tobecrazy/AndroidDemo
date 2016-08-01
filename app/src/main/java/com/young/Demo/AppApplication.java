package com.young.Demo;

import android.app.Application;

import com.library.utils.GlideUtil;

/**
 * Created by longbh on 16/5/24.
 */
public class AppApplication extends Application {

    public void onCreate() {
        super.onCreate();
        //图片框架初始化
        GlideUtil.init(this);
    }

}
