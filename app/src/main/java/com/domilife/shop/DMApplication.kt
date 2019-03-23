package com.domilife.shop

import android.app.Application
import android.content.Context
import com.baidu.mapapi.CoordType
import com.baidu.mapapi.SDKInitializer
import com.mob.MobSDK
import kotlin.properties.Delegates

/**
 * Created by janrone on 2019/3/19.
 */
class DMApplication : Application() {


    companion object {

        private val TAG = "DMApplication"

        var context: Context by Delegates.notNull()
            private set

//        fun getRefWatcher(context: Context): RefWatcher? {
//            val myApplication = context.applicationContext as MyApplication
//            return myApplication.refWatcher
//        }

    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        MobSDK.init(this)

        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        SDKInitializer.initialize(this)
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL)
    }
}