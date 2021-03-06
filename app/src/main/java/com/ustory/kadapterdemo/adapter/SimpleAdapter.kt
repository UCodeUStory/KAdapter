package com.ustory.kadapterdemo.adapter

import android.widget.TextView
import com.ustory.kadapterdemo.Menu
import com.ustory.kadapterdemo.R
import com.ustory.koinsample.Adapter.KAdapterFactory.KAdapter
import com.ustory.koinsample.Adapter.KotlinAdapter

/**
 * 定义一个adapter就这么简单
 */
var simpleAdapter: KotlinAdapter<Menu> = KAdapter(R.layout.menu_list) {

    bindData { type, vh, data ->
        vh.bindView<TextView>(R.id.tv_title).text = data.name
    }

}