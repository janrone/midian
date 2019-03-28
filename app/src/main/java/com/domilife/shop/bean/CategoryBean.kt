package com.domilife.shop.bean

import android.os.Parcelable
import com.flyco.tablayout.listener.CustomTabEntity
import kotlinx.android.parcel.Parcelize


@Parcelize
class CategoryBean (val bigCateName:String, val categoryItem:List<CategoryItemBean>) : Parcelable
//data class ForecastResult(val city: City, val list: List<Forecast>) bigCateItem