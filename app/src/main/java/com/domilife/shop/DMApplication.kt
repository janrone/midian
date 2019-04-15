package com.domilife.shop

import android.app.Application
import android.content.Context
import com.baidu.mapapi.CoordType
import com.baidu.mapapi.SDKInitializer
import com.mob.MobSDK
import kotlin.properties.Delegates
import com.umeng.commonsdk.UMConfigure
import com.umeng.socialize.PlatformConfig

/**
 * Created by janrone on 2019/3/19.
 */
class DMApplication : Application() {


    companion object {

        private val TAG = "DMApplication"

        var context: Context by Delegates.notNull()
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
        SDKInitializer.setHttpsEnable(true)

        UMConfigure.init(this,"5a12384aa40fa3551f0001d1"
                ,"umeng", UMConfigure.DEVICE_TYPE_PHONE,"")

        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0")
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad","http://sns.whalecloud.com")

        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba")

        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "")
    }
}