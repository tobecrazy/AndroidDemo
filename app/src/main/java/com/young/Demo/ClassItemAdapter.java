package com.young.Demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.library.adapter.BaseViewHolder;
import com.library.adapter.MutiRecyclerAdapter;
import com.young.Demo.widget.BannerLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by longbh on 16/7/15.
 */
public class ClassItemAdapter extends MutiRecyclerAdapter<String> {

    Context context;

    public ClassItemAdapter(List<String> datas, Context context) {
        super(datas);
        this.context = context;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_banner, parent, false);
            return new HeadViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_class_layout, parent, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? 0 : 1;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class HeadViewHolder extends BaseViewHolder<String> {

        @InjectView(R.id.banner_layout)
        BannerLayout bannerLayout;

        public HeadViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void build(String object, int position) {
            List<String> bannerUrls = new ArrayList<String>();

            bannerUrls.add("http://d6.yihaodianimg.com/N05/M0B/BA/12/ChEbulebKmuAIGKQAAHjllhJHus39800.jpg");
            bannerUrls.add("http://img30.360buyimg.com/da/jfs/t2983/297/2002289844/50960/e2971e62/5796d126Nb934d250.jpg");
            bannerUrls.add("http://img20.360buyimg.com/da/jfs/t2710/176/3413602650/131433/1c625fbc/578c9889N672d27a8.jpg");
            bannerUrls.add("http://img13.360buyimg.com/da/jfs/t2848/214/3282152582/48035/cf431448/578733ffN1d445722.jpg");
            bannerUrls.add("http://img12.360buyimg.com/da/jfs/t2827/259/3266026343/46191/9169b41b/5784c4adN4352af6c.jpg");
            bannerLayout.setViewUrls(bannerUrls);
        }
    }

    class ViewHolder extends BaseViewHolder<String> {

        @InjectView(R.id.flexbox_layout)
        FlexboxLayout flexboxLayout;
        @InjectView(R.id.flexbox_layout1)
        FlexboxLayout flexboxLayout1;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void build(String object, int position) {
            flexboxLayout.removeAllViews();
            flexboxLayout1.removeAllViews();
            String[] tea = new String[]{"龙井茶 ", "信阳毛类", "碧螺春 ", "黄山毛峰", "龙井茶 ", "碧螺春 ", "碧螺春 ", "黄山毛峰", "龙井茶 ", "信阳毛类", "碧螺春 ", "黄山毛峰"};
            for (int i = 0; i < tea.length; i++) {
                View view = LayoutInflater.from(context).inflate(R.layout.item_textview, null);
                TextView textView = (TextView) view.findViewById(R.id.content);
                textView.setText(tea[i]);
                flexboxLayout.addView(view);
            }
            String[] fishes = new String[]{"草鱼", "鲤鱼", "鲫鱼", "带鱼", "黄鱼", "鲈鱼", "鳕鱼", "墨鱼", "鲅鱼", "金枪鱼",
                    "鲢鱼", "青鱼", "桂鱼", "鲳鱼", "鲶鱼"};
            for (int i = 0; i < fishes.length; i++) {
                View view = LayoutInflater.from(context).inflate(R.layout.item_textview, null);
                TextView textView = (TextView) view.findViewById(R.id.content);
                textView.setText(fishes[i]);
                flexboxLayout1.addView(view);
            }
        }
    }

}
