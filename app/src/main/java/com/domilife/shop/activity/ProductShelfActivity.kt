package com.domilife.shop.activity

import android.content.Intent
import com.domilife.shop.R
import com.domilife.shop.base.BaseActivity
import kotlinx.android.synthetic.main.activity_prodoct_shelf.*

class ProductShelfActivity : BaseActivity() {


    override fun layoutId(): Int {
        return R.layout.activity_prodoct_shelf
    }

    override fun initView() {
        toolbar?.setNavigationOnClickListener { finish() }
    }

    override fun initData() {
        rl_product.setOnClickListener {
            startActivity(Intent(this@ProductShelfActivity, ProductActivity::class.java))
        }

        ll_shop_info.setOnClickListener {
            startActivity(Intent(this@ProductShelfActivity, OrderActivity::class.java))
        }

        rl_share.setOnClickListener {

        }
    }


 }