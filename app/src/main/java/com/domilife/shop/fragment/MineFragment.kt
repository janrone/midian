package com.midian.shop.fragment

import android.os.Bundle
import com.midian.shop.R
import com.midian.shop.base.BaseFragment

/**
 * Created by janrone on 2019/3/16.
 */
class MineFragment : BaseFragment() {

    private var mTitle :String? = null

    companion object {
        fun getInstance(title: String): MineFragment {
            val fragment = MineFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }



    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }
}