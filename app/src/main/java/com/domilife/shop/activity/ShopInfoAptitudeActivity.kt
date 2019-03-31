package com.domilife.shop.activity

import android.content.Intent
import android.util.Log
import android.view.View
import com.domilife.shop.Constants
import com.domilife.shop.R
import com.domilife.shop.base.BaseActivity
import kotlinx.android.synthetic.main.activity_shop_info_zz.*
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.ImageView
import com.jhworks.library.ImageSelector
import com.jhworks.library.bean.MediaSelectConfig
import java.io.File
import com.domilife.shop.utils.GlideApp
import id.zelory.compressor.Compressor
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.MultipartBody
import android.os.Environment
import com.domilife.shop.net.RetrofitManager
import com.domilife.shop.utils.Preference
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.lang.Exception


class ShopInfoAptitudeActivity : BaseActivity() {

    var imageArray: Array<ImageView>? = null
    lateinit var imageList: ArrayList<String>

    var cartType = 0

    var imageKey: Array<String> = arrayOf("doorPic", "insidePic", "licensePic", "identifyFPic", "identifyBPic")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageArray = arrayOf(iv_shop_f, iv_shop_b, iv_yyzz, iv_card_f, iv_card_b)
        imageList = ArrayList()

    }

    //doorPic
    //insidePic
    //licensePic
    //identifyFPic
    //identifyBPic

    override fun layoutId(): Int {
        return R.layout.activity_shop_info_zz
    }

    override fun initView() {
        toolbar?.setNavigationOnClickListener { finish() }

        setDateInput(rg_date.checkedRadioButtonId)
        rg_date.setOnCheckedChangeListener { radioGroup, i ->
            setDateInput(i)
            Log.d(Constants.TAG, i.toString())
        }

    }

    private fun setDateInput(i: Int) {
        if (i == radio0.id) {
            cartType = 0
            rl_to_date.visibility = View.VISIBLE
        } else {
            cartType = 1
            rl_to_date.visibility = View.GONE
        }
    }

    override fun initData() {

        var config = MediaSelectConfig().setSelectMode(MediaSelectConfig.MODE_SINGLE) //设置选择图片模式，单选与多选
                .setShowCamera(true) //是否展示打开摄像头拍照入口，只针对照片，视频列表无效
                .setOpenCameraOnly(false) //是否只是打开摄像头拍照而已
                .setImageSpanCount(4) //自定义列表展示个数，默认3

        iv_shop_f.setOnClickListener {
            ImageSelector.create()
                    .setMediaConfig(config)
                    .startImageAction(this, 0)

        }

        iv_shop_b.setOnClickListener {
            ImageSelector.create()
                    .setMediaConfig(config)
                    .startImageAction(this, 1)

        }

        iv_yyzz.setOnClickListener {
            ImageSelector.create()
                    .setMediaConfig(config)
                    .startImageAction(this, 2)

        }

        iv_card_f.setOnClickListener {
            ImageSelector.create()
                    .setMediaConfig(config)
                    .startImageAction(this, 3)

        }

        iv_card_b.setOnClickListener {
            ImageSelector.create()
                    .setMediaConfig(config)
                    .startImageAction(this, 4)

        }

        tv_save.setOnClickListener {
            submit()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        super.onActivityReenter(resultCode, data)
        if (resultCode === Activity.RESULT_OK) {
            //返回的数据
            //al result = Phoenix.result(data)
            var path = data!!.getStringArrayListExtra(ImageSelector.EXTRA_RESULT)[0]
            imageList.add(path)
            GlideApp.with(this@ShopInfoAptitudeActivity).load(path).into(imageArray!![requestCode])
            //compressPic()
            toast(data?.getStringArrayListExtra(ImageSelector.EXTRA_RESULT).toString())
            //mMediaAdapter.setData(result)
        }
    }


    private fun compressPic(path: String) {
        var compressedImageFile = Compressor(this).compressToFile(File(path))
    }

    fun getLayoutId(paramContext: Context, paramString: String): Int {
        return paramContext.resources.getIdentifier(paramString, "layout", paramContext.packageName)
    }
    //return paramContext.getResources().getIdentifier(paramString, "layout",) 


    fun toRequestBody(value: String): RequestBody {
        //MediaType.parse("multipart/form-data")
        return RequestBody.create(MediaType.parse("multipart/form-data"), value)
    }

    val parts = ArrayList<MultipartBody.Part>()

    fun submit() {
            saveData()
            doUpload()

    }


    fun doUpload() {
        mLoadingDialog?.show()
       try {
           var accountId: String by Preference("accountId", "")

           var param = HashMap<String, RequestBody>()
           param.put("accountId", toRequestBody(accountId))
           param.put("name", toRequestBody(et_name.text.toString()))
           param.put("identifyNo", toRequestBody(et_id_code.text.toString()))
           param.put("timeType", toRequestBody(cartType.toString()))
           param.put("endTime", toRequestBody(et_id_to_date.text.toString()))
           param.put("regNo", toRequestBody(et_reg_no.text.toString()))


           var qparam = HashMap<String, String>()
           qparam.put("action", Constants.POSTSHOPQUALITY)


           RetrofitManager.service.baseRequestUpload(qparam,param, parts)
                   .subscribeOn(Schedulers.io())
                   .unsubscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(
                           { result ->
                               mLoadingDialog?.hide()
                               Log.d(Constants.TAG, "fun doUpload() result"+result.toString())
                           }, { error ->
                       mLoadingDialog?.hide()
                       Log.d(Constants.TAG, error.toString())
                       Log.d(Constants.TAG, "fun doUpload() error"+error.toString())
                   }
                   )
       }catch (e: Exception){
           Log.d(Constants.TAG, "fun doUpload() "+e.toString())
           mLoadingDialog?.hide()
       }
    }

    fun saveData() {
        for (i in 0 until imageList.size) {
            //这里采用的Compressor图片压缩
//            val file = Compressor(this@ShopInfoAptitudeActivity)
//                    .setMaxWidth(720)
//                    .setMaxHeight(1080)
//                    .setQuality(80)
//                    .setCompressFormat(Bitmap.CompressFormat.JPEG)
//                    .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
//                            Environment.DIRECTORY_PICTURES).absolutePath)
//                    .compressToFile(File(imageList[i]))
            var file =  File(imageList[i])
            val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)

            val part = MultipartBody.Part.createFormData(imageKey[i], file.name, requestFile)
            Log.d(Constants.TAG, imageKey[i] + " is key")

            parts.add(part)
        }
    }
}