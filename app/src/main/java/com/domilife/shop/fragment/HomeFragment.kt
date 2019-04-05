package com.domilife.shop.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.domilife.shop.R
import com.domilife.shop.activity.*
import com.domilife.shop.base.BaseFragment
import com.domilife.shop.bean.InCodeBean
import com.domilife.shop.utils.Preference
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by janrone on 2019/3/16.
 */
class HomeFragment : BaseFragment() {


    private var mTitle: String? = null

    var cannext by Preference("cannext", false)

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

            if(canNext())
                startActivity(Intent(activity, JingyingFenxiActivity::class.java))
        }

        ll_product.setOnClickListener {

            if(canNext())
                startActivity(Intent(activity, ProductShelfActivity::class.java))
        }

        ll_member.setOnClickListener {

            if(canNext())
                startActivity(Intent(activity, MemberActivity::class.java))
        }

        ll_yingxiao.setOnClickListener {

            if(canNext())
                startActivity(Intent(activity, YingXiaoActivity::class.java))
        }

        if (Preference.contains("incodebean")) {

            var incodebean by Preference("incodebean", "")
            var inCodeBean:InCodeBean= Gson().fromJson(incodebean,InCodeBean::class.java)

            var cannext by Preference("cannext", false)

            if (inCodeBean?.isCompQuality != 1 || inCodeBean?.isCompShopMsg != 1) {
                tv_bot2.visibility = View.VISIBLE
                cannext = false
            } else {
                tv_bot2.visibility = View.GONE
                cannext = true
            }

        }
    }

    private fun canNext(): Boolean{
        if(!cannext){
            activity?.toast("开店资料未提交，点击继续提交")
            //return false
            return true
        }
        return true
    }

}

