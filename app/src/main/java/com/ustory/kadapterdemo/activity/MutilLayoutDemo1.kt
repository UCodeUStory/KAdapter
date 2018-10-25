package com.ustory.kadapterdemo.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.insert
import android.provider.SyncStateContract.Helpers.update
import android.support.v7.widget.LinearLayoutManager
import com.ustory.kadapterdemo.Menu
import com.ustory.kadapterdemo.R
import com.ustory.kadapterdemo.R.id.recyclerView
import com.ustory.kadapterdemo.adapter.mutilAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.header.view.*
import kotlinx.android.synthetic.main.list_item2.view.*


class MutilLayoutDemo1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mutil_layout_demo1)

        var datas = arrayListOf(Menu("Android"), Menu("IOS"), Menu("微信小程序"), Menu("HTML程序"))

        recyclerView.layoutManager = LinearLayoutManager(this)

        // 对新增red_layout类型数据单独设置，新数据使用backupData字段，因为新数据类型不确定
        mutilAdapter.bindData(R.layout.red_layout){
            type, vh, data, backupData ->
            backupData?.let {
                vh.itemView.tv_item.text = backupData as String
            }
        }
        // 对原始旧数据重新设置，原始数据使用data字段
        mutilAdapter.bindData(R.layout.green_layout){
            type, vh, data, backupData ->
            data?.let {
                vh.itemView.tv_item.text = "新·"+data.name
            }
        }

        mutilAdapter.data(datas) {
            update(0,R.layout.red_layout) //设置第一个元素类型为red_layout
            update(0..1,R.layout.yellow_layout) //设置0 到 1的类型为yellow_layout，如果之前设置过0，会覆盖之前的设置的类型
            update(2,R.layout.blue_layout)
            update(3,R.layout.green_layout)
            insert(2,R.layout.red_layout,"Python")//在第2个位置后插入一个red_layout类型数据,
        }

        mutilAdapter into recyclerView
    }
}
