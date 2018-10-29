package com.ustory.kadapterdemo.adapter

import com.ustory.kadapterdemo.R
import com.ustory.koinsample.Adapter.KAdapterFactory.KAdapter
import com.ustory.koinsample.Adapter.KotlinAdapter
import kotlinx.android.synthetic.main.horization_item.view.*

var horizationAdapter = KAdapter<String>{


    layout {
        R.layout.horization_item
    }
    
    bindData { type, vh, data ->

        vh.itemView.tv_text.text = data
    }
}