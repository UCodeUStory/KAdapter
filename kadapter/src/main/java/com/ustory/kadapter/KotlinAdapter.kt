package com.ustory.koinsample.Adapter

import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.*


abstract class KotlinAdapter<T> : RecyclerView.Adapter<KotlinAdapter.ViewHolder>() {

    fun layout(layoutId: () -> Int) {
        mLayout = layoutId()
    }

    fun data(datas: () -> ArrayList<*>) {
        mDatas = datas() as ArrayList<T>
    }

    fun bindData(bind: (vh: ViewHolder, data: T) -> Unit) {
        mBind = bind
    }

    fun onItemClick(itemClickFunction: (position: Int, view: View) -> Unit) {
        mOnItemClickListener = itemClickFunction
    }

    fun header(view: View, bindHeader: (view: View) -> Unit) {
        bindHeader(view)
    }

    fun header(layoutId: Int, bindHeader: (view: View) -> Unit) {
        mHeaderLayoutId = layoutId
        mBindHeader = bindHeader
    }

    fun footer(layoutId: Int, bindFooter: (view: View) -> Unit) {
        mFooterLayoutId = layoutId
        mBindFooter = bindFooter
    }

    fun inflater(inflater: () -> LayoutInflater) {
        layoutInflater = inflater()
    }

    fun inflater(inflater: LayoutInflater, withInflater: KotlinAdapter<T>.() -> Unit) {
        layoutInflater = inflater
        withInflater()
    }

    fun inflater(context: Context, withContext: KotlinAdapter<T>.() -> Unit) {
        layoutInflater = LayoutInflater.from(context)
        withContext()
    }

    infix fun into(recyclerView: RecyclerView?) {
        recyclerView?.adapter = this
    }

    fun into(recyclerView: () -> RecyclerView) {
        recyclerView().adapter = this
    }

    private var mLayout: Int = 0

    private var mDatas: ArrayList<T> = arrayListOf()

    private var mHeaderView: View? = null

    private var mHeaderLayoutId: Int? = null

    private var mFooterView: View? = null

    private var mFooterLayoutId: Int? = null

    private lateinit var mBind: ((vh: ViewHolder, data: T) -> Unit?)

    private lateinit var mBindHeader: (view: View) -> Unit

    private lateinit var mBindFooter: (view: View) -> Unit

    lateinit var layoutInflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): ViewHolder {
        Log.i("info", "onBindViewHolder=" + type)
        val inflater = LayoutInflater.from(parent.context)

        when (type) {
            HEAD_TYPE -> {
                if (mHeaderView != null) {
                    return ViewHolder(mHeaderView!!)
                }
                if (mHeaderLayoutId != null) {
                    return ViewHolder(inflater.inflate(mHeaderLayoutId!!, parent, false))
                }

            }

            FOOT_TYPE -> {
                if (mFooterView != null) {
                    return ViewHolder(mFooterView!!)
                }
                if (mFooterLayoutId != null) {
                    return ViewHolder(inflater.inflate(mFooterLayoutId!!, parent, false))
                }
            }
        }
        return ViewHolder(inflater.inflate(mLayout, parent, false))
    }

    override fun getItemCount(): Int {
        var count = mDatas.size
        if (mHeaderLayoutId != null || mHeaderView != null) {
            count++
        }
        if (mFooterLayoutId != null || mFooterView != null) {
            count++
        }
        return count
    }

    override fun onBindViewHolder(vh: ViewHolder, position: Int) {
        Log.i("info", "onBindViewHolder=" + position)
        if (getItemViewType(position) == BODY_TYPE) {
            var calculatePosition = position
            if (mHeaderView != null || mHeaderLayoutId != null) {
                calculatePosition = position - 1
            }
            vh.itemView.setOnClickListener { view ->
                mOnItemClickListener(calculatePosition, view)
            }
            mBind(vh, mDatas.get(calculatePosition))
        } else if (getItemViewType(position) == HEAD_TYPE) {
            mBindHeader(vh.itemView)
        } else if (getItemViewType(position) == FOOT_TYPE) {
            mBindFooter(vh.itemView)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (isHead(position)) {
            return HEAD_TYPE
        } else if (isFoot(position)) {
            return FOOT_TYPE
        } else {
            return BODY_TYPE
        }
        return super.getItemViewType(position)
    }

    private fun isFoot(position: Int): Boolean {
        if (position == itemCount - 1) {
            if (mFooterLayoutId != null || mFooterView != null) {
                return true
            }
        }
        return false
    }

    private fun isHead(position: Int): Boolean {
        if (position == 0) {
            if (mHeaderLayoutId != null || mHeaderLayoutId != null) {
                return true
            }
        }
        return false
    }

    companion object {
        private lateinit var mOnItemClickListener: (position: Int, view: View) -> Unit

        val HEAD_TYPE = 1
        val FOOT_TYPE = 2
        val BODY_TYPE = 3
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var views = SparseArray<View>()

        fun <T : View> bindView(id: Int): T {
            if (views.get(id) == null) {
                var view = itemView.findViewById<View>(id)
                views.put(id, view)
                return view as T
            } else {
                return views.get(id) as T
            }
        }

    }


}

