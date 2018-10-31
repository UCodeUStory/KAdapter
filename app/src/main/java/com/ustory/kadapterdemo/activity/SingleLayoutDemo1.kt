package com.ustory.kadapterdemo.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.ustory.kadapterdemo.Menu
import com.ustory.kadapterdemo.R
import com.ustory.kadapterdemo.R.id.recyclerView
import com.ustory.kadapterdemo.adapter.simpleAdapter
import kotlinx.android.synthetic.main.activity_main.*

class SingleLayoutDemo1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_layout_demo1)
        var datas = arrayListOf(Menu("Android"),Menu("IOS"),Menu("微信小程序"))

        recyclerView.layoutManager = LinearLayoutManager(this)

        simpleAdapter.data {
            datas
        }

        simpleAdapter into recyclerView

        simpleAdapter.addDatas(arrayListOf(Menu("新增数据1"), Menu("新增数据2")))

        simpleAdapter.notifyDataSetChanged()

    }
}
