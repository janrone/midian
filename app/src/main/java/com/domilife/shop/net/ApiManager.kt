package com.domilife.shop.net

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * Created by janrone on 2019/3/19.
 */
interface ApiManager {

    //shopsmslogin?phone={phone}&smsCode={smsCode}
    @GET("learn")
    fun shopsmslogin(@Query("action") action:String ,
            @Query("phone") phone:String, @Query("smsCode") smsCode: String): Observable<BaseResponse>

    //bae
    @GET("learn")
    fun baseRequest(@Query("action") action:String ,
                     @Query("phone") phone:String, @Query("smsCode") smsCode: String): Observable<BaseResponse>
    //bae
    @GET("learn")
    fun baseRequest(@QueryMap param: Map<String, String>): Observable<BaseResponse>
}