package com.github.baseapp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.github.baseapp.base.BaseActivity;
import com.github.baseapp.utils.DialogUtils;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreateActivity(@Nullable Bundle savedInstanceState) {
        DialogUtils.showEditDialog(mContext, "手机号", "输入手机号", new DialogUtils.OnEdtDialogClickListener() {
            @Override
            public void onEditDialog(String content) {

            }
        });
    }
}
