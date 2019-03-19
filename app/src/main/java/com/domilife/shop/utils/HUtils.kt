package com.midian.shop.utils

import java.util.regex.Pattern

/**
 * Created by janrone on 2019/3/19.
 */
open class HUtils {
    public fun checkPhoneNum(num: String): Boolean{
        val regExp = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(14[5-9])|(166)|(19[8,9])|)\\d{8}$"
        val p = Pattern.compile(regExp)
        val m = p.matcher(num)
        return m.matches()
    }
}