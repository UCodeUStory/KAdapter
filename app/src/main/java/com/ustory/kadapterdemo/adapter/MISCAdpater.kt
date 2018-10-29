package com.ustory.kadapterdemo.adapter

import com.ustory.kadapterdemo.Menu
import com.ustory.kadapterdemo.R
import com.ustory.kadapterdemo.bean.ImageBean
import com.ustory.koinsample.Adapter.KAdapterFactory
import com.ustory.koinsample.Adapter.KotlinAdapter
import kotlinx.android.synthetic.main.list_item.view.*

var MISCAdapter: KotlinAdapter<ImageBean> = KAdapterFactory.KAdapter {

    multiLayout {
        layout {
            R.layout.menu_layout
        }
        layout {
            R.layout.horization_list
        }
        layout {
            R.layout.content_item
        }
    }


    bindData { type, vh, data ->
        when (type) {

        }
    }
}