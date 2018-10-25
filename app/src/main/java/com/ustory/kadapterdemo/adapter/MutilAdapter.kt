package com.ustory.kadapterdemo.adapter

import android.widget.TextView
import com.ustory.kadapterdemo.Menu
import com.ustory.kadapterdemo.R
import com.ustory.koinsample.Adapter.KAdapterFactory
import com.ustory.koinsample.Adapter.KotlinAdapter
import kotlinx.android.synthetic.main.list_item.view.*

var mutilAdapter: KotlinAdapter<Menu> = KAdapterFactory.KAdapter {

    multiLayout {
        layout {
            R.layout.list_item
        }
        layout {
            R.layout.list_item2
        }
        layout {
            R.layout.list_item3
        }
    }


    bindData { type, vh, data ->
        when (type) {
            R.layout.list_item -> vh.itemView.tv_item.text = data.name
            R.layout.list_item2 -> vh.itemView.tv_item.text = data.name
            R.layout.list_item3 -> vh.itemView.tv_item.text = data.name
        }
    }
}



var mutilAdapter2: KotlinAdapter<Menu> = KAdapterFactory.KAdapter {

    multiLayout {
        layout {
            R.layout.list_item
        }
        layout {
            R.layout.list_item2
        }
        layout {
            R.layout.list_item3
        }
    }

    bindData { type, vh, data ->
        when (type) {
            R.layout.list_item -> vh.itemView.tv_item.text = data.name
            R.layout.list_item2 -> vh.itemView.tv_item.text = data.name
            R.layout.list_item3 -> vh.itemView.tv_item.text = data.name
        }
    }
}