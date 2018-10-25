package com.ustory.kadapterdemo.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.ustory.kadapterdemo.Menu
import com.ustory.kadapterdemo.R
import com.ustory.kadapterdemo.R.id.recyclerView
import com.ustory.kadapterdemo.adapter.mutilAdapter
import com.ustory.kadapterdemo.adapter.mutilAdapter2
import com.ustory.kadapterdemo.adapter.simpleAdapter2
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.header.view.*
import kotlinx.android.synthetic.main.list_item2.view.*

class MutilLayoutDemo2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mutil_layout_demo1)

        var datas = arrayListOf(Menu("Android"), Menu("IOS"), Menu("微信小程序"))

        recyclerView.layoutManager = LinearLayoutManager(this)
//        //需要单独处理的类型
//        mutilAdapter2.interceptBindView(R.layout.list_item2){
//            position ,type, vh ->  vh.itemView.tv_item.text = "Pro-IOS"
//        }
//        mutilAdapter2.interceptBindView(R.layout.list_item){
//            position,type, vh ->  vh.itemView.tv_item.text = "Pro-Android"
//
//        }

        mutilAdapter2.data(datas) {
            update(0,R.layout.red_layout)
            update(1,R.layout.yellow_layout)
            update(2,R.layout.blue_layout)
//            update(3,R.layout.green_layout)
        }

        mutilAdapter2 into recyclerView
    }
}
