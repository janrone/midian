package com.domilife.shop.fragment

import android.content.Intent
import android.os.Bundle
import com.domilife.shop.R
import com.domilife.shop.activity.EditPwdActivity
import com.domilife.shop.activity.ShopInfoMainActivity
import com.domilife.shop.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_mine.*

/**
 * Created by janrone on 2019/3/16.
 */
class MineFragment : BaseFragment() {

    private var mTitle :String? = null

    companion object {
        fun getInstance(title: String): MineFragment {
            val fragment = com.domilife.shop.fragment.MineFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_mine
    }

    override fun initView() {

        rl_pwd.setOnClickListener {
            startActivity(Intent(activity, EditPwdActivity::class.java))
        }
    }
}