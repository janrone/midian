package com.domilife.shop.activity

import android.content.Intent
import com.domilife.shop.R
import com.domilife.shop.base.BaseActivity
import kotlinx.android.synthetic.main.activity_yingxiao.*

class YingXiaoActivity : BaseActivity() {


    override fun layoutId(): Int {
        return R.layout.activity_yingxiao
    }

    override fun initView() {
        toolbar?.setNavigationOnClickListener { finish() }
    }

    override fun initData() {

        ll_bk.setOnClickListener {
            startActivity(Intent(this@YingXiaoActivity, ProductShelfActivity::class.java),false)
        }

        ll_hd.setOnClickListener {
            startActivity(Intent(this@YingXiaoActivity, HuodongActivity::class.java),false)
        }

        ll_zk.setOnClickListener {
            startActivity(Intent(this@YingXiaoActivity, HuodongActivity::class.java),false)
        }

        ll_hb.setOnClickListener {
            toast("功能正在开发中，尽请期待！")
        }


    }



 }