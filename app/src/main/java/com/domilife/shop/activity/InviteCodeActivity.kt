package com.domilife.shop.activity

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import com.domilife.shop.R
import com.domilife.shop.base.BaseActivity
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.activity_code.*
import java.util.concurrent.TimeUnit


class InviteCodeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initData()

    }

    override fun layoutId(): Int {
        return R.layout.activity_code
    }

    override fun initView() {
        toolbar.setNavigationOnClickListener { finish() }
        tv_no_code.paint.flags = Paint.UNDERLINE_TEXT_FLAG //下划线



    }

    override fun initData() {

        RxView.clicks(tv_next).throttleFirst(3, TimeUnit.SECONDS)
                .subscribe {
                    startActivity(Intent(this, ShopInfoMainActivity::class.java))
                }

    }




    //private var mLoginType = 0
    //var mLoadingDialog: LoadingDialog? = null



 }