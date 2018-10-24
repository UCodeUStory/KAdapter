package com.ustory.kadapterdemo.adapter

import android.widget.TextView
import com.ustory.kadapterdemo.Menu
import com.ustory.kadapterdemo.R
import com.ustory.koinsample.Adapter.KAdapterFactory
import com.ustory.koinsample.Adapter.KotlinAdapter

/**
 * 定义一个adapter就这么简单
 */
var mutilAdapter: KotlinAdapter<Menu> = KAdapterFactory.KAdapter {

    multiLayout {
        layout {
            R.layout.list_item
        }
        layout {
            R.layout.list_item2
        }
    }

    dataWithType {

        var mDataWithTypes: MutableMap<Int, Menu> = mutableMapOf()
        mDataWithTypes.put(R.layout.list_item, Menu("菜单1"))
        mDataWithTypes.put(R.layout.list_item2, Menu("菜单2"))
        mDataWithTypes
    }

    bindData { type, vh, data ->
        when (type) {
            R.layout.list_item -> vh.bindView<TextView>(R.id.tv_title).text = data.name
            R.layout.list_item2 -> vh.bindView<TextView>(R.id.tv_title).text = data.name
        }
    }


}