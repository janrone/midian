package com.domilife.shop.activity;

import android.Manifest
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import com.domilife.shop.R
import com.domilife.shop.base.BaseActivity
import com.umeng.socialize.ShareAction
import com.umeng.socialize.UMShareListener
import com.umeng.socialize.bean.SHARE_MEDIA
import com.umeng.socialize.media.UMImage
import com.umeng.socialize.media.UMWeb
import com.umeng.socialize.shareboard.SnsPlatform
import com.umeng.socialize.utils.ShareBoardlistener

/**
 * 测试友盟不同位置分享框的Activity
 * 1，底部带白色背景图标
 * 2，底部不带白色背景图标
 * 3，中部带白色背景图标
 * 3，中部不带白色背景图标
 */
class MenuActivity : BaseActivity(), View.OnClickListener {
    override fun layoutId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * 初始化控件
     */
    override fun initView() {
     //       initShare()
    }


    override fun onClick(v: View?) {
        when (v?.id) {
//            R.id.btn_01 -> {
////                toast("1")
//                mShareAction?.open()
//            }
//            R.id.btn_02 -> {
////                toast("2")
//                var config: ShareBoardConfig = ShareBoardConfig()
//                config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_NONE)//底部菜单，图标无白色圆圈
//                mShareAction?.open(config)
//            }
//            R.id.btn_03 -> {
////                toast("3")
//                var config: ShareBoardConfig = ShareBoardConfig()
//                config.setShareboardPostion(ShareBoardConfig.SHAREBOARD_POSITION_CENTER)
//                config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_CIRCULAR)//中部菜单，有白色圆圈
//                mShareAction?.open(config)
//            }
//            R.id.btn_04 -> {
////                toast("4")
//                val config = ShareBoardConfig()
//                config.setShareboardPostion(ShareBoardConfig.SHAREBOARD_POSITION_CENTER)
//                config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_NONE)//中部菜单，无白色圆圈
//                mShareAction?.open(config)
//            }
        }
    }





}