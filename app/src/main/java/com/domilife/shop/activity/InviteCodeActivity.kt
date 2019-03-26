package com.domilife.shop.activity

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.View
import com.domilife.shop.Constants
import com.domilife.shop.R
import com.domilife.shop.base.BaseActivity
import com.domilife.shop.bean.InviteInfoBean
import com.domilife.shop.net.RetrofitManager
import com.domilife.shop.utils.GlideApp
import com.google.gson.Gson
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_code.*
import java.util.concurrent.TimeUnit


class InviteCodeActivity : BaseActivity() {

    private var hasInviteCode = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initData()

    }

    override fun layoutId(): Int {
        return R.layout.activity_code
    }

    override fun initView() {
        toolbar.setNavigationOnClickListener { finish() }
        tv_no_code.paint.flags = Paint.UNDERLINE_TEXT_FLAG //下划线


    }

    override fun initData() {

        RxView.clicks(tv_next).throttleFirst(3, TimeUnit.SECONDS)
                .subscribe {
                    startActivity(Intent(this, ShopInfoMainActivity::class.java))
                }

        tv_no_code.setOnClickListener {
            et_code.setText("13PI")
            getInViteInfo()
        }


        RxView.clicks(tv_next).throttleFirst(3, TimeUnit.SECONDS)
                .subscribe {

                    startActivity(Intent(this@InviteCodeActivity, ShopInfoMainActivity.class)
                }
    }

    private fun getInViteInfo() {
        var param = HashMap<String, String>()
        param.put("action", Constants.SHOPACCOUNTBYINVNO)
        param.put("invNo", et_code.text.toString())

        RetrofitManager.service.baseRequest(param)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            if (result.code == 1) {
                                var inviteInfo: InviteInfoBean = Gson().fromJson(result.data.toString(), InviteInfoBean::class.java)
                                ll_invite.visibility = View.VISIBLE
                                tv_no_code.visibility = View.GONE
                                tv_name.text = inviteInfo.nickName
                                GlideApp.with(this@InviteCodeActivity).load(inviteInfo.headUrl).into(iv_head)
                            }
                        }, { error ->
                    Log.d(Constants.TAG, error.toString())
                }
                )
    }
    //private var mLoginType = 0
    //var mLoadingDialog: LoadingDialog? = null


}