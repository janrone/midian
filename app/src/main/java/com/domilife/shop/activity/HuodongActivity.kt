package com.domilife.shop.activity

import android.support.v7.widget.LinearLayoutManager
import com.domilife.shop.R
import com.domilife.shop.adapter.OrderProductAdapter
import com.domilife.shop.base.BaseActivity
import kotlinx.android.synthetic.main.activity_yingxiao.*

class HuodongActivity : BaseActivity() {


    override fun layoutId(): Int {
        return R.layout.activity_huodong
    }

    override fun initView() {
        toolbar?.setNavigationOnClickListener { finish() }
    }

    override fun initData() {

//        tv_express.setOnClickListener {
//            startActivity(Intent(this@OrderDetailActivity, ExpressActivity::class.java))
//        }

    }


 }