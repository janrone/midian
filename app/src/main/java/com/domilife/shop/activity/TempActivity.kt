package com.domilife.shop.activity

import android.os.Bundle
import com.domilife.shop.R
import com.domilife.shop.base.BaseActivity


class TempActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initData()

    }

    override fun layoutId(): Int {
        return R.layout.activity_login
    }

    override fun initView() {

    }

    override fun initData() {

    }




    //private var mLoginType = 0
    //var mLoadingDialog: LoadingDialog? = null



 }