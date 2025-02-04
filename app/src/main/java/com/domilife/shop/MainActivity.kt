package com.domilife.shop

import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.domilife.shop.R
import com.domilife.shop.base.BaseActivity
import com.domilife.shop.bean.InCodeBean
import com.domilife.shop.bean.TabEntity
import com.domilife.shop.fragment.HomeFragment
import com.domilife.shop.fragment.MineFragment
import com.domilife.shop.fragment.SyijingFragment
import com.domilife.shop.fragment.ZbenFragment
import com.domilife.shop.utils.Preference
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun initView() {

    }

    override fun initData() {

        //if(inCodeBean?.isCompQuality == 1) {
        //if(inCodeBean?.isCompShopMsg == 1) {

    }


    private val mTitles = arrayOf("首页", "账本", "生意经", "我的")

    // 未被选中的图标
    private val mIconUnSelectIds = intArrayOf(R.mipmap.ic_home_n, R.mipmap.ic_home_zb_n, R.mipmap.ic_syj_n, R.mipmap.ic_mine_n)
    // 被选中的图标
    private val mIconSelectIds = intArrayOf(R.mipmap.ic_home_p, R.mipmap.ic_home_zb_p, R.mipmap.ic_syj_p, R.mipmap.ic_mine_p)

    private val mTabEntities = ArrayList<CustomTabEntity>()

    private var mHomeFragment: HomeFragment? = null
    private var mZbenFragment: ZbenFragment? = null
    private var mSyijingFragment: SyijingFragment? = null
    private var mMineFragment: MineFragment? = null

    private var mIndex = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            mIndex = savedInstanceState.getInt("currTabIndex")
        }
        initTab()
        tab_layout.currentTab =mIndex
        switchFragment(mIndex)

    }

    override fun layoutId(): Int {
        return R.layout.activity_main
    }


    //初始化底部菜单
    private fun initTab() {
        (0 until mTitles.size)
                .mapTo(mTabEntities) { TabEntity(mTitles[it], mIconSelectIds[it], mIconUnSelectIds[it]) }
        //为Tab赋值
        tab_layout.setTabData(mTabEntities)
        tab_layout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                //切换Fragment
                switchFragment(position)
            }

            override fun onTabReselect(position: Int) {

            }
        })
    }

    /**
     * 切换Fragment
     * @param position 下标
     */
    private fun switchFragment(position: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        when (position) {
            0 // 首页
            -> mHomeFragment?.let {
                transaction.show(it)
            } ?: HomeFragment.getInstance(mTitles[position]).let {
                mHomeFragment = it
                transaction.add(R.id.fl_container, it, "home")
            }
            1  //账本
            -> mZbenFragment?.let {
                transaction.show(it)
            } ?: ZbenFragment.getInstance(mTitles[position]).let {
                mZbenFragment = it
                transaction.add(R.id.fl_container, it, "zben") }
            2  //生意经
            -> mSyijingFragment?.let {
                transaction.show(it)
            } ?: SyijingFragment.getInstance(mTitles[position]).let {
                mSyijingFragment = it
                transaction.add(R.id.fl_container, it, "syijing") }
            3 //我的
            -> mMineFragment?.let {
                transaction.show(it)
            } ?: MineFragment.getInstance(mTitles[position]).let {
                mMineFragment = it
                transaction.add(R.id.fl_container, it, "mine") }

            else -> {

            }
        }

        mIndex = position
        tab_layout.currentTab = mIndex
        transaction.commitAllowingStateLoss()
    }


    /**
     * 隐藏所有的Fragment
     * @param transaction transaction
     */
    private fun hideFragments(transaction: FragmentTransaction) {
        mHomeFragment?.let { transaction.hide(it) }
        mZbenFragment?.let { transaction.hide(it) }
        mSyijingFragment?.let { transaction.hide(it) }
        mMineFragment?.let { transaction.hide(it) }
    }
}
