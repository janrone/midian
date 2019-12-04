package com.domilife.shop.activity

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.text.TextUtils
import android.util.Log
import com.domilife.shop.Constants
import com.domilife.shop.R
import com.domilife.shop.base.BaseActivity
import com.domilife.shop.net.RetrofitManager
import com.domilife.shop.utils.Preference
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_shop_info.*
import java.util.concurrent.TimeUnit


class ShopInfoActivity : BaseActivity() {


    override fun layoutId(): Int {
        return R.layout.activity_shop_info
    }

    override fun initView() {
        toolbar?.setNavigationOnClickListener { finish() }

        RxView.clicks(ll_shop_info).throttleFirst(3, TimeUnit.SECONDS)
                .subscribe {
                    startActivityForResult(Intent(this, MapControlActivity::class.java), 2)
                }

        ll_2.setOnClickListener {
            startActivityForResult(Intent(this, ShopCategoryActivity::class.java), 1)
        }

    }


    override fun initData() {
        iv_tips.setOnClickListener {
            showTips()
        }

        et_ser.setOnClickListener {
            if (TextUtils.isEmpty(tv_kind.text)) {
                //tv_kind.text
                toast("请先选择经营品类。")
            }
        }

        tv_save.setOnClickListener {
            if (TextUtils.isEmpty(et_name.text)) {
                toast("请输入店铺名称。")
                return@setOnClickListener
            }
            if (tv_id_name.text.length > 30) {
                toast("店铺名不能超过30个字")
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(tv_kind.text)) {
                //tv_kind.text
                toast("请先选择经营品类。")
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(et_ser.text)) {
                //tv_kind.text
                toast("请输入服务费。")
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(iv_add_info.text)) {
                //tv_kind.text
                toast("请输入店铺地址。")
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(et_pel.text)) {
                //tv_kind.text
                toast("请输入联系人。")
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(et_phone.text)) {
                //tv_kind.text
                toast("请输入联系电话。")
                return@setOnClickListener
            }

            saveShoInfo()
        }

    }

    private var kind: String = ""
    private var serRate: String = ""

    private var add: String = ""
    private var city: String = ""
    private var nearBy: String = ""
    private var categoryId: String = ""
    private var lon: String = ""
    private var lat: String = ""

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1&& resultCode == Activity.RESULT_OK) {
            kind = data!!.getStringExtra("kind")
            tv_kind.setText(kind)
            serRate = data!!.getStringExtra("serRate")
            et_ser.setText(serRate)
        } else if (requestCode == 2 && resultCode == Activity.RESULT_OK) {
            add = data!!.getStringExtra("add")
            city = data!!.getStringExtra("city")
            nearBy = data!!.getStringExtra("nearBy")
            lon = data!!.getStringExtra("lon")
            lat = data!!.getStringExtra("lat")
            iv_add_info.setText(add)
        }
    }


    private fun showTips() {
        val msg = "服务费是什么：\n" +
                "\n" +
                "XX生活商户端为商家提供网络信息服务及相应软件（包括但不限于XX生活商户端APP，后台系统等）技术服务、商家门店在XX生活APP端展现并吸引会员消费的服务、其他XX生活平台提供的服务所收取的相关服务费用。每店的服务费率由XX生活拓店者与商家协商确定后在XX生活商户端APP提交确定。"
        AlertDialog.Builder(this)
                .setMessage(msg)
                .setPositiveButton("确定", DialogInterface.OnClickListener { _, _ ->
                    //copyText(holder.itemView.context, item.value)
                    //Toast.makeText(holder.itemView.context, "复制成功", Toast.LENGTH_SHORT).show()
                })
                //.setNeutralButton("取消", null)
                .create()
                .show()
    }

    private fun saveShoInfo() {
        try {
            mLoadingDialog?.show()
            var accountId: String by Preference("accountId", "")
            var param = HashMap<String, String>()
            param.put("action", Constants.POSTSHOPINFO)
            param.put("accountId", accountId)
            param.put("shopName", et_name.text.toString())
            param.put("categoryId", categoryId)
            param.put("servRate", et_ser.text.toString().toDouble().toString())
            param.put("lon", lon) //jingdu
            param.put("lat", lat)
            param.put("address", add)
            param.put("city", city)
            param.put("nearBy", nearBy)
            param.put("contactName", et_pel.text.toString())
            param.put("contactPhone", et_phone.text.toString())

            RetrofitManager.service.baseRequestPost(param)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { result ->
                                mLoadingDialog?.hide()
                                if (result.code == 1) {
                                    toast("保存成功")
                                    //GlideApp.with(this@InviteCodeActivity).load(inviteInfo.headUrl).into(iv_head)
                                } else {
                                    toast(result.msg)
                                }
                            }, { error ->
                        mLoadingDialog?.hide()
                        Log.d(Constants.TAG, error.toString())
                    }
                    )
        } catch (e: Exception) {
            Log.d(Constants.TAG, e.toString())
            mLoadingDialog?.hide()
        }
    }

}