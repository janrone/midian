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


open class ProductAdapter(context: Context?, list: ArrayList<String>?, type:String?) : RecyclerView.Adapter<ProductAdapter.CommonHolder>() {

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

        p0?.tvTitle?.text = title

//        p0.item?.setOnClickListener {
//            var context = mContext as MainActivity
//            context.startActivity(Intent(mContext, DmWebActivity::class.java),false)
//        }

        with(p0){
            tvShare?.setOnClickListener {mShareListener?.onClick(it) }
            tvEdit?.setOnClickListener {mShareListener?.onClick(it) }
            tvDown?.setOnClickListener {mShareListener?.onClick(it) }
            llItem?.setOnClickListener {mShareListener?.onClick(it) }
            tvDel?.setOnClickListener {mShareListener?.onClick(it) }
            tvUp?.setOnClickListener {mShareListener?.onClick(it) }

            when(mType){
                "已下架" ->{
                    llDel?.visibility = View.VISIBLE
                    llUp?.visibility = View.VISIBLE
                }
                else ->{
                    llDel?.visibility = View.GONE
                    llUp?.visibility = View.GONE
                }
            }
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CommonHolder {
        when (mType) {
            "已下架" -> return CommonHolder(mInflater?.inflate(R.layout.item_product_down_layout, p0, false))
        }
        return CommonHolder(mInflater?.inflate(R.layout.item_product_layout, p0, false))
    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    inner class CommonHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var tvTitle = itemView?.findViewById<TextView>(R.id.iv_name)
        var tvShare = itemView?.findViewById<TextView>(R.id.iv_share)
        var tvEdit = itemView?.findViewById<TextView>(R.id.tv_edit)
        var tvDown = itemView?.findViewById<TextView>(R.id.tv_down)
        var llItem = itemView?.findViewById<LinearLayout>(R.id.ll_item)
        var tvDel = itemView?.findViewById<TextView>(R.id.tv_del)
        var tvUp = itemView?.findViewById<TextView>(R.id.tv_up)
        var llUp = itemView?.findViewById<LinearLayout>(R.id.ll_up)
        var llDel = itemView?.findViewById<LinearLayout>(R.id.ll_del)
    }

    fun setOnClickListener(shareListener:ShareListener){
        mShareListener = shareListener
    }

    interface ShareListener{
        fun onClick(v: View)
    }

}
