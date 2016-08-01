package com.young.Demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioGroup;


import com.library.activity.BasicActivity;

public class MainActivity extends BasicActivity {

    public static MainActivity instance;
    public static int type = 0;//用于首页切换到产品的fragment

    public static RadioGroup mTabMenuRg;

    private static final String TAG_MAIN = "main";
    private static final String TAG_CROWDFUNDING = "crowdfunding";
    private static final String TAG_INFOMATION = "infomation";
    private static final String TAG_PRODUCT = "product";
    private static final String TAG_USER_CENTER = "user_center";

    private Fragment mMainFragment, mCrowdfundingFragment, mInfomationFragment, mUserCenterFragment, mProductFragment;

    @Override
    protected int getViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        mTabMenuRg = (RadioGroup) findViewById(R.id.tab_menu_rg);
        mMainFragment = new MainFragment();
        mCrowdfundingFragment = new ClassFragment();
        mInfomationFragment = new ClassFragment();
        mProductFragment = new ClassFragment();
        mUserCenterFragment = new ClassFragment();
        replaceFragment(mMainFragment, TAG_MAIN);

        mTabMenuRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_rb:
                        replaceFragment(mMainFragment, TAG_MAIN);
                        break;
                    case R.id.crowdfunding_rb:
                        replaceFragment(mCrowdfundingFragment, TAG_CROWDFUNDING);
                        break;
                    case R.id.mine_rb:
                        replaceFragment(mUserCenterFragment, TAG_USER_CENTER);
                        break;
                }
            }
        });

        instance = this;
    }

    @Override
    protected void onGetBundle(Bundle bundle) {

    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

    }

    private Fragment currentFragment;

    /**
     * 用tempFragment替代当前Fragment, 并给tempFragment增加一个tag，以便下次调用，不用新建
     *
     * @param tag
     * @param fragment 设定文件
     */
    private void replaceFragment(Fragment fragment, String tag) {
        currentFragment = fragment;
        boolean isAdd = true;
        Fragment tempFragment = getSupportFragmentManager().findFragmentByTag(tag);
        if (tempFragment == null) {
            tempFragment = fragment;
            isAdd = false;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container_rl, tempFragment, tag);
        if (!isAdd) {
            transaction.addToBackStack(tag);
        }
        transaction.commitAllowingStateLoss();
    }

    private long lastTimePressed = 0;

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - lastTimePressed < 1000) {
            finishSimple();
        } else {
            lastTimePressed = System.currentTimeMillis();
            showMessage(getString(R.string.press_again_to_exit));
        }
    }

    public void toSecond(View view){
        startActivity(null,SecondActivity.class);
    }

}
