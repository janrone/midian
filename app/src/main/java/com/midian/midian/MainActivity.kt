package com.midian.midian

import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.hazz.kotlinmvp.mvp.model.bean.TabEntity
import com.midian.midian.base.BaseActivity
import com.midian.midian.fragment.HomeFragment
import com.midian.midian.fragment.MineFragment
import com.midian.midian.fragment.SyijingFragment
import com.midian.midian.fragment.ZbenFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {


    private val mTitles = arrayOf("首页", "账本", "生意经", "我的")

    // 未被选中的图标
    private val mIconUnSelectIds = intArrayOf(R.mipmap.ic_home_normal, R.mipmap.ic_discovery_normal, R.mipmap.ic_hot_normal, R.mipmap.ic_mine_normal)
    // 被选中的图标
    private val mIconSelectIds = intArrayOf(R.mipmap.ic_home_selected, R.mipmap.ic_discovery_selected, R.mipmap.ic_hot_selected, R.mipmap.ic_mine_selected)

    private val mTabEntities = ArrayList<CustomTabEntity>()

    private var mHomeFragment: HomeFragment? = null
    private var mZbenFragment: ZbenFragment? = null
    private var mSyijingFragment: SyijingFragment? = null
    private var mMineFragment: MineFragment? = null

    private var mIndex = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            mIndex = savedInstanceState.getInt("currTabIndex")
        }
        super.onCreate(savedInstanceState)
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
