package com.domilife.shop.adapter;

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.domilife.shop.MainActivity
import com.domilife.shop.R
import com.domilife.shop.activity.DmWebActivity
import com.umeng.socialize.ShareAction
import com.umeng.socialize.UMShareListener
import com.umeng.socialize.bean.SHARE_MEDIA
import com.umeng.socialize.media.UMImage
import com.umeng.socialize.media.UMWeb
import com.umeng.socialize.shareboard.SnsPlatform
import com.umeng.socialize.utils.ShareBoardlistener


class ProductAdapter(context: Context?, list: ArrayList<String>?) : RecyclerView.Adapter<ProductAdapter.CommonHolder>() {

    var mContext: Context? = null
    private var mList: ArrayList<String>? = null
    private var mInflater: LayoutInflater? = null

    private var mShareListener: ShareListener?=null

    init {
        mContext = context
        mList = list
        mInflater = LayoutInflater.from(context)
    }

    override fun onBindViewHolder(p0: CommonHolder, p1: Int) {
        val title: String? = mList?.get(p1)

        p0?.tvTitle?.text = title

//        p0.item?.setOnClickListener {
//            var context = mContext as MainActivity
//            context.startActivity(Intent(mContext, DmWebActivity::class.java),false)
//        }
        p0.tvShare?.setOnClickListener {
            mShareListener?.share()
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CommonHolder {
        return CommonHolder(mInflater?.inflate(R.layout.item_product_layout, p0, false))
    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    class CommonHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var tvTitle = itemView?.findViewById<TextView>(R.id.iv_name)
        var tvShare = itemView?.findViewById<TextView>(R.id.iv_share)
    }

    fun setShareListener(shareListener:ShareListener){
        mShareListener = shareListener
    }

    fun setShareListener() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    interface ShareListener{
        fun share()
    }

}
