package com.young.Demo;

import android.os.Bundle;

import com.library.activity.BasicActivity;

/**
 * Created by longbh on 16/7/19.
 */
public class SecondActivity extends BasicActivity{
    @Override
    protected void onGetBundle(Bundle bundle) {

    }

    @Override
    protected int getViewId() {
        return R.layout.activity_fragment_layout;
    }

    @Override
    protected void init() {
        replaceFragment(R.id.content,"class",new ClassFragment());
    }

    public void onBackPressed(){
        finish();
    }
}
