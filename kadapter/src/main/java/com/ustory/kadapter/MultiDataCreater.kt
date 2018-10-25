package com.ustory.kadapter

/**
 * 封装添加数据
 */
class MultiDataCreater<T> {

    /**
     * 存入位置和类型
     */
    var updateTypes: MutableMap<Int, UpdateModel> = hashMapOf()

    var insertTypes: MutableList<InsertModel> = arrayListOf()

    /**
     * 更新某位置数据类型
     */
    fun update(targetPosition: Int, type: Int) {
        updateTypes.put(targetPosition, UpdateModel(type))
    }
    /**
     * 更新某位置数据类型
     */
    fun update(targetPosition: IntRange, type: Int) {
        targetPosition.forEach {
            updateTypes.put(it, UpdateModel(type))
        }
    }
    /**
     * 插入某位置后数据，并制定类型
     */
    fun insert(insertPosition: Int, type: Int, data: Any) {
        insertTypes.add(InsertModel(insertPosition, type, data))
    }


}

class UpdateModel(var type: Int)
/**
 * insertPosition 插入位置
 * type item类型
 * backupData 备用数据
 */
class InsertModel(var insertPosition: Int, var type: Int, var backupData: Any)
