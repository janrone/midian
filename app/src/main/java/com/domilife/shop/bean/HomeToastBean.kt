package com.domilife.shop.bean

import android.os.Parcelable
import com.flyco.tablayout.listener.CustomTabEntity
import kotlinx.android.parcel.Parcelize


@Parcelize
class HomeToastBean (val linkUrl:String, var title:String) : Parcelable