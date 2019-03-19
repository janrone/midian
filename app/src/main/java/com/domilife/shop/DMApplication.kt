package com.domilife.shop

import android.app.Application
import android.content.Context
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
    }
}