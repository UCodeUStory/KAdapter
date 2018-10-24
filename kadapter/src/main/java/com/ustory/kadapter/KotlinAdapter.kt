package com.ustory.koinsample.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ustory.kadapter.MultiLayoutCreater
import java.lang.ref.WeakReference
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


abstract class KotlinAdapter<T> : RecyclerView.Adapter<KotlinAdapter.ViewHolder>() {

    /**
     * 添加单个布局
     */
    fun layout(layoutId: () -> Int) {
        mLayout = layoutId()
    }


    /**
     * 添加单个布局
     */
    fun singleLayout(layoutId: () -> Int) {
        mLayout = layoutId()
        mLayoutIds.put(mLayout, mLayout)
    }

    /**
     * 批量添加一个map
     */
    fun multiLayout(layoutIds: MutableMap<Int, Int>) {
        mLayoutIds = layoutIds
    }

    /**
     * 默认以id为类型key 批量添加
     */
    fun multiLayout(layoutIds: ArrayList<Int>) {
        layoutIds.forEach {
            mLayoutIds.put(it, it)
        }
    }

    /**
     * 默认以id为类型key
     */
    fun multiLayout(initLayout: MultiLayoutCreater.() -> Unit) {
        val creator = MultiLayoutCreater()
        creator.initLayout()
        var firstKey = creator.getValue().keys.first()
        mLayout = creator.getValue().get(firstKey)!!
        mLayoutIds = creator.getValue()
    }


    /**
     * 添加带类型的数据
     */
    fun dataWithType(datasFun: () -> MutableMap<Int, T>) {
        mDataWithTypes = datasFun()
        mDataWithTypes.forEach { key, data ->
            mTypes.add(key)
            mDatas.add(data)
        }
    }

    /**
     * 仅更新数据，如果和类型数据不设置，默认用最后一个设置的layout
     */
    fun data(datas: () -> ArrayList<*>) {
        mDatas = datas() as ArrayList<T>
    }

    /**
     * 仅更新类型，如果和数据数量不一致会有问题
     */
    fun type(types: () -> ArrayList<Int>) {
        mDatas ?: error("请先初始化数据")
        mTypes = types()
        for (i in mTypes.indices) {
            mDataWithTypes.put(mTypes[i], mDatas[i])
        }
    }

    fun bindData(bind: (type: Int, vh: ViewHolder, data: T) -> Unit) {
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

    private var mHeaderView: WeakReference<View>? = null

    private var mHeaderLayoutId: Int? = null

    private var mFooterView: WeakReference<View>? = null

    private var mFooterLayoutId: Int? = null

    private lateinit var mBind: ((type: Int, vh: ViewHolder, data: T) -> Unit?)

    private lateinit var mBindHeader: (view: View) -> Unit

    private lateinit var mBindFooter: (view: View) -> Unit

    lateinit var layoutInflater: LayoutInflater
    //处理多类型布局
    private var mLayoutIds: MutableMap<Int, Int> = mutableMapOf()
    //处理多类型Type
    private var mTypes: ArrayList<Int> = ArrayList()

    private var mDataWithTypes: MutableMap<Int, T> = mutableMapOf()

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): ViewHolder {
        Log.i("KotlinAdapter", "onBindViewHolder=" + type)
        val inflater = LayoutInflater.from(parent.context)

        when (type) {
            HEAD_TYPE -> {
                if (mHeaderLayoutId != null) {
                    return ViewHolder(inflater.inflate(mHeaderLayoutId!!, parent, false))
                }

            }

            FOOT_TYPE -> {
                if (mFooterLayoutId != null) {
                    return ViewHolder(inflater.inflate(mFooterLayoutId!!, parent, false))
                }
            }
        }

        if (mDataWithTypes[type] != null) {
            return ViewHolder(mLayoutIds[type]?.let {
                inflater.inflate(it, parent, false)
            }!!)
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
        if (getItemViewType(position) == HEAD_TYPE) {
            mBindHeader(vh.itemView)
        } else if (getItemViewType(position) == FOOT_TYPE) {
            mBindFooter(vh.itemView)
        } else {
            var calculatePosition = position
            if (mHeaderView != null || mHeaderLayoutId != null) {
                calculatePosition = position - 1
            }
            vh.itemView.setOnClickListener { view ->
                mOnItemClickListener?.invoke(calculatePosition, view)
            }
            mBind(getItemViewType(position), vh, mDatas.get(calculatePosition))
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (isHead(position)) {
            return HEAD_TYPE
        } else if (isFoot(position)) {
            return FOOT_TYPE
        } else if (mTypes.size > 0) {
            return GetType(position)
        }
        return super.getItemViewType(position)
    }

    private fun GetType(position: Int): Int {
        val p = if (position > 0) position - 1 else position
        return mTypes.get(p)
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
        private var mOnItemClickListener: ((position: Int, view: View) -> Unit)? = null

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

