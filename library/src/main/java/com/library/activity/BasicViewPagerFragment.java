package com.library.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.library.R;
import com.library.adapter.ViewPagerAdapter;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
*@author Young
 */
public class BasicViewPagerFragment extends BasicFragment{
    Toolbar titleToolbar;
    protected TabPageIndicator indicator;
    protected ViewPager viewPager;

    protected List<Fragment> fragmentList;
    protected ViewPagerAdapter adapter;

    @Override
    protected int getViewId() {
        return R.layout.activity_viewpager_layout;
    }

    @Override
    protected void init() {
        titleToolbar = (Toolbar) rootView.findViewById(R.id.title_toolbar);
        context.setToolbar(titleToolbar);
        indicator = (TabPageIndicator) rootView.findViewById(R.id.indicator);
        viewPager = (ViewPager) rootView.findViewById(R.id.view_pager);

        fragmentList = new ArrayList<>();
        adapter = new ViewPagerAdapter(getChildFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
    }

    public void addItem(String title, Fragment fragment) {
        fragmentList.add(fragment);
        adapter.addTitle(title);
    }

    public void notifyDataSetChanged() {
        adapter.notifyDataSetChanged();
        indicator.notifyDataSetChanged();
    }
}
