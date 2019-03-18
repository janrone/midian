package com.midian.midian.activity

import android.os.Bundle

import com.midian.midian.R
import com.midian.midian.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun layoutId(): Int {
        return R.layout.activity_login
    }
}
