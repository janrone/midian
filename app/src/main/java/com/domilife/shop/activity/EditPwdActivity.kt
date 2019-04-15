package com.domilife.shop.activity

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import cn.smssdk.EventHandler
import cn.smssdk.SMSSDK
import com.domilife.shop.Constants
import com.domilife.shop.R
import com.domilife.shop.base.BaseActivity
import com.domilife.shop.net.RetrofitManager
import com.domilife.shop.utils.Preference
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_edit_pwd.*
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import java.lang.Exception
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern

class EditPwdActivity : BaseActivity() {


    var type : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SMSSDK.registerEventHandler(eh)
    }

    val eh: EventHandler = object : EventHandler() {

        override fun afterEvent(event: Int, result: Int, data: Any) {

            if (result == SMSSDK.RESULT_COMPLETE) {
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    runOnUiThread {
                        // TODO 处理验证码验证通过的结果
                        toast("验证通过")
                        //Log.d(Constants.TAG, "onStart")
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
        return R.layout.activity_edit_pwd
    }

    override fun initView() {
        toolbar?.setNavigationOnClickListener { finish() }
    }

    override fun initData() {
        type = intent.getIntExtra("type", 0)

        tv_get_code.setOnClickListener {

            // 请求验证码，其中country表示国家代码，如“86”；phone表示手机号码，如“13800138000”
            val mobile = et_phone.checkBlank("手机号不能为空") ?: return@setOnClickListener
            if (!checkPhoneNum(mobile)) {
                toast("手机号格式不正确")
                return@setOnClickListener
            }
            SMSSDK.getVerificationCode("86", mobile)
            timer(tv_get_code)
        }

        RxView.clicks(tv_login).throttleFirst(3, TimeUnit.SECONDS)
                .subscribe {
                    setPwd()
                }
    }

    private fun setPwd() {
        val mobile = et_phone.checkBlank("手机号不能为空") ?: return
        if (!checkPhoneNum(mobile)) {
            toast("手机号格式不正确")
            return
        }
        val code = et_code.checkBlank("验证码不能为空") ?: return
        val pwd = et_pwd.checkBlank("密码不能为空") ?: return

        when(type){
            0 ->  {doTiXianPwd(mobile, code, pwd)}
            1 ->  {doSetPwd(mobile, code, pwd)}
        }

    }


    fun TextView.checkBlank(message: String): String? {
        val text = this.text.toString()
        if (text.isBlank()) {
            toast(message)
            return null
        }
        return text
    }

    fun checkPhoneNum(num: String): Boolean {
        val regExp = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(14[5-9])|(166)|(19[8,9])|)\\d{8}$"
        val p = Pattern.compile(regExp)
        val m = p.matcher(num)
        return m.matches()
    }

    private fun timer(getCode: TextView) {
        var mSubscription: Subscription? = null
        val count = 59L
        Flowable.interval(0, 1, TimeUnit.SECONDS)
                .onBackpressureBuffer()//加上背压策略
                .take(count) //设置循环次数
                .map { aLong ->
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


    fun doSetPwd(phone:String , code :String, pwd:String){
        try {
            var accountId by Preference("accountId","")
            var param = HashMap<String, String>()
            param.put("action", Constants.SHOPSETPASSWORD)
            param.put("accountId", accountId)
            param.put("phone", phone)
            param.put("smsCode", code)
            param.put("pwd", pwd)
            mLoadingDialog?.show()


            RetrofitManager.service.baseRequest(param)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ result ->
                        mLoadingDialog?.hide()
                        Log.d(Constants.TAG, result.toString())
                        if(result.code ==1){
                            toast("设置成功")
                            finish()
                        }else{
                            toast(result.msg)
                        }
                    }, { error ->
                        mLoadingDialog?.hide()
                        toast(error.toString())
                        Log.d(Constants.TAG, error.toString())
                    }
                    )
        } catch (e: Exception) {
            mLoadingDialog?.hide()
            Log.d(Constants.TAG, e.toString())
        }


    }

    fun doTiXianPwd(phone:String , code :String, pwd:String){
        try {
            var accountId by Preference("accountId","")
            var param = HashMap<String, String>()
            param.put("action", Constants.SHOPSETPASSWORD)
            param.put("accountId", accountId)
            param.put("phone", phone)
            param.put("smsCode", code)
            param.put("pwd", pwd)
            mLoadingDialog?.show()


            RetrofitManager.service.baseRequest(param)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ result ->
                        mLoadingDialog?.hide()
                        Log.d(Constants.TAG, result.toString())
                        if(result.code ==1){
                            toast("设置成功")
                            finish()
                        }else{
                            toast(result.msg)
                        }
                    }, { error ->
                        mLoadingDialog?.hide()
                        toast(error.toString())
                        Log.d(Constants.TAG, error.toString())
                    }
                    )
        } catch (e: Exception) {
            mLoadingDialog?.hide()
            Log.d(Constants.TAG, e.toString())
        }


    }
 }