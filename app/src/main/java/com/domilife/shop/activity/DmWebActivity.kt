package com.domilife.shop.activity

import com.domilife.shop.R
import com.domilife.shop.base.BaseActivity
import kotlinx.android.synthetic.main.activity_web.*

class DmWebActivity : BaseActivity() {


    override fun layoutId(): Int {
        return R.layout.activity_web
    }

    override fun initView() {
        web_view.loadUrl(intent?.getStringExtra("url"))
    }

    override fun initData() {
        //web_view.loadUrl()
    }


 }