package com.young.Demo;

import android.support.v7.widget.RecyclerView;

import com.library.activity.BasicListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by longbh on 16/7/15.
 */
public class ItemClassFragment extends BasicListFragment {

    private List<String> datas;

    @Override
    public void loadData(int page) {

    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        datas = new ArrayList<>();
        datas.add("test");
        datas.add("test");
        datas.add("test");
        datas.add("test");
        return new ClassItemAdapter(datas, getActivity());
    }
}
