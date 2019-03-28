package com.domilife.shop.fragment

import android.content.Intent
import android.os.Bundle
import com.domilife.shop.R
import com.domilife.shop.activity.JingyingFenxiActivity
import com.domilife.shop.activity.ShopInfoMainActivity
import com.domilife.shop.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by janrone on 2019/3/16.
 */
class HomeFragment: BaseFragment() {


    private var mTitle :String? = null

    companion object {
        fun getInstance(title: String): HomeFragment {
            val fragment = HomeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {

        tv_bot2.setOnClickListener {
            startActivity(Intent(activity, ShopInfoMainActivity::class.java))
        }

        ll_jyfx.setOnClickListener {
            startActivity(Intent(activity, JingyingFenxiActivity::class.java))
        }

    }
}

