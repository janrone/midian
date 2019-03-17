package com.midian.midian.fragment

import android.os.Bundle
import com.midian.midian.R
import com.midian.midian.base.BaseFragment

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