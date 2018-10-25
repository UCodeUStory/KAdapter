package com.ustory.kadapterdemo.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.ustory.kadapterdemo.Menu
import com.ustory.kadapterdemo.R
import com.ustory.kadapterdemo.R.id.recyclerView
import com.ustory.kadapterdemo.adapter.mutilAdapter
import com.ustory.kadapterdemo.adapter.simpleAdapter2
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.header.view.*
import kotlinx.android.synthetic.main.list_item2.view.*

class MutilLayoutDemo1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mutil_layout_demo1)

        var datas = arrayListOf(Menu("Android"), Menu("IOS"), Menu("微信小程序"))
        var dataTargets:ArrayList<Pair<Int,Menu?>> = arrayListOf()
        for (i in datas.indices) {
            if (i == 0) {
                dataTargets.add(R.layout.list_item to datas[i])
            } else if(i == 1){
                //插入一个空数据
                dataTargets.add(R.layout.list_item2 to null)
                //原数据继续添加
                dataTargets.add(R.layout.list_item to datas[i])
            } else {
                dataTargets.add(R.layout.list_item3 to datas[i])
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(this)



        mutilAdapter into recyclerView
    }
}
