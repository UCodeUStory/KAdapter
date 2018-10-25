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
            R.layout.red_layout
        }
        layout {
            R.layout.yellow_layout
        }
        layout {
            R.layout.blue_layout
        }
        layout {
            R.layout.green_layout
        }
    }


    bindData { type, vh, data ->
        when (type) {
            R.layout.red_layout -> vh.itemView.tv_item.text = data.name
            R.layout.yellow_layout -> vh.itemView.tv_item.text = data.name
            R.layout.blue_layout -> vh.itemView.tv_item.text = data.name
            R.layout.green_layout -> vh.itemView.tv_item.text = data.name
        }
    }
}


var mutilAdapter2: KotlinAdapter<Menu> = KAdapterFactory.KAdapter {

    multiLayout {
        layout {
            R.layout.red_layout
        }
        layout {
            R.layout.yellow_layout
        }
        layout {
            R.layout.blue_layout
        }
        layout {
            R.layout.green_layout
        }
    }


    bindData { type, vh, data ->
        when (type) {
            R.layout.red_layout -> vh.itemView.tv_item.text = data.name
            R.layout.yellow_layout -> vh.itemView.tv_item.text = data.name
            R.layout.blue_layout -> vh.itemView.tv_item.text = data.name
            R.layout.green_layout -> vh.itemView.tv_item.text = data.name
        }
    }
}