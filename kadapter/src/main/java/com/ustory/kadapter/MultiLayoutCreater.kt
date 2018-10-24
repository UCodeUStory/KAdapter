package com.ustory.kadapter

/**
 * 封装添加布局
 */
class MultiLayoutCreater {

    private var layoutMaps:MutableMap<Int,Int> = mutableMapOf()
    /**
     * 添加单个布局，以layoutId为type
     */
    fun layout(layoutId: () -> Int) {
        var id = layoutId()
        var type = id
        layoutMaps.put(type,id)
    }

    /**
     * 添加单个布局，自定义Type
     */
    fun layout(type: Int, layoutId: Int) {
        layoutMaps.put(type,layoutId)
    }

    fun getValue(): MutableMap<Int,Int> {
       return layoutMaps
    }

}