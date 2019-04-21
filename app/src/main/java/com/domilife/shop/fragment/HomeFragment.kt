package com.domilife.shop.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.domilife.shop.Constants
import com.domilife.shop.R
import com.domilife.shop.activity.*
import com.domilife.shop.base.BaseFragment
import com.domilife.shop.bean.HomePageBean
import com.domilife.shop.bean.InCodeBean
import com.domilife.shop.bean.ShopGallaryBean
import com.domilife.shop.net.RetrofitManager
import com.domilife.shop.utils.GlideApp
import com.domilife.shop.utils.Preference
import com.google.gson.Gson
import com.stx.xhb.xbanner.XBanner
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * Created by janrone on 2019/3/16.
 */
class HomeFragment : BaseFragment() {

    private var mTitle: String? = null

    var cannext by Preference("cannext", false)

    companion object {
        fun getInstance(title: String): HomeFragment {
            val fragment = com.domilife.shop.fragment.HomeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {

        tv_bot2.setOnClickListener {
            startActivity(Intent(activity, ShopInfoMainActivity::class.java))
        }

        ll_jyfx.setOnClickListener {

            if (canNext())
                startActivity(Intent(activity, JingyingFenxiActivity::class.java))
        }

        ll_product.setOnClickListener {

            if (canNext())
                startActivity(Intent(activity, ProductShelfActivity::class.java))
        }

        ll_member.setOnClickListener {

            if (canNext())
                startActivity(Intent(activity, MemberActivity::class.java))
        }

        ll_yingxiao.setOnClickListener {

            if (canNext())
                startActivity(Intent(activity, YingXiaoActivity::class.java))
        }

        ll_order.setOnClickListener {

            if (canNext())
                startActivity(Intent(activity, OrderActivity::class.java))
        }

        ll_sk.setOnClickListener {

            if (canNext())
                startActivity(Intent(activity, QRCodeActivity::class.java))
        }

        ll_scan.setOnClickListener {

            if (canNext())
                startActivity(Intent(activity, ScanActivity::class.java))
        }

        iv_close.setOnClickListener {
            shop_toast.visibility = View.GONE
        }

        if (Preference.contains("incodebean")) {

            var incodebean by Preference("incodebean", "")
            var inCodeBean: InCodeBean = Gson().fromJson(incodebean, InCodeBean::class.java)

            var cannext by Preference("cannext", false)

            if (inCodeBean?.isCompQuality != 1 || inCodeBean?.isCompShopMsg != 1) {
                tv_bot2.visibility = View.VISIBLE
                cannext = false
            } else {
                tv_bot2.visibility = View.GONE
                cannext = true
            }

        }


        banner.loadImage(XBanner.XBannerAdapter { banner, model, view, position ->
            //在此处使用图片加载框架加载图片，demo中使用glide加载，可替换成自己项目中的图片加载框架
            val shopGallaryBean = model as ShopGallaryBean
            GlideApp.with(this).load(shopGallaryBean.pic).into(view as ImageView)
        })



        getHomePageData()
    }

    private fun canNext(): Boolean {
        if (!cannext) {
            activity?.toast("开店资料未提交，点击继续提交")
            //return false
            return true
        }
        return true
    }

    /**
     * at nanjing
     * get home page data
     * 2019.4.17
     */
    private fun getHomePageData() {
        //shophomepage
        var accountId by Preference("accountId", "")
        var params = HashMap<String, String>()
        params.put("action", "shophomepage")
        params.put("accountId", accountId)
        RetrofitManager.service.baseRequest(params)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            Log.d(Constants.TAG, result.toString())
                            if (result.code == 1) {
                                var homePageBean: HomePageBean = Gson().fromJson(result.data.toString(), HomePageBean::class.java)
                                if (homePageBean?.isCompQuality != 1 || homePageBean?.isCompShopMsg != 1) {
                                    tv_bot2.visibility = View.VISIBLE
                                    cannext = false
                                } else {
                                    tv_bot2.visibility = View.GONE
                                    cannext = true
                                }

                                //取出banner 显示的 img 和 Title
//                                Observable.fromIterable(homePageBean.shopGallarys)
//                                        .subscribe { list ->
//                                            bannerImgs.add(list?.pic?: "")
//                                            bannerTitleList.add(list?.name ?: "")
//                                        }
                                //刷新数据之后，需要重新设置是否支持自动轮播
                                banner.setAutoPlayAble(homePageBean.shopGallarys.size > 1);
                                banner.setBannerData(homePageBean.shopGallarys)

                                banner.setOnItemClickListener(XBanner.OnItemClickListener {
                                    banner, model, view, position ->
                                    var intent = Intent(activity, DmWebActivity::class.java)
                                    intent.putExtra("url", homePageBean.shopGallarys[position].linkUr)
                                    startActivity(intent)
                                })

                                val toasts = ArrayList<String>()
                                Observable.fromIterable(homePageBean.showToasts)
                                        .subscribe { list ->
                                            toasts.add(list?.title?: "")
                                        }
                                marqueeView.startWithList(toasts)
                                marqueeView.setOnItemClickListener {
                                    position, textView ->
                                    var intent = Intent(activity, DmWebActivity::class.java)
                                    intent.putExtra("url", homePageBean.showToasts[position].linkUrl)
                                    startActivity(intent)

                                }
                            } else {
                                activity?.toast(result.msg)
                            }
                        },
                        { error ->
                            Log.d(Constants.TAG, error.toString())

                        }

                )

    }


}

