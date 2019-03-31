package com.domilife.shop.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.domilife.shop.Constants
import com.domilife.shop.R
import com.domilife.shop.adapter.OneAdapter
import com.domilife.shop.adapter.TwoAdapter
import com.domilife.shop.base.BaseActivity
import com.domilife.shop.bean.BigCateBean
import com.domilife.shop.bean.OneBean
import com.domilife.shop.bean.TwoBean
import com.domilife.shop.net.RetrofitManager
import com.domilife.shop.utils.RecycUtil
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_category.*


class ShopCategoryActivity : BaseActivity() {

    lateinit var oneLayoutM: LinearLayoutManager
    lateinit var twoLayoutM: LinearLayoutManager

    lateinit var oneAdapter: OneAdapter
    lateinit var twoAdapter: TwoAdapter

    lateinit var oneitemD: DividerItemDecoration
    lateinit var twoitemD: DividerItemDecoration

    var oneData: MutableList<OneBean> = mutableListOf()
    var twoData: MutableList<TwoBean> = mutableListOf()

    var oneIndex = -1

    lateinit var handler: Handler
    var rightClick: Boolean = false


    override fun layoutId(): Int {
        return R.layout.activity_category
    }

    override fun initView() {

        toolbar?.setNavigationOnClickListener { finish() }

        oneLayoutM = LinearLayoutManager(this)
        oneLayoutM.orientation = LinearLayoutManager.VERTICAL

        twoLayoutM = LinearLayoutManager(this)
        oneLayoutM.orientation = LinearLayoutManager.VERTICAL

        recyc_one.layoutManager = oneLayoutM
        recyc_two.layoutManager = twoLayoutM

        oneitemD = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        twoitemD = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

        //ecyc_one.addItemDecoration(oneitemD)
        recyc_two.addItemDecoration(twoitemD)

        oneAdapter = OneAdapter(R.layout.item_one, oneData)
        twoAdapter = TwoAdapter(R.layout.item_two, twoData)
        recyc_one.adapter = oneAdapter
        recyc_two.adapter = twoAdapter


        oneAdapter.setOnItemClickListener { adapter, view, position ->

            rightClick = true
            //点击变色
            select(position)

            //点击右侧滚动
            //判断右侧滚动到哪里
            var twoI = 0
            while (twoI < twoData.size) {
                if (twoData.get(twoI).id == oneData.get(position).id) {
                    break;
                }
                twoI++
            }

            RecycUtil.moveToPositAndTop(twoI, twoLayoutM, recyc_two, handler)
        }

        twoAdapter.setOnItemClickListener { adapter, view, position ->
            var item = adapter.getItem(position) as TwoBean
            var intent = Intent()
            intent.putExtra("kind", item.name)
            intent.putExtra("serRate", item.servRate)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

//        recyc_two.addOnScrollListener(object : RecyclerView.OnScrollListener(){
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//            }
//
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//            }
//        })

        recyc_two.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                //切断子母列表循环联调
                if (rightClick == false && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //判断当前子列表显示哪个id的内容
                    var now = 0
                    var first = twoLayoutM.findFirstVisibleItemPosition()
                    if (twoData.get(first).isTitle) {
                        now = twoData.get(first).id
                    } else {
                        if (twoData.get(first).id + 1 > oneData.get(oneData.size - 1).id) {
                            now = twoData.get(first).id
                        } else {
                            now = twoData.get(first).id + 1
                        }
                    }
                    //滚动主列表
                    RecycUtil.moveToPositAndCenter(now, oneLayoutM, recyc_one, handler)
                    select(now)

                } else if (rightClick == true && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    rightClick = false
                } else if (rightClick == true && newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    rightClick = false
                }
            }
        })

    }

    override fun initData() {
        Log.d(Constants.TAG, "call init data")

        handler = Handler()
        try {
            mLoadingDialog?.show()
            var param = HashMap<String, String>()
            param.put("action", Constants.SHOPCATEGORY)
            param.put("accountId", "")

            RetrofitManager.service.baseRequest(param)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ result ->
                        mLoadingDialog!!.hide()
                        if (result.code == 1) {

                            Log.d(Constants.TAG, "set data")
                            var bigCateBean: BigCateBean = Gson().fromJson(result.data, BigCateBean::class.javaObjectType)

                            var i = 0
                            while (i < bigCateBean.bigCateItem.size) {
                                oneData.add(OneBean(i, bigCateBean.bigCateItem[i].bigCateName))
                                var j = 0
                                while (j < bigCateBean.bigCateItem[i].categoryItem.size) {
                                    if (j == 0) {
                                        twoData.add(TwoBean(i,
                                                bigCateBean.bigCateItem[i].categoryItem[j].categoryName,
                                                bigCateBean.bigCateItem[i].categoryItem[j].servRate, true))
                                    } else {
                                        twoData.add(TwoBean(i,
                                                bigCateBean.bigCateItem[i].categoryItem[j].categoryName,
                                                bigCateBean.bigCateItem[i].categoryItem[j].servRate, false))
                                    }
                                    j++
                                }
                                i++

                            }

                            oneAdapter.notifyDataSetChanged()
                            twoAdapter.notifyDataSetChanged()
                        }
                        Log.d(Constants.TAG, result.toString())
                    }, { error ->
                        mLoadingDialog?.hide()
                        toast(error.toString())
                        Log.d(Constants.TAG, error.toString())
                    }
                    )
        } catch (e: Exception) {
            mLoadingDialog?.hide()
            Log.d(Constants.TAG, e.toString())
        }

    }


    fun select(position: Int) {
        var i = oneAdapter.index
        oneAdapter.index = position
        if (i >= 0) {
            oneAdapter.notifyItemChanged(i)
        }
        oneAdapter.notifyItemChanged(position)
        Log.v("zzww", " i:" + i + " position:" + position)
    }


}