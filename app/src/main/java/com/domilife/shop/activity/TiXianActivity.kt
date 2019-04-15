package com.domilife.shop.activity

import android.content.Intent
import com.domilife.shop.R
import com.domilife.shop.base.BaseActivity
import kotlinx.android.synthetic.main.activity_tixian.*

class TiXianActivity : BaseActivity() {


    override fun layoutId(): Int {
        return R.layout.activity_tixian
    }

    override fun initView() {
        toolbar?.setNavigationOnClickListener { finish() }
    }

    override fun initData() {

        rl_card.setOnClickListener {
            startActivity(Intent(this, AddCardActivity::class.java),false)
        }
    }

 }