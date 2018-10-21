package com.ustory.koinsample.Adapter

import android.widget.TextView
import com.ustory.kadapterdemo.Person
import com.ustory.kadapterdemo.R
import com.ustory.koinsample.Adapter.KAdapterFactory.KAdapter


/**
 * 定义一个adapter就这么简单
 */
var myAdapter: KotlinAdapter<Person> = KAdapter {

    layout {
        R.layout.list_item
    }

    data {
        arrayListOf(Person("张三"), Person("李四"))
    }

    bindData { vh, data ->
        vh.bindView<TextView>(R.id.tv_item).text = data.name
    }

    onItemClick { _, _ ->

    }


}