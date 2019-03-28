package com.domilife.shop.adapter

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.domilife.shop.R
import com.domilife.shop.bean.TwoBean

class TwoAdapter(var layoutId:Int,var datas:List<TwoBean>):BaseQuickAdapter<TwoBean,BaseViewHolder>(layoutId,datas){


    override fun convert(helper: BaseViewHolder?, item: TwoBean?) {

        helper!!.getView<TextView>(R.id.tv_name).setText(item!!.name)
        helper!!.getView<TextView>(R.id.tv_rate).setText(item!!.servRate + "%")
    }
}