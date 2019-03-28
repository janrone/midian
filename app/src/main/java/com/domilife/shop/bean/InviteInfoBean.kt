package com.domilife.shop.bean

import android.os.Parcelable
import com.flyco.tablayout.listener.CustomTabEntity
import kotlinx.android.parcel.Parcelize


//accountId	Integer	用户ID
@Parcelize
class InviteInfoBean (val headUrl:String, val nickName:String) : Parcelable