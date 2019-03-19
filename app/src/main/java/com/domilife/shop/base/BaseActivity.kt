package com.midian.shop.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by janrone on 2019/3/16.
 */
abstract class BaseActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        //initData()
        //initView()
        //start()
        //initListener()


    }

    /**
     *  加载布局
     */
    abstract fun layoutId(): Int


//    /**
//     * 初始化数据
//     */
//    abstract fun initData()
//
//    /**
//     * 初始化 View
//     */
//    abstract fun initView()

}