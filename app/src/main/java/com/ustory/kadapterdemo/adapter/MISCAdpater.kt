package com.ustory.kadapterdemo.adapter

import com.ustory.kadapterdemo.Menu
import com.ustory.kadapterdemo.R
import com.ustory.kadapterdemo.bean.ImageBean
import com.ustory.koinsample.Adapter.KAdapterFactory
import com.ustory.koinsample.Adapter.KotlinAdapter
import kotlinx.android.synthetic.main.content_item.view.*
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
        layout {
            R.layout.content_item1
        }
        layout {
            R.layout.first_menu
        }
    }


    bindData { type, vh, data ->
        when(type){
            R.layout.content_item1->vh.itemView.tv_text.text = data.title
            R.layout.content_item->vh.itemView.tv_text.text = data.title
        }
    }
}