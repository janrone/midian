package com.domilife.shop.net

import com.google.gson.JsonObject

/**
 * Created by janrone on 2019/3/19.
 * 封装返回的数据
 */
data class BaseResponse(val code: Int,
                        val msg: String,
                        var data: JsonObject){
    override fun toString(): String {
        return super.toString()
    }
}