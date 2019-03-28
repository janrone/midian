package com.domilife.shop.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.domilife.shop.view.LoadingDialog

/**
 * Created by janrone on 2019/3/16.
 */
abstract class BaseActivity : AppCompatActivity(){
    protected var mLoadingDialog:LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        mLoadingDialog = LoadingDialog(this)
        initView()
        initData()
        //start()
        //initListener()
    }

    /**
     *  加载布局
     */
    abstract fun layoutId(): Int

    abstract fun initView()

    abstract fun initData()

    fun Context.toast(message:CharSequence)= Toast.makeText(this,message, Toast.LENGTH_LONG).show()


}