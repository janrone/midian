package com.domilife.shop.fragment

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.domilife.shop.R
import com.domilife.shop.activity.EditProductActivity
import com.domilife.shop.activity.ProductActivity
import com.domilife.shop.adapter.ProductAdapter
import com.domilife.shop.base.BaseFragment
import com.umeng.socialize.ShareAction
import com.umeng.socialize.UMShareListener
import com.umeng.socialize.bean.SHARE_MEDIA
import com.umeng.socialize.media.UMImage
import com.umeng.socialize.media.UMWeb
import com.umeng.socialize.shareboard.SnsPlatform
import com.umeng.socialize.utils.ShareBoardlistener
import kotlinx.android.synthetic.main.fragment_syj.*

/**
 * Created by janrone on 2019/3/16.
 */
class ProductFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    private var mTitle: String? = null
    private var mList: ArrayList<String>? = null
    var mCommonAdapter: ProductAdapter? = null

    private var mShareAction: ShareAction? = null

    companion object {
        fun getInstance(title: String): ProductFragment {
            val fragment = ProductFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_product
    }


    override fun initView() {
        //下拉刷新颜色
        srl.isEnabled = false
        srl.setColorSchemeColors(Color.parseColor("#F1AA0E"))
        srl.setOnRefreshListener(this)
        list.layoutManager = LinearLayoutManager(activity)
        mList = ArrayList()
        list.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            var lastVisibleItem: Int? = 0
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView!!, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem!! + 1 == mCommonAdapter?.itemCount) {
                    //addData()
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView!!, dx, dy)
                val layoutManager = recyclerView?.layoutManager as LinearLayoutManager
                //最后一个可见的ITEM
                lastVisibleItem = layoutManager.findLastVisibleItemPosition()
            }
        })

        mCommonAdapter = ProductAdapter(activity, mList, mTitle)

        mCommonAdapter?.setOnClickListener(object :ProductAdapter.ShareListener{
            override fun onClick(v: View) {
                when(v.id){
                    R.id.iv_share -> initShare()
                    R.id.tv_edit -> initShare()
                    R.id.tv_down -> initShare()
                    R.id.tv_up -> {}
                    R.id.tv_del -> {}
                    R.id.ll_item -> {
                        startActivity(Intent(activity, EditProductActivity::class.java))
                    }
                }
            }
        })
        list.adapter = mCommonAdapter

        addData()
    }

    override fun onRefresh() {
        //关闭下拉刷新进度条
        srl.isRefreshing = true
        addData()
    }

    //添加数据
    private fun addData() {
        mList!!.add("1111")
        mList!!.add("aaaa")
        mList!!.add("--------")

        list.adapter?.notifyDataSetChanged()
    }


    private fun initShare(){
        //获得权限
        if (ContextCompat.checkSelfPermission(activity!!.applicationContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //没有的话弹出权限获取框
            ActivityCompat.requestPermissions(activity!!, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        }

        //配置分享面板
        mShareAction = ShareAction(activity)
                .withText("分享")
                .setDisplayList(
                        //传入展示列表
                        SHARE_MEDIA.QQ,//QQ
                        SHARE_MEDIA.QZONE,//QQ
                        SHARE_MEDIA.WEIXIN, //微信
                        SHARE_MEDIA.WEIXIN_CIRCLE//微信朋友圈
                        //SHARE_MEDIA.WEIXIN_FAVORITE//微信收藏
                ).setShareboardclickCallback(object : ShareBoardlistener {
                    override fun onclick(snsPlatform: SnsPlatform?, share_media: SHARE_MEDIA?) {
                        val web = UMWeb("https://www.jianshu.com/u/8c6b4be8770b")//你要分享的url
                        web.mText = "tonjies的博客"//分享内容的标题
                        web.description = "tonjies的博客，会每周分享一些开发知识"//分享内容的描述
                        web.setThumb(UMImage(activity, R.drawable.smssdk_corners_bg))//分享内容的缩略图
                        ShareAction(activity)
                                .withMedia(web)
                                .setPlatform(share_media)//设置分享的平台
                                .setCallback(shareListener)
                                .share()
                    }
                })

        mShareAction?.open()
    }

    //分享的回调
    private val shareListener = object : UMShareListener {

        //开始分享，platform为平台类型
        override fun onStart(platform: SHARE_MEDIA) {
            //L.d("开始分享，分享的平台是：$platform");
        }

        //分享成功
        override fun onResult(platform: SHARE_MEDIA) {
            //L.d("分享成功")
            (activity as ProductActivity).toast("分享成功")
        }

        //分享失败
        override fun onError(platform: SHARE_MEDIA, t: Throwable) {
            (activity as ProductActivity).toast("分享失败,失败的原因是$t")
        }

        //分享取消了
        override fun onCancel(platform: SHARE_MEDIA) {
            (activity as ProductActivity).toast("分享取消了")
        }
    }

}