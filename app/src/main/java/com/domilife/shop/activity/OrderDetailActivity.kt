package com.domilife.shop.activity

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.domilife.shop.R
import com.domilife.shop.adapter.OrderProductAdapter
import com.domilife.shop.base.BaseActivity
import kotlinx.android.synthetic.main.activity_order_detail.*

class OrderDetailActivity : BaseActivity() {


    override fun layoutId(): Int {
        return R.layout.activity_order_detail
    }

    override fun initView() {
        toolbar?.setNavigationOnClickListener { finish() }
    }

    override fun initData() {

        tv_express.setOnClickListener {
            startActivity(Intent(this@OrderDetailActivity, ExpressActivity::class.java))
        }

        setProductData()
    }


    private var mList: ArrayList<String>? = null
    private var mAdaper: OrderProductAdapter? = null

    fun setProductData(){
        mList = ArrayList()
        mList!!.add("1111")
        mList!!.add("aaaa")
        mList!!.add("1111")
        mList!!.add("aaaa")
        mList!!.add("1111")
        mList!!.add("aaaa")

        rv_list.layoutManager = LinearLayoutManager(this)
        mAdaper = OrderProductAdapter(this@OrderDetailActivity, mList)
        rv_list.adapter = mAdaper;

    }


 }