package com.pos.posscan.api;

import com.pos.posscan.bean.PosPayNotifyFeed;
import com.pos.posscan.bean.PosPayNotifyPoJo;
import com.pos.posscan.bean.PrePayFeed;
import com.pos.posscan.bean.ScanPoJo;

import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;

/**
 * @author Administrator
 * @date 创建时间 2018/3/18
 * @Description
 */

public interface RetrofitInterface {


    @Headers({"Content-Type:application/json", "x:this_is_for_test_only", "projectguid:1"})
    @POST("propertyapi/pos/prePay")
    Observable<PrePayFeed> prePay(@Body ScanPoJo scanPoJo);

    @Headers({"Content-Type:application/json", "x:this_is_for_test_only", "projectguid:1"})
    @POST
    Observable<PosPayNotifyFeed> pospayNotify(@Url String url, @Body PosPayNotifyPoJo posPayNotifyPoJo);

}
