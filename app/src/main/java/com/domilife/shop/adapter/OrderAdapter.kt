package com.domilife.shop.adapter

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


open class OrderAdapter(context: Context?, list: ArrayList<String>?, type:String?) : RecyclerView.Adapter<OrderAdapter.CommonHolder>() {

    open var mContext: Context? = null
    private var mList: ArrayList<String>? = null
    private var mInflater: LayoutInflater? = null
    open var mType: String? = null

    private var mShareListener: ShareListener?=null

    init {
        mContext = context
        mList = list
        mType = type
        mInflater = LayoutInflater.from(context)
    }

    override fun onBindViewHolder(p0: CommonHolder, p1: Int) {
        val title: String? = mList?.get(p1)

//        p0.item?.setOnClickListener {
//            var context = mContext as MainActivity
//            context.startActivity(Intent(mContext, DmWebActivity::class.java),false)
//        }

        with(p0){
            llItem?.setOnClickListener {mShareListener?.onClick(it) }
            tvName?.text = title

        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CommonHolder {
        return CommonHolder(mInflater?.inflate(R.layout.item_order_layout, p0, false))
    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    inner class CommonHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var tvName = itemView?.findViewById<TextView>(R.id.tv_name)
        var tvState = itemView?.findViewById<TextView>(R.id.tv_state)
        var tvNum = itemView?.findViewById<TextView>(R.id.tv_num)
        var tvdh = itemView?.findViewById<TextView>(R.id.tv_dh)
        var tvTime = itemView?.findViewById<TextView>(R.id.tv_time)
        var tvm= itemView?.findViewById<TextView>(R.id.tv_m)
        var llItem= itemView?.findViewById<LinearLayout>(R.id.ll_item)
    }

    fun setOnClickListener(shareListener:ShareListener){
        mShareListener = shareListener
    }

    interface ShareListener{
        fun onClick(v: View)
    }

}
