package com.ustory.kadapterdemo.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.ustory.kadapterdemo.Menu
import com.ustory.kadapterdemo.R
import com.ustory.kadapterdemo.adapter.simpleAdapter2
import com.ustory.kadapterdemo.adapter.simpleAdapter3
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.header.view.*

class SingleLayoutDemo3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_layout_demo3)

        var datas = arrayListOf(Menu("Android"), Menu("IOS"), Menu("微信小程序"))

        recyclerView.layoutManager = LinearLayoutManager(this)

        simpleAdapter3.data {
            datas
        }
        simpleAdapter3.header(R.layout.header){
            it.tv_text.text = "Header"
        }
        simpleAdapter3.footer(R.layout.footer){
            it.tv_text.text = "Footer"
        }
        simpleAdapter3 into recyclerView
    }
}
