package com.ustory.kadapterdemo.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.ustory.kadapterdemo.Menu

import com.ustory.kadapterdemo.R
import com.ustory.kadapterdemo.adapter.mutilAdapter
import com.ustory.kadapterdemo.adapter.mutilAdapter2
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.header.view.*
import kotlinx.android.synthetic.main.list_item2.view.*

class MutilLayoutDemo2 : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mutil_layout_demo1)

        var datas = arrayListOf(Menu("Android"), Menu("IOS"), Menu("微信小程序"), Menu("HTML程序"))

        recyclerView.layoutManager = LinearLayoutManager(this)

        mutilAdapter2.header(R.layout.header){
            it.tv_text.text = "HEADER"
        }

        mutilAdapter2.footer(R.layout.footer){
            it.tv_text.text = "FOOTER"
        }

        // 对新增red_layout类型数据单独设置，新数据使用backupData字段，因为新数据类型不确定
        mutilAdapter2.bindData(R.layout.red_layout){
            type, vh, data, backupData ->
            backupData?.let {
                vh.itemView.tv_item.text = backupData as String
            }
        }
        // 对原始旧数据重新设置，原始数据使用data字段
        mutilAdapter2.bindData(R.layout.green_layout){
            type, vh, data, backupData ->
            data?.let {
                vh.itemView.tv_item.text = "新·"+data.name
            }
        }

        mutilAdapter2.data(datas){
            if (it.name.equals("Android")){
                addData(R.layout.green_layout,it)
            }else if(it.name.equals("IOS")){
                addData(R.layout.red_layout,it)
            }else if(it.name.equals("微信小程序")){
                addData(R.layout.yellow_layout,it)
            }else{
                addData(R.layout.blue_layout,it)
            }
        }

        var datas2 = arrayListOf(Menu("Android2"), Menu("IOS2"))
        mutilAdapter2.addDatas(datas2){

            if(it.name.equals("Android2")){
                addData(R.layout.yellow_layout,it)
            }else{
                addData(R.layout.blue_layout,it)
            }
        }

        mutilAdapter2.onItemClick { position, view ->
            toast("被点击position="+position)
        }

        mutilAdapter2 into recyclerView
    }
}
