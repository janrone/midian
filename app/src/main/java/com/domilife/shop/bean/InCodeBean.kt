package com.domilife.shop.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


//accountId	Integer	用户ID
//isBindInvNo	Integer	是否已经绑定邀请码 0 否 1是
//isCompShopMsg	Integer	是否已经输入店铺信息 0 没有 1 绑定
//isCompQuality	Integer	是否已经输入资质信息 0 没有 1 绑定
//isShopPassed	Integer	是否店铺已经通过审核
@Parcelize
class InCodeBean (val accountId:Int,val isBindInvNo:Int, var isCompQuality:Int,
                        var isCompShopMsg:Int, var isShopPassed:Int) : Parcelable