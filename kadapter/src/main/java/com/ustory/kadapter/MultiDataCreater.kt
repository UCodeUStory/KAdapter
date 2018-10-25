package com.ustory.kadapter
/**
 * 封装添加数据
 */
class MultiDataCreater<T> {

    val values:ArrayList<Pair<Int, T?>> = arrayListOf()

    fun add(data:Pair<Int,T?>){
        values.add(data)
    }

    fun getValue(): ArrayList<Pair<Int, T?>> {
        return values
    }
}