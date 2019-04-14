package com.domilife.shop.net

import io.reactivex.Observable
import okhttp3.MediaType
import retrofit2.http.*
import okhttp3.MultipartBody
import retrofit2.http.POST
import retrofit2.http.Multipart
import okhttp3.RequestBody
import okhttp3.ResponseBody







/**
 * Created by janrone on 2019/3/19.
 */
interface ApiManager {

    //shopsmslogin?phone={phone}&smsCode={smsCode}
    @GET("learn")
    fun shopsmslogin(@Query("action") action:String ,
            @Query("phone") phone:String, @Query("smsCode") smsCode: String): Observable<BaseResponse> //shopsmslogin?phone={phone}&smsCode={smsCode}

    @GET("learn")
    fun shoppwdlogin(@Query("action") action:String ,
            @Query("phone") phone:String, @Query("pwd") pwd: String): Observable<BaseResponse>

    //bae
    @GET("learn")
    fun baseRequest(@Query("action") action:String ,
                     @Query("phone") phone:String, @Query("smsCode") smsCode: String): Observable<BaseResponse>
    //bae
    @POST("learn")
    fun baseRequest(@QueryMap param: Map<String, String>): Observable<BaseResponse>

    //bae
    @FormUrlEncoded
    @POST("learn")
    fun baseRequestPost(@FieldMap params: Map<String, String>): Observable<BaseResponse>

    @JvmSuppressWildcards
    @Multipart
    @POST("learn")
    fun baseRequestUpload(@QueryMap qparam: Map<String, String>, @PartMap params: Map<String, RequestBody> ,
                          @Part parts: List<MultipartBody.Part>): Observable<BaseResponse>


}