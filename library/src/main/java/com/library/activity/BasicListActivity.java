package com.library.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import com.library.R;
import com.library.widget.Divider;
import com.library.widget.refreshlayout.RefreshLayout;
import com.library.widget.refreshlayout.RefreshLayoutDirection;

/**
 * @author Young
 */
public abstract class BasicListActivity extends BasicActivity {

    protected Toolbar titleToll;
    protected RecyclerView listView;
    public RefreshLayout refreshLayout;
    public Button use;
    public TextView message;
    private int page = 1;

    @Override
    protected int getViewId() {
        return R.layout.activity_list_layout;
    }

    @Override
    protected void init() {
        use = (Button) findViewById(R.id.bottom_confirm);
        titleToll = (Toolbar) findViewById(R.id.title_toolbar);
        message = (TextView) findViewById(R.id.message);
        setToolbar(titleToll);
        refreshLayout = (RefreshLayout) findViewById(R.id.ref_layout);
        refreshLayout.setColorSchemeResources(R.color.main_color);
        refreshLayout.setOnRefreshListener(new RefreshLayout.OnRefreshListener() {
            @Override
            public void onPullDownToRefresh() {
                page = 1;
                loadData(page);
            }

            @Override
            public void onPullUpToRefresh() {
                page++;
                loadData(page);
            }
        });

        listView = (RecyclerView) findViewById(R.id.list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        listView.setLayoutManager(layoutManager);
        listView.setHasFixedSize(true);
        listView.setAdapter(getAdapter());
        if (isAddItemDecoration()) {
            listView.addItemDecoration(new Divider(context));
        }

    }

    public boolean isAddItemDecoration() {
        return true;
    }

    public abstract void loadData(int page);

    public abstract RecyclerView.Adapter getAdapter();

    public void onLoad(int size) {
        if (refreshLayout == null) {
            return;
        }
        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
        if (size < 10) {
            refreshLayout.setDirection(RefreshLayoutDirection.TOP);
        } else {
            refreshLayout.setDirection(RefreshLayoutDirection.BOTH);
        }
    }
}
