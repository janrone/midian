package com.domilife.shop.activity

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.domilife.shop.R
import com.domilife.shop.base.BaseActivity
import com.domilife.shop.fragment.JingYingFragment
import com.domilife.shop.fragment.ProductFragment
import kotlinx.android.synthetic.main.activity_product.*

class ProductActivity : BaseActivity() {


    override fun layoutId(): Int {
        return R.layout.activity_product
    }


    override fun initView() {
        toolbar?.setNavigationOnClickListener { finish() }
        setTab()
        setItem()
    }


    override fun initData() {
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                tabs.getTabAt(tab.position)?.select()
                //当tab被选中，改变tab
                viewpager.setCurrentItem(tab.position, true)
            }

            //当tab被选中，改变viewpage实现同步
            override fun onTabReselected(tab: TabLayout.Tab) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
        })


        viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
    }

    //设置Tab
    private fun setTab() {
        val tab1 = ProductFragment()
        val tab2 = ProductFragment()
        var list = listOf<Fragment>(tab1, tab2)

        viewpager?.adapter = TitleFragmentPagesAdapter(list, supportFragmentManager)
        tabs.setupWithViewPager(viewpager)

    }

    //设置条目
    fun setItem() {
        //
        val list = listOf<String>(" 上架中 ", " 已下架 ")
        tabs.getTabAt(0)?.text = list[0]
        tabs.getTabAt(1)?.text = list[1]

    }


    class TitleFragmentPagesAdapter(var fmList: List<Fragment>, fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {

        override fun getItem(p0: Int) = fmList[p0]

        override fun getCount() = fmList.size

    }



}