package com.pos.posscan;

import android.app.Application;
import android.content.Context;

/**
 * @author Administrator
 * @date 创建时间 2018/4/18
 * @Description
 */

public class App extends Application {

    static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }


    public static Context getContext() {
        return instance;
    }
}
