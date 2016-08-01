package com.young.Demo;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.library.utils.GlideUtil;

import java.util.List;

/**
 * Created by longbh on 16/7/7.
 */
class ImageAdapter extends PagerAdapter {

    public List<String> datas;
    private Context context;

    public ImageAdapter(List<String> datas, Context context){
        this.datas = datas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)   {
       container.removeView((View) object);//删除页卡
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {  //这个方法用来实例化页卡
        ImageView iv = new ImageView(context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        iv.setLayoutParams(params);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);// 图片按等比缩放
        GlideUtil.loadPicture(datas.get(position),iv, R.drawable.default_image);
        container.addView(iv);
        return iv;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}

