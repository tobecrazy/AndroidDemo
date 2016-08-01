package com.library.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> list_fragments;
    private List<String> titles = new ArrayList<>();

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public ViewPagerAdapter(FragmentManager fm,
                            List<Fragment> list_fragments) {
        super(fm);
        if (list_fragments == null) {
            this.list_fragments = new ArrayList<Fragment>();
        } else {
            this.list_fragments = list_fragments;
        }
    }

    @Override
    public Fragment getItem(int arg0) {
        return list_fragments.get(arg0);
    }

    @Override
    public int getCount() {
        return list_fragments.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (titles.size() > 0) {
            return titles.get(position);
        }
        return "";
    }

    public void addTitle(String title) {
        titles.add(title);
    }

    public void updateTitle(String title, int position) {
        titles.set(position, title);
    }
}
