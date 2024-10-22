package com.domilife.shop.activity

import android.Manifest
import android.content.Intent
import com.domilife.shop.MainActivity
import com.domilife.shop.R
import com.domilife.shop.base.BaseActivity
import com.domilife.shop.utils.Preference
import pub.devrel.easypermissions.EasyPermissions


class SplashActivity : BaseActivity() {


    override fun layoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initView() {
        checkPermission()
    }

    override fun initData() {

    }

    fun jump(){
        if (Preference.contains("incodebean")) {
            startActivity(Intent(this, MainActivity::class.java))
        }else{
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }


    private fun checkPermission(){
        val perms = arrayOf(Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
        EasyPermissions.requestPermissions(this, " XX小店应用需要以下权限，请允许", 0, *perms)

    }


    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        if (requestCode == 0) {
            if (perms.isNotEmpty()) {
                if (perms.contains(Manifest.permission.READ_PHONE_STATE)
                        && perms.contains(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    jump()
                }
            }
        }
    }


    //private var mLoginType = 0
    //var mLoadingDialog: LoadingDialog? = null



 }