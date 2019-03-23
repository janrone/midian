package com.domilife.shop.activity

import android.content.Intent
import android.os.Bundle
import com.domilife.shop.R
import com.domilife.shop.base.BaseActivity
import kotlinx.android.synthetic.main.activity_shop_info.*


class ShopInfoActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initData()

    }

    override fun layoutId(): Int {
        return R.layout.activity_shop_info
    }

    override fun initView() {
        toolbar?.setNavigationOnClickListener { finish() }

        ll_shop_info.setOnClickListener {
            startActivity(Intent(this, MapControlActivity::class.java))
        }

    }


    override fun initData() {

    }




    //private var mLoginType = 0
    //var mLoadingDialog: LoadingDialog? = null



 }