package com.domilife.shop.activity

import android.graphics.Bitmap
import android.os.Environment
import com.domilife.shop.R
import com.domilife.shop.base.BaseActivity
import com.domilife.shop.utils.VersionUtil
import kotlinx.android.synthetic.main.activity_contant.*
import java.io.File
import java.io.OutputStream
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.provider.MediaStore


class ContentActivity : BaseActivity() {


    var mPath:String ? = null
    override fun layoutId(): Int {
        return R.layout.activity_contant
    }

    override fun initView() {
        toolbar?.setNavigationOnClickListener { finish() }
    }

    override fun initData() {
        tv_v_code.setText(VersionUtil.getVersionName(this))


        mPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + "/"

        tv_save.setOnClickListener {
            val bitmap  = (iv_save.drawable as BitmapDrawable).bitmap
            //val file_path = mPath+"XX生活公众号.png"
            //FileUtil.saveImage(file_path, bitmap)
            MediaStore.Images.Media.insertImage(
                    contentResolver,
                    bitmap!!,
                    "image_file",
                    "file")
            toast("图片已保存到相册")
        }

        tv_kf.setOnClickListener{
            val cm = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val mClipData = ClipData.newPlainText("text", tv_kf.tag.toString())
            cm.primaryClip=(mClipData)
            toast("复制成功")
        }
    }


    object FileUtil {
        fun saveImage(path: String, bitmap: Bitmap) {
            try {
                val file = File(path)
                //outputStream获取文件的输出流对象
                val fos: OutputStream = file.outputStream()
                //压缩格式为JPEG图像，压缩质量为80%
                bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fos)
                fos.flush()
                fos.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    override fun onStart() {
        super.onStart()
        iv_save.isDrawingCacheEnabled = true
    }

    override fun onStop() {
        super.onStop()
        iv_save.isDrawingCacheEnabled = false
    }

 }