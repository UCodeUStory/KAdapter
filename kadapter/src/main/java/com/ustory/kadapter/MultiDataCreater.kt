package com.ustory.kadapter

/**
 * 封装添加数据
 */
class MultiDataCreater<T> {

    var dataAndTypes:MutableList<Pair<Int,T>> = arrayListOf()

    fun addData(type:Int,data:T){
        dataAndTypes.add(type to data)
    }


}
