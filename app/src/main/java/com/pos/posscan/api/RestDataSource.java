package com.pos.posscan.api;


import android.util.Log;

import com.pos.posscan.AppConfig;
import com.pos.posscan.bean.PosPayNotifyFeed;
import com.pos.posscan.bean.PosPayNotifyPoJo;
import com.pos.posscan.bean.PrePayBean;
import com.pos.posscan.bean.PrePayFeed;
import com.pos.posscan.bean.ScanPoJo;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Administrator
 * @date 创建时间 2018/4/8
 * @Description
 */

public class RestDataSource {

    private static RetrofitInterface instance;

    private static RestDataSource restDataSource;

    private String BASE_URL = "http://remeberme.mynatapp.cc/";
    OkHttpClient client;

    private void init() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.i("RetrofitLog","retrofitBack = "+message);
            }
        });


        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client= new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        instance = retrofit.create(RetrofitInterface.class);
    }

    public static RetrofitInterface getAPIService() {
        if (restDataSource == null) {
            restDataSource = new RestDataSource();
        }

        if (instance == null) {
            restDataSource.init();
        }

        return instance;
    }

    public static void scanRequest(Observer<PrePayFeed> observer) {
        setSubscribe(getAPIService().prePay(new ScanPoJo("test",AppConfig.HEADERX)), observer);
    }

    public static void notify(PrePayBean prePayBean,PosPayNotifyPoJo posPayNotifyPoJo, Observer<PosPayNotifyFeed> observer) {
        setSubscribe(getAPIService().pospayNotify(prePayBean.getCbUrl(),posPayNotifyPoJo), observer);
    }



    /**
     * 插入观察者
     *
     * @param observable
     * @param observer
     * @param <T>
     */
    public static <T> void setSubscribe(Observable<T> observable, Observer<T> observer) {

        observable.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())//子线程访问网络
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(observer);
    }
}
