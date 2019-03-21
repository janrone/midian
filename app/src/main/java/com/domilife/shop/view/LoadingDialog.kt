package com.domilife.shop.view

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.widget.ImageView
import com.domilife.shop.R

class LoadingDialog(context: Context) : Dialog(context, R.style.CustomProgressDialog) {

    private var imgIv: ImageView? = null

    init {
        setContentView(R.layout.loading_dialog)
        imgIv = findViewById(R.id.imgIv) as ImageView
    }

    @SuppressLint("ObjectAnimatorBinding")
    private fun initAnim() {
        val animator = ObjectAnimator.ofFloat(imgIv, "rotation", 0f, 359f)
        animator.repeatCount = ValueAnimator.INFINITE
        animator.repeatMode = ValueAnimator.REVERSE
        animator.duration = 2000
        animator.start()
    }

    override fun show() {//在要用到的地方调用这个方法
        super.show()
        initAnim()
    }

    override fun dismiss() {
        super.dismiss()
    }

}