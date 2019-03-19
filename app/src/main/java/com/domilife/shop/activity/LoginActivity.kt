package com.midian.shop.activity

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import cn.smssdk.EventHandler
import cn.smssdk.SMSSDK
import com.jakewharton.rxbinding2.view.RxView

import com.midian.shop.R
import com.midian.shop.base.BaseActivity
import com.midian.shop.net.RetrofitManager
import com.midian.shop.utils.StatusBarUtil
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SMSSDK.registerEventHandler(eh)
        initView()
    }

    val eh: EventHandler = object : EventHandler() {

        override fun afterEvent(event: Int, result: Int, data: Any) {

            if (result == SMSSDK.RESULT_COMPLETE) {
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    runOnUiThread {
                        // TODO 处理验证码验证通过的结果
                        toast("验证通过")
                    }
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    // 请注意，此时只是完成了发送验证码的请求，验证码短信还需要几秒钟之后才送达
                    runOnUiThread { toast("验证码已发送") }
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    //返回支持发送验证码的国家列表
                }
            } else {
                //(data as Throwable).printStackTrace()
                runOnUiThread {
                    toast("验证码错误")
                }
            }
        }
    }

    override fun layoutId(): Int {
        return R.layout.activity_login
    }

    private fun initView(){
        this?.let { StatusBarUtil.darkMode(it) }
        //this?.let { StatusBarUtil.setPaddingSmart(it, toolbar) }

        tv_line.paint.flags = Paint.UNDERLINE_TEXT_FLAG //下划线

        et_code.setOnClickListener { et_code.requestFocus() }

        iv_close.setOnClickListener { this.finish() }

        tv_get_code.setOnClickListener {

            // 请求验证码，其中country表示国家代码，如“86”；phone表示手机号码，如“13800138000”
            val mobile = et_phone.checkBlank("手机号不能为空") ?: return@setOnClickListener
            if(!checkPhoneNum(mobile)){
                toast("手机号格式不正确")
                return@setOnClickListener
            }
            SMSSDK.getVerificationCode("86", mobile)
            timer(tv_get_code)
        }


        RxView.clicks(tv_login).throttleFirst(20, TimeUnit.SECONDS).subscribe {
            tv_login.setOnClickListener {
                goLogin()
            }
        }

    }

    private fun timer(getCode: TextView) {
        var mSubscription: Subscription? = null
        val count = 59L
        Flowable.interval(0, 1, TimeUnit.SECONDS)
                .onBackpressureBuffer()//加上背压策略
                .take(count) //设置循环次数
                .map{ aLong ->
                    count - aLong //
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Long> {
                    override fun onSubscribe(s: Subscription?) {
                        getCode.isEnabled = false
                        getCode.setTextColor((Color.GRAY))
                        mSubscription = s
                        s?.request(Long.MAX_VALUE)
                    }

                    override fun onNext(aLong: Long?) {
                        getCode.text = "${aLong}s后重发"
                    }

                    override fun onComplete() {
                        getCode.text = "点击重发"
                        getCode.isEnabled = true
                        getCode.setTextColor(Color.WHITE)
                        mSubscription?.cancel()//取消订阅，防止内存泄漏
                    }

                    override fun onError(t: Throwable?) {
                        t?.printStackTrace()
                    }
                })
    }

    fun checkPhoneNum(num: String): Boolean{
        val regExp = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(14[5-9])|(166)|(19[8,9])|)\\d{8}$"
        val p = Pattern.compile(regExp)
        val m = p.matcher(num)
        return m.matches()
    }

    fun TextView.checkBlank(message: String): String? {
        val text = this.text.toString()
        if (text.isBlank()) {
            toast(message)
            return null
        }
        return text
    }


    private fun toast(message: String){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

    private fun goLogin(){
        val mobile = et_phone.checkBlank("手机号不能为空") ?: return
        if(!checkPhoneNum(mobile)){
            toast("手机号格式不正确")
            return
        }
        val code = et_code.checkBlank("验证码不能为空") ?: return


        //SMSSDK.submitVerificationCode("86", mobile, code)
        shopsmslogin(mobile,code)


    }

    private fun shopsmslogin(phone: String, code: String){
        RetrofitManager.service.shopsmslogin("shopsmslogin", phone, code)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    result ->
                    Log.d("Kotlin", result.toString())
                }, {
                    error ->
                    error.printStackTrace()
                }, {
                    Log.d("Kotlin", "onComplete")
                }, {
                    Log.d("Kotlin", "onStart")
                })

    }

    override fun onDestroy() {
        super.onDestroy()
        SMSSDK.unregisterEventHandler(eh)
    }

}

