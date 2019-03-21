package com.domilife.shop.activity

import android.content.Intent
import android.os.Bundle
import com.domilife.shop.R
import com.domilife.shop.base.BaseActivity
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.activity_shop_info_all.*
import java.util.concurrent.TimeUnit


class ShopInfoMainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initData()

    }

    override fun layoutId(): Int {
        return R.layout.activity_shop_info_all
    }

    override fun initView() {
        toolbar?.setNavigationOnClickListener { finish() }

    }

    override fun initData() {

        RxView.clicks(ll_zz).throttleFirst(3, TimeUnit.SECONDS)
                .subscribe {
                    startActivity(Intent(this, ShopInfoAptitudeActivity::class.java))
                }

        RxView.clicks(ll_shop_info).throttleFirst(3, TimeUnit.SECONDS)
                .subscribe {
                    startActivity(Intent(this, ShopInfoActivity::class.java))
                }

    }




    //private var mLoginType = 0
    //var mLoadingDialog: LoadingDialog? = null



 }