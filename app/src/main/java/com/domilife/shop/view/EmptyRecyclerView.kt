package com.domilife.shop.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.Log
import android.view.View

class EmptyRecyclerView : RecyclerView {

    private var emptyView: View? = null
    private var errorView: View? = null
    private var isShowError: Boolean = false

    private val observer = object : RecyclerView.AdapterDataObserver() {
        override fun onChanged() {
            checkIfEmpty()
            checkIfError()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            Log.i(TAG, "onItemRangeInserted$itemCount")
            checkIfEmpty()
            checkIfError()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            checkIfEmpty()
            checkIfError()
        }
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet,
                defStyle: Int) : super(context, attrs, defStyle)

    private fun checkIfEmpty() {
        if (emptyView != null && adapter != null) {
            val emptyViewVisible = adapter!!.itemCount == 0
            emptyView!!.visibility = if (emptyViewVisible) View.VISIBLE else View.GONE
            visibility = if (emptyViewVisible) View.GONE else View.VISIBLE
        }
    }

    private fun checkIfError() {
        if (errorView != null && isShowError) {
            emptyView?.visibility = View.GONE
            visibility = View.GONE
            emptyView?.visibility = View.VISIBLE
        }
    }

    override fun setAdapter(adapter: RecyclerView.Adapter<*>?) {
        val oldAdapter = getAdapter()
        oldAdapter?.unregisterAdapterDataObserver(observer)
        super.setAdapter(adapter)
        adapter?.registerAdapterDataObserver(observer)

        checkIfEmpty()
        checkIfError()
    }

    //设置没有内容时，提示用户的空布局
    fun setEmptyView(emptyView: View) {
        this.emptyView = emptyView
        checkIfEmpty()
    }

    //设置没有内容时，提示用户的空布局
    fun setErrorView(errorView: View) {
        this.errorView = errorView
        checkIfError()
    }

    fun isShowError(isShowError: Boolean){
        this.isShowError = isShowError
    }

    companion object {
        private const val TAG = "EmptyRecyclerView"
    }

}