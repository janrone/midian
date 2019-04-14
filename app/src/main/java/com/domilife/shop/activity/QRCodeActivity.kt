package com.domilife.shop.activity

import com.domilife.shop.R
import com.domilife.shop.base.BaseActivity
import kotlinx.android.synthetic.main.activity_qrcode.*

class QRCodeActivity : BaseActivity() {


    override fun layoutId(): Int {
        return R.layout.activity_qrcode
    }

    override fun initView() {
        toolbar?.setNavigationOnClickListener { finish() }
    }

    override fun initData() {

    }


 }