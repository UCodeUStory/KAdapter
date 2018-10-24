package com.ustory.kadapterdemo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.ustory.kadapterdemo.R.id.recyclerView
import com.ustory.kadapterdemo.activity.BaseActivity
import com.ustory.kadapterdemo.activity.SingleLayoutDemo1
import com.ustory.koinsample.Adapter.menuAdapter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 多类型使用场景
 */

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)

        menuAdapter.onItemClick { position, view ->
            when(position){
                0 -> {
                    launcher(SingleLayoutDemo1::class.java)
                }

            }
        }
        menuAdapter into recyclerView



//        myAdapter.data {
//            arrayListOf(Person("Bob"),Person("John"))
//        }
//        myAdapter.onItemClick { position, view -> Toast.makeText(this, "position=" + position, Toast.LENGTH_SHORT).show() }
//
//        myAdapter.header(R.layout.header){
//            it.tv_text.text = "My name is Header"
//        }
//        myAdapter.footer(R.layout.footer){
//            it.tv_text.text = "My name is Footer"
//        }
//        myAdapter into recyclerView
    }
}
