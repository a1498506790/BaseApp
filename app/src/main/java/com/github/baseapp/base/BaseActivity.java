package com.github.baseapp.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import com.github.baseapp.utils.ActivityStack;
import com.github.baseapp.utils.MPermissionUtils;

import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * @data 2018-08-03
 * @desc
 */

public abstract class BaseActivity extends SwipeBackActivity{

    protected Activity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //设置activity 屏幕的方向
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //设置键盘默认是不弹出
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        //隐藏ActionBar
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        mContext = this;
        //设置布局
        setContentView(getLayoutRes());
        ButterKnife.bind(this);
        onCreateActivity(savedInstanceState);
        ActivityStack.getInstance().pushActivity(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    //获取布局
    protected abstract int getLayoutRes();
    protected abstract void onCreateActivity(@Nullable Bundle savedInstanceState);

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MPermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityStack.getInstance().popActivity(this);
    }

}
