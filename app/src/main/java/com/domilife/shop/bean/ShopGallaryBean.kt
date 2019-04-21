package com.domilife.shop.bean

import android.os.Parcelable
import com.flyco.tablayout.listener.CustomTabEntity
import com.stx.xhb.xbanner.entity.BaseBannerInfo
import com.stx.xhb.xbanner.entity.SimpleBannerInfo
import kotlinx.android.parcel.Parcelize


@Parcelize
class ShopGallaryBean (val desc:String, var linkUr:String,var name:String,var pic:String) : Parcelable, SimpleBannerInfo() {
    override fun getXBannerUrl(): Any {
        return pic
    }

    override fun getXBannerTitle(): String {
        return  name
    }
}