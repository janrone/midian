package com.domilife.shop.activity

import android.content.Intent
import android.os.Bundle
import com.domilife.shop.MainActivity
import com.domilife.shop.R
import com.domilife.shop.base.BaseActivity
import com.domilife.shop.bean.InCodeBean
import com.domilife.shop.bean.InviteInfoBean
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.activity_shop_info_all.*
import java.util.concurrent.TimeUnit


class ShopInfoMainActivity : BaseActivity() {


    override fun layoutId(): Int {
        return R.layout.activity_shop_info_all
    }

    override fun initView() {
        toolbar?.setNavigationOnClickListener { finish() }

    }

    override fun initData() {

        val inCodeBean = intent.getParcelableExtra<InCodeBean>("data")

        if(inCodeBean?.isCompQuality == 1) {
            ll_shop_info.isClickable = false
            iv_shop_info.text = "以完善"

        }
        if(inCodeBean?.isCompShopMsg == 1) {
            ll_zz.isClickable = false
            tv_zz.text = "以完善"
        }

        RxView.clicks(ll_zz).throttleFirst(3, TimeUnit.SECONDS)
                .subscribe {
                    startActivity(Intent(this, ShopInfoAptitudeActivity::class.java))
                }

        RxView.clicks(ll_shop_info).throttleFirst(3, TimeUnit.SECONDS)
                .subscribe {
                    startActivity(Intent(this, ShopInfoActivity::class.java))
                }

        RxView.clicks(tv_skip).throttleFirst(3, TimeUnit.SECONDS)
                .subscribe {
                    startActivity(Intent(this, MainActivity::class.java))
                }

    }




    //private var mLoginType = 0
    //var mLoadingDialog: LoadingDialog? = null



 }