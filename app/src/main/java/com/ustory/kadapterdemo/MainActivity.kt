package com.ustory.kadapterdemo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.ustory.kadapterdemo.R.id.recyclerView
import com.ustory.kadapterdemo.activity.*
import com.ustory.koinsample.Adapter.menuAdapter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 多类型使用场景
 */

class MainActivity : BaseActivity() {

   var datas:ArrayList<Menu> = ArrayList<Menu>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        datas.add(Menu("单个布局写法1"))
        datas.add(Menu("单个布局写法2"))
        datas.add(Menu("添加header和footer"))
        datas.add(Menu("多种布局写法1"))
        datas.add(Menu("多种布局写法2"))

        recyclerView.layoutManager = LinearLayoutManager(this)


        menuAdapter.onItemClick { position, view ->
            when(position){
                0 -> {
                    launcher(SingleLayoutDemo1::class.java)
                }
                1 -> {
                    launcher(SingleLayoutDemo2::class.java)
                }
                2 -> {
                    launcher(SingleLayoutDemo3::class.java)
                }
                3 -> {
                    launcher(MutilLayoutDemo1::class.java)
                }
                4 -> {
                    launcher(MutilLayoutDemo2::class.java)
                }

            }
        }

        menuAdapter.data {
            datas
        }

        menuAdapter into recyclerView

    }
}
