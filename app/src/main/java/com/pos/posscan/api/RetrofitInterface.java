package com.pos.posscan.api;

import android.database.Observable;


import com.pos.posscan.bean.PrePayFeed;

import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * @author Administrator
 * @date 创建时间 2018/3/18
 * @Description
 */

public interface RetrofitInterface {


    @Headers("Content-Type:application/json")
    @POST("/propertyapi/pos/prePay")
    Observable<PrePayFeed> prePay();

    @Headers("Content-Type:application/json")
    @POST("/propertyapi/pos/prePay")
    Observable<PrePayFeed> pospayNotify();

}
