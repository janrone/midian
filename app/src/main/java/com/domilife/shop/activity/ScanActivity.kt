package com.domilife.shop.activity

import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import android.util.Log
import android.widget.Toast
import com.domilife.shop.Constants
import com.domilife.shop.R
import com.domilife.shop.base.BaseActivity
import com.sencent.qrcodelib.QRCodeView
import com.sencent.qrcodelib.ZXingView
import kotlinx.android.synthetic.main.activity_scan.*

class ScanActivity : BaseActivity() , QRCodeView.Delegate {

    private var mZXingView: ZXingView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mZXingView = findViewById(R.id.zxingview)
        mZXingView?.setDelegate(this)
    }

    override fun onStart() {
        super.onStart()
        mZXingView?.startCamera() // 打开后置摄像头开始预览，但是并未开始识别
        //        mZXingView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT); // 打开前置摄像头开始预览，但是并未开始识别
        mZXingView?.startSpotAndShowRect() // 显示扫描框，并开始识别
    }

    override fun onStop() {
        mZXingView?.stopCamera() // 关闭摄像头预览，并且隐藏扫描框
        super.onStop()
    }

    override fun onDestroy() {
        mZXingView?.onDestroy() // 销毁二维码扫描控件
        super.onDestroy()
    }

    private fun vibrate() {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(200)
    }

    override fun onScanQRCodeSuccess(result: String?) {
        Log.i(Constants.TAG, "result:$result")
        Toast.makeText(this, "扫描结果为：$result", Toast.LENGTH_SHORT).show()
        vibrate()
        //mZXingView?.startSpot() // 开始识别
    }

    override fun onCameraAmbientBrightnessChanged(isDark: Boolean) {
        // 这里是通过修改提示文案来展示环境是否过暗的状态，接入方也可以根据 isDark 的值来实现其他交互效果
        var tipText = mZXingView?.getScanBoxView()?.getTipText() ?: return
        val ambientBrightnessTip = "\n环境过暗，请打开闪光灯"
        if (isDark) {
            if (!tipText.contains(ambientBrightnessTip)) {
                mZXingView?.getScanBoxView()?.setTipText(tipText + ambientBrightnessTip)
            }
        } else {
            if (tipText.contains(ambientBrightnessTip)) {
                tipText = tipText.substring(0, tipText.indexOf(ambientBrightnessTip))
                mZXingView?.getScanBoxView()?.setTipText(tipText)
            }
        }
    }

    override fun onScanQRCodeOpenCameraError() {
        Log.e(Constants.TAG, "打开相机出错")
    }


    override fun layoutId(): Int {
        return R.layout.activity_scan
    }

    override fun initView() {
        toolbar?.setNavigationOnClickListener { finish() }
    }

    override fun initData() {

    }

 }