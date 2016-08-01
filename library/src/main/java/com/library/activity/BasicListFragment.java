package com.library.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.library.R;
import com.library.widget.Divider;
import com.library.widget.refreshlayout.RefreshLayout;

/**
 * Created by longbh on 16/5/31.
 */
public abstract class BasicListFragment extends BasicFragment {
    protected RecyclerView listView;
    protected RefreshLayout refreshLayout;

    protected int page = 1;

    @Override
    protected int getViewId() {
        return R.layout.fragment_list_layout;
    }

    @Override
    protected void init() {
        listView = (RecyclerView) rootView.findViewById(R.id.list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        listView.setLayoutManager(layoutManager);
        listView.setHasFixedSize(true);
        listView.setAdapter(getAdapter());
        if (isAddItemDecoration()) {
            listView.addItemDecoration(new Divider(context));
        }

        refreshLayout = (RefreshLayout) rootView.findViewById(R.id.ref_layout);
        loadData(page);
        refreshLayout.setOnRefreshListener(new RefreshLayout.OnRefreshListener() {
            @Override
            public void onPullDownToRefresh() {
                page = 1;
                context.loading.show();
                loadData(page);
            }

            @Override
            public void onPullUpToRefresh() {
                page++;
                loadData(page);
            }
        });
    }

    protected void onLoad() {
        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
        context.loading.dismiss();
    }

    public abstract void loadData(int page);

    public abstract RecyclerView.Adapter getAdapter();

    protected boolean isAddItemDecoration() {
        return true;
    }
}
