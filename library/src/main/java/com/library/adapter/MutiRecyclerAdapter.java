package com.library.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author Young
 */
public abstract class MutiRecyclerAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    protected List<T> datas;

    public MutiRecyclerAdapter(List<T> datas) {
        super();
        this.datas = datas;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.build(datas.get(position), position);
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
