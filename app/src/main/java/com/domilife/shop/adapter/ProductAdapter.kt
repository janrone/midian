package com.domilife.shop.adapter;

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.domilife.shop.MainActivity
import com.domilife.shop.R
import com.domilife.shop.activity.DmWebActivity


class ProductAdapter(context: Context?, list: ArrayList<String>?) : RecyclerView.Adapter<ProductAdapter.CommonHolder>() {
    override fun onBindViewHolder(p0: CommonHolder, p1: Int) {
        val title: String? = mList?.get(p1)

        p0?.tvTitle?.text = title

//        p0.item?.setOnClickListener {
//            var context = mContext as MainActivity
//            context.startActivity(Intent(mContext, DmWebActivity::class.java),false)
//        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CommonHolder {
        return CommonHolder(mInflater?.inflate(R.layout.item_product_layout, p0, false))
    }


    var mContext: Context? = null
    private var mList: ArrayList<String>? = null
    private var mInflater: LayoutInflater? = null

    init {
        mContext = context
        mList = list
        mInflater = LayoutInflater.from(context)
    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    class CommonHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var tvTitle = itemView?.findViewById<TextView>(R.id.iv_name)
    }
}
