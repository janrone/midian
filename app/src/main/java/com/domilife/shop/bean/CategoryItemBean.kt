package com.domilife.shop.bean

import android.os.Parcelable
import com.flyco.tablayout.listener.CustomTabEntity
import kotlinx.android.parcel.Parcelize


@Parcelize
class CategoryItemBean (val categoryId:Int, val categoryName:String, var servRate:String) : Parcelable
