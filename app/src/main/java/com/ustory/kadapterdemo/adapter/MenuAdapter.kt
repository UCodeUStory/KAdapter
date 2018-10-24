package com.ustory.koinsample.Adapter

import android.widget.TextView
import com.ustory.kadapterdemo.Menu
import com.ustory.kadapterdemo.Person
import com.ustory.kadapterdemo.R
import com.ustory.koinsample.Adapter.KAdapterFactory.KAdapter


/**
 * 定义一个adapter就这么简单
 */
var menuAdapter: KotlinAdapter<Menu> = KAdapter {
    layout {
        R.layout.menu_list
    }

    data {
        arrayListOf(Menu("单个布局写法1"), Menu("多种布局写法1"), Menu("多种布局写法2"))
    }

    bindData { type, vh, data ->
        vh.bindView<TextView>(R.id.tv_title).text = data.name
    }


}