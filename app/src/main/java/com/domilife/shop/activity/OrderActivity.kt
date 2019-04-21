package com.domilife.shop.activity

import android.content.Context
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.domilife.shop.base.BaseActivity
import kotlinx.android.synthetic.main.activity_order.*
import com.umeng.socialize.UMShareAPI
import android.content.Intent
import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import com.domilife.shop.R
import com.domilife.shop.fragment.OrderFragment


class OrderActivity : BaseActivity() {


    override fun layoutId(): Int {
        return R.layout.activity_order
    }


    override fun initView() {
        toolbar?.setNavigationOnClickListener { finish() }
        setTab()
        //setItem()
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
        val tab1 = OrderFragment()
        val tab2 = OrderFragment()
        val tab3 = OrderFragment ()
        var list = listOf<Fragment>(tab1, tab2, tab3)

        val titles = listOf(" 待发货 ", " 已发货 ", "已失效")
        viewpager?.adapter = TitleFragmentPagesAdapter(this@OrderActivity,list, supportFragmentManager, titles)
        tabs.setupWithViewPager(viewpager)
        for (i in 0 until tabs.tabCount){
            tabs.getTabAt(i)?.setCustomView((viewpager?.adapter as TitleFragmentPagesAdapter).getTabView(i))
            tabs?.setTabIndicatorFullWidth(false)
        }

    }

    //设置条目
    fun setItem() {
        val list = listOf(" 待发货 ", " 已发货 ", "已失效")
        tabs.getTabAt(0)?.text = list[0]
        tabs.getTabAt(1)?.text = list[1]

    }


    class TitleFragmentPagesAdapter(var context: Context, var fmList: List<Fragment>, fm: FragmentManager?, val list:List<String>) : FragmentStatePagerAdapter(fm) {

        override fun getItem(p0: Int) = fmList[p0]

        override fun getCount() = fmList.size

        open fun getTabView(position: Int): View {
            val view = LayoutInflater.from(context).inflate(R.layout.item_tab_head, null)
            val textView = view.findViewById<TextView>(R.id.tv)
            textView.setText(list[position])
            return view
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data)
    }



}


private operator fun TabLayout.get(i: Any) {

}
