package com.young.Demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.library.activity.BasicFragment;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by longbh on 16/7/7.
 */
public class ClassFragment extends BasicFragment {

    @InjectView(R.id.indicator)
    TabPageIndicator indicator;
    @InjectView(R.id.view_pager)
    ViewPager viewPager;
    @InjectView(R.id.back_btn)
    ImageView backBtn;

    private List<String> datas;
    FragmentPagerAdapter adapter;
    private boolean iscreate = false;

    @Override
    protected int getViewId() {
        return R.layout.fragment_class_layout;
    }

    @Override
    protected void init() {

        if (!iscreate) {
            datas = new ArrayList<>();
            datas.add("茶叶类");
            datas.add("休闲、罐装食品类");
            datas.add("饮品类");
            datas.add("果品类");
            datas.add("绿色、保健类");
            adapter = new TabPageIndicatorAdapter(getChildFragmentManager(), datas);
            iscreate = true;
        }

        //ViewPager的adapter
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        //实例化TabPageIndicator然后设置ViewPager与之关联
        indicator.setViewPager(viewPager);
        //如果我们要对ViewPager设置监听，用indicator设置就行了
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * ViewPager适配器
     */
    class TabPageIndicatorAdapter extends FragmentPagerAdapter {

        List<String> datas;

        public TabPageIndicatorAdapter(FragmentManager fm, List<String> datas) {
            super(fm);
            this.datas = datas;
        }

        @Override
        public Fragment getItem(int position) {
            //新建一个Fragment来展示ViewPager item的内容，并传递参数
            Fragment fragment = new ItemClassFragment();
            Bundle args = new Bundle();
            args.putSerializable("id", datas.get(position));
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return datas.get(position % datas.size());
        }

        @Override
        public int getCount() {
            return datas.size();
        }
    }

}
