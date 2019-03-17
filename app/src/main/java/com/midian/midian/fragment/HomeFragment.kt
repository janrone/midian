package com.midian.midian.fragment

import android.os.Bundle
import com.midian.midian.R
import com.midian.midian.base.BaseFragment

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

}

