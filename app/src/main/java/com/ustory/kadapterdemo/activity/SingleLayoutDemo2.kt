package com.ustory.kadapterdemo.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.ustory.kadapterdemo.Menu
import com.ustory.kadapterdemo.R
import com.ustory.kadapterdemo.adapter.simpleAdapter2
import kotlinx.android.synthetic.main.activity_main.*

class SingleLayoutDemo2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_layout_demo2)

        var datas = arrayListOf(Menu("苹果"), Menu("香蕉"), Menu("橘子"))

        recyclerView.layoutManager = LinearLayoutManager(this)

        simpleAdapter2.data {
            datas
        }

        simpleAdapter2 into recyclerView

        simpleAdapter2.addData(Menu("我是新增数据"))
        simpleAdapter2.notifyDataSetChanged()
    }
}
