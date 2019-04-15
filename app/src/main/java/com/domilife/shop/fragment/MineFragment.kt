package com.domilife.shop.fragment

import android.content.Intent
import android.os.Bundle
import com.domilife.shop.R
import com.domilife.shop.activity.*
import com.domilife.shop.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_mine.*

/**
 * Created by janrone on 2019/3/16.
 */
class MineFragment : BaseFragment() {

    private var mTitle :String? = null

    companion object {
        fun getInstance(title: String): MineFragment {
            val fragment = com.domilife.shop.fragment.MineFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_mine
    }

    override fun initView() {

        rl_pwd.setOnClickListener {
            var intent = Intent(activity, EditPwdActivity::class.java)
            intent.putExtra("type",1)
            startActivity(intent)
        }

        rl_tx.setOnClickListener {
            startActivity(Intent(activity, EditPwdActivity::class.java))
        }

        rl_kf.setOnClickListener {
            startActivity(Intent(activity, ContentActivity::class.java))
        }

        tv_to_tx.setOnClickListener {
            startActivity(Intent(activity, TiXianActivity::class.java))
        }

        rl_txjl.setOnClickListener {
            startActivity(Intent(activity, TiXianListActivity::class.java))
        }

        rl_txjl.setOnClickListener {
            startActivity(Intent(activity, TiXianListActivity::class.java))
        }
    }
}