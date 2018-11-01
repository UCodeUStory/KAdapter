package com.ustory.koinsample.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ustory.kadapter.Item
import com.ustory.kadapter.MultiDataCreater
import com.ustory.kadapter.MultiLayoutCreater
import java.lang.ref.WeakReference
import kotlin.collections.ArrayList


abstract class KotlinAdapter<T> : RecyclerView.Adapter<KotlinAdapter.ViewHolder>() {

    /**
     * 添加单个布局
     */
    fun layout(layoutId: () -> Int) {
        mLayout = layoutId()
        mLayoutIds.put(mLayout, mLayout)
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
        if (mLayoutIds.size > 0) {
            var firstKey = mLayoutIds.keys.first()
            mLayout = mLayoutIds[firstKey]!!
        }
    }

    /**
     * 默认以id为类型key 批量添加
     */
    fun multiLayout(layoutIds: ArrayList<Int>) {
        layoutIds.forEach {
            mLayoutIds.put(it, it)
        }

        if (mLayoutIds.size > 0) {
            var firstKey = mLayoutIds.keys.first()
            mLayout = mLayoutIds[firstKey]!!
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

    private var mItemDatas: MutableList<Item<T>> = arrayListOf()

    /**
     * 适配数据
     */
    fun data(datas: List<T>, initData:MultiDataCreater<T>.(T) -> Unit) {
        mDatas.clear()
        mOriginData = datas
        var creator = MultiDataCreater<T>()
        datas.forEach {
            creator.initData(it)
        }

        creator.dataAndTypes.forEach { (type, data) ->
            mDatas.add(Item(type = type,data = data))
        }

        mDatas.forEach {
            if (it.type == null) {
                it.type = mLayout//最后一个
            }
            mTypes.add(it.type!!)
        }
    }

    /**
     * 仅更新数据，单布局使用此方法，如果和类型数据不设置，默认用最后一个设置的layout
     */
    fun data(datas: () -> ArrayList<*>) {
        mDatas.clear()
        var tempdatas = datas() as ArrayList<T?>
        mOriginData = tempdatas
        tempdatas.forEach {
            mDatas.add(Item(data = it, type = mLayout))
            mTypes.add(mLayout)
        }


    }

    /**
     * 新增单个数据在尾部
     * notice 多布局调用不起作用
     */
    fun addData(data: T) {
        if (!isMultiLayoutMode()) {
            mDatas.add(Item(data = data, type = mLayout))
            mTypes.add(mLayout)
        }
    }

    /**
     * 新增mutilType数据在尾部
     */
    fun addData(type: Int, data: T) {
        mDatas.add(Item(data = data, type = type))
        mTypes.add(type)
    }

    /**
     * 指定位置添加数据
     */
    fun addData(index: Int, type: Int, data: T) {
        mDatas.add(index,Item(data = data, type = type))
        mTypes.add(index,type)
    }

    /**
     * 指定位置添加数据
     */
    fun addOtherData(index: Int, type: Int, backupData:Any) {
        mDatas.add(index,Item( type = type,backupData = backupData))
        mTypes.add(index,type)
    }

    /**
     * 新增mutilType数据在尾部
     * notice 多个布局模式调用时，会显示第一个布局的样式
     *
     */
    fun addDatas(datas: ArrayList<T>) {
        if (!isMultiLayoutMode()) {
            datas.forEach {
                mDatas.add(Item(data = it, type = mLayout))
                mTypes.add(mLayout)
            }
        }
    }


    /**
     * 当我们已经定义好大部分要绑定的数据是，只是个别的需要单独设置，我们可以通过这个方法拦截，backupData作为备份数据，也就是其他少量布局数据
     */
    fun bindData(type: Int, interceptBind: (type: Int, vh: ViewHolder, data: T?, backupData: Any?) -> Unit) {
        interceptViews.put(type, interceptBind)
    }

    /**
     * 判断此类型布局是否被拦截
     */
    fun isIntercept(itemViewType: Int): Boolean {
        return interceptViews.containsKey(itemViewType)
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

    private fun isMultiLayoutMode(): Boolean {
        if (mLayoutIds.size < 0) {
            error("请至少设置一个布局")
        }
        return mLayoutIds.size > 1
    }

    private var mLayout: Int = 0

    private var mOriginData: List<T?> = arrayListOf()

    private var mDatas: MutableList<Item<T>> = arrayListOf()

    private var mHeaderView: WeakReference<View>? = null

    private var mHeaderLayoutId: Int? = null

    private var mFooterView: WeakReference<View>? = null

    private var mFooterLayoutId: Int? = null

    private lateinit var mBind: ((type: Int, vh: ViewHolder, data: T) -> Unit?)

    private var mBindInterceptView: ((type: Int, vh: ViewHolder, data: T?, backupData: Any?) -> Unit?)? = null

    private lateinit var mBindHeader: (view: View) -> Unit

    private lateinit var mBindFooter: (view: View) -> Unit

    lateinit var layoutInflater: LayoutInflater
    //处理多类型布局
    private var mLayoutIds: MutableMap<Int, Int> = mutableMapOf()
    //处理多类型Type
    private var mTypes: ArrayList<Int> = ArrayList()

    private var mDataWithTypes: ArrayList<Pair<Int, T?>> = arrayListOf()
    /** 保存拦截的类型和处理函数**/
    private var interceptViews: MutableMap<Int, Any> = mutableMapOf()

    private var mOnItemClickListener: ((position: Int, view: View) -> Unit)? = null

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

        if (mTypes.contains(type) && mLayoutIds[type] != null) {
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
            if (isIntercept(getItemViewType(position))) {
                mBindInterceptView = interceptViews.get(getItemViewType(position)) as ((type: Int, vh: ViewHolder, data: T?, backupData: Any?) -> Unit?)?
                if (mBindInterceptView != null) {
                    mBindInterceptView?.invoke(getItemViewType(position), vh, mDatas.get(calculatePosition).data, mDatas.get(calculatePosition).backupData)
                }
            } else {
                mDatas.get(calculatePosition).data?.let { mBind(getItemViewType(position), vh, it) }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (isHead(position)) {
            return HEAD_TYPE
        } else if (isFoot(position)) {
            return FOOT_TYPE
        } else if (mTypes.size > 0) {//单个布局这个集合为0
            return GetType(position)
        }
        return super.getItemViewType(position)
    }

    private fun GetType(position: Int): Int {
        var p = position
        if (mHeaderLayoutId != null || mHeaderView != null) {
            p = if (position > 0) position - 1 else position
        }
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

