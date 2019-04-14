package com.domilife.shop.fragment

import android.graphics.Color
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.domilife.shop.R
import com.domilife.shop.adapter.CommonAdapter
import com.domilife.shop.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_syj.*

/**
 * Created by janrone on 2019/3/16.
 */
class SyijingFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    private var mTitle: String? = null
    private var mList: ArrayList<String>? = null
    var mCommonAdapter: CommonAdapter? = null

    companion object {
        fun getInstance(title: String): SyijingFragment {
            val fragment = com.domilife.shop.fragment.SyijingFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_syj
    }


    override fun initView() {
        //下拉刷新颜色
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
        mCommonAdapter = CommonAdapter(activity, mList)
        list.adapter = mCommonAdapter
        addData()
    }

    override fun onRefresh() {
        //关闭下拉刷新进度条
        srl.isRefreshing = false
        addData()
    }

    //添加数据
    private fun addData() {
        mList!!.add("1111")
        mList!!.add("aaaa")
        mList!!.add("--------")

        list.adapter?.notifyDataSetChanged()
    }


}