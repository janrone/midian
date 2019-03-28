package com.domilife.shop.activity

import android.content.Intent
import android.os.Bundle
import com.domilife.shop.R
import com.domilife.shop.base.BaseActivity
import kotlinx.android.synthetic.main.activity_shop_info.*


class ShopInfoActivity : BaseActivity() {


    override fun layoutId(): Int {
        return R.layout.activity_shop_info
    }

    override fun initView() {
        toolbar?.setNavigationOnClickListener { finish() }

        ll_shop_info.setOnClickListener {
            startActivity(Intent(this, MapControlActivity::class.java))
        }

        ll_2.setOnClickListener {
            startActivityForResult(Intent(this, ShopCategoryActivity::class.java),1)
        }

    }


    override fun initData() {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1){
            tv_kind.setText(data?.getStringExtra("kind"))
            et_ser.setText(data?.getStringExtra("serRate"))
        }
    }




    //private var mLoginType = 0
    //var mLoadingDialog: LoadingDialog? = null



 }