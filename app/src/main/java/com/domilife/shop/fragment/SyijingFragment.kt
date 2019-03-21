package com.domilife.shop.fragment

import android.os.Bundle
import com.domilife.shop.R
import com.domilife.shop.base.BaseFragment

/**
 * Created by janrone on 2019/3/16.
 */
class SyijingFragment : BaseFragment() {

    private var mTitle :String? = null

    companion object {
        fun getInstance(title: String): SyijingFragment {
            val fragment = SyijingFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }



    override fun getLayoutId(): Int {
        return R.layout.fragment_syj
    }

}