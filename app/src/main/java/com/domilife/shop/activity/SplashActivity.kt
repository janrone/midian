package com.domilife.shop.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.domilife.shop.MainActivity
import com.domilife.shop.R
import com.domilife.shop.base.BaseActivity
import com.domilife.shop.bean.InCodeBean
import com.domilife.shop.utils.Preference
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_home.*


class SplashActivity : BaseActivity() {


    override fun layoutId(): Int {
        return R.layout.activity_login
    }

    override fun initView() {

    }

    override fun initData() {
        if (Preference.contains("incodebean")) {
            startActivity(Intent(this, MainActivity::class.java))
        }else{
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }




    //private var mLoginType = 0
    //var mLoadingDialog: LoadingDialog? = null



 }