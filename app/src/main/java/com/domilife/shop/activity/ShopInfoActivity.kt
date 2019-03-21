package com.domilife.shop.activity

import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.View
import com.domilife.shop.Constants
import com.domilife.shop.R
import com.domilife.shop.base.BaseActivity
import kotlinx.android.synthetic.main.activity_shop_info_zz.*


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

    }

    private fun setDateInput(i: Int){
        if(i== radio0.id){
            rl_to_date.visibility = View.VISIBLE
        }else{
            rl_to_date.visibility = View.GONE
        }
    }

    override fun initData() {

    }




    //private var mLoginType = 0
    //var mLoadingDialog: LoadingDialog? = null



 }