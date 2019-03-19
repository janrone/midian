package com.midian.shop.net

import org.json.JSONObject

/**
 * Created by janrone on 2019/3/19.
 * 封装返回的数据
 */
data class BaseResponse(val code :Int,
                      val msg:String,
var data:JSONObject)