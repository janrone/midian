package com.midian.midian.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by janrone on 2019/3/16.
 */

abstract class BaseFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(),null)
    }


    /**
     * 加载布局
     */
    @LayoutRes
    abstract fun getLayoutId():Int
}