package com.ustory.koinsample.Adapter

import android.widget.Button
import android.widget.TextView
import com.ustory.kadapterdemo.Menu
import com.ustory.kadapterdemo.Person
import com.ustory.kadapterdemo.R
import com.ustory.koinsample.Adapter.KAdapterFactory.KAdapter
import kotlinx.android.synthetic.main.menu_list.view.*


/**
 * 定义一个adapter就这么简单
 */
var menuAdapter: KotlinAdapter<Menu> = KAdapter {
    layout {
        R.layout.menu_list
    }

    bindData { type, vh, data ->
        vh.itemView.tv_title.text = data.name
    }


}