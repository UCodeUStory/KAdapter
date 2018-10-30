package com.ustory.kadapterdemo.adapter

import com.ustory.kadapterdemo.R
import com.ustory.koinsample.Adapter.KAdapterFactory.KAdapter
import com.ustory.koinsample.Adapter.KotlinAdapter
import kotlinx.android.synthetic.main.horization_item.view.*

var horizationAdapter = KAdapter<Int>{


    layout {
        R.layout.horization_item
    }
    
    bindData { type, vh, data ->

        vh.itemView.iv_image.setImageResource(data)
    }
}