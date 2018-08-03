package com.github.baseapp.base;

import android.app.Application;
import android.content.Context;

/**
 * @data 2018-08-03
 * @desc
 */

public class App extends Application{

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext(){
        return mContext;
    }


}
