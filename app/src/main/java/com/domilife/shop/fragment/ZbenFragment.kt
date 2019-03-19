package com.midian.shop.fragment

import android.os.Bundle
import com.midian.shop.R
import com.midian.shop.base.BaseFragment

/**
 * Created by janrone on 2019/3/16.
 */
class ZbenFragment : BaseFragment() {

    private var mTitle :String? = null

    companion object {
        fun getInstance(title: String): ZbenFragment {
            val fragment = ZbenFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }



    override fun getLayoutId(): Int {
        return R.layout.fragment_zben
    }

}