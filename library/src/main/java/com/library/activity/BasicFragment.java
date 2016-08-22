package com.library.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BasicFragment extends Fragment {

    protected BasicActivity context;
    protected View rootView;
    public Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = (BasicActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = setContentView(inflater, getViewId());
        unbinder =ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 调用该办法可避免重复加载UI
     */
    public View setContentView(LayoutInflater inflater, int resId) {
        if (rootView == null) {
            rootView = inflater.inflate(resId, null);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    protected boolean onBackKeyPressed() {
        return false;
    }

    protected void finish() {
        context.finish();
    }

    protected void finishSimple() {
        context.finish();
    }

    public void finishResult(Intent intene) {
        context.finishResult(intene);
    }

    public void finishResult() {
        context.finishResult();
    }

    public void startActivity(Bundle bundle, Class<?> target) {
        context.startActivity(bundle, target);
    }

    public void startForResult(Bundle bundle, int requestCode, Class<?> target) {
        Intent intent = new Intent(context, target);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    public void showMessage(final Object message) {
        context.showMessage(message);
    }

    protected abstract int getViewId();

    protected abstract void init();


}
