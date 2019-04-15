package com.domilife.shop.activity

import com.domilife.shop.R
import com.domilife.shop.base.BaseActivity
import kotlinx.android.synthetic.main.activity_zb_detail.*

class ZDdetailActivity : BaseActivity() {


    override fun layoutId(): Int {
        return R.layout.activity_zb_detail
    }

    override fun initView() {
        toolbar?.setNavigationOnClickListener { finish() }
    }

    override fun initData() {
        tv_header_title.setText("")
    }


 }