package com.domilife.shop.bean

import android.os.Parcelable
import com.flyco.tablayout.listener.CustomTabEntity
import kotlinx.android.parcel.Parcelize


@Parcelize
class BigCateBean (val bigCateItem: List<CategoryBean>) : Parcelable
//data class ForecastResult(val city: City, val list: List<Forecast>) bigCateItem