package com.domilife.shop.activity

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.domilife.shop.Constants
import com.domilife.shop.R
import com.domilife.shop.base.BaseActivity
import com.domilife.shop.bean.InCodeBean
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

    private var hasInviteCode = 0
    private var inviteInfo: InviteInfoBean? = null

    override fun layoutId(): Int {
        return R.layout.activity_code
    }

    override fun initView() {
        toolbar.setNavigationOnClickListener { finish() }
        tv_no_code.paint.flags = Paint.UNDERLINE_TEXT_FLAG //下划线


    }

    override fun initData() {

        tv_no_code.setOnClickListener {
            et_code.setText("13PI")
            getInViteInfo()
        }


        RxView.clicks(tv_next).throttleFirst(3, TimeUnit.SECONDS)
                .subscribe {
                    if(hasInviteCode == 1 && !TextUtils.isEmpty(et_code.text)){
                       bindInvCode()
                    }else{
                        toast("请输入邀请码！")
                    }

                }

        iv_close_small.setOnClickListener {
            et_code.setText("")
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
                            hasInviteCode = result.code
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

    private fun bindInvCode(){
        var param = HashMap<String, String>()
        param.put("action", Constants.SHOPACCOUNTBINDINVNO)
        param.put("accountId", getIntent().getParcelableExtra<InCodeBean>("data")?.accountId.toString())
        param.put("invNo", et_code.text.toString())

        RetrofitManager.service.baseRequest(param)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            var intent = Intent(this@InviteCodeActivity, ShopInfoMainActivity::class.java);
                            intent.putExtra("data", getIntent().getParcelableExtra<InCodeBean>("data"))
                            startActivity(intent)
                        }, { error ->
                    Log.d(Constants.TAG, error.toString())
                }
                )
    }
    //private var mLoginType = 0
    //var mLoadingDialog: LoadingDialog? = null


}