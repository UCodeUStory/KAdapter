package com.ustory.kadapterdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.widget.Toast
import com.ustory.kadapterdemo.R.id.recyclerView
import com.ustory.koinsample.Adapter.myAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.header.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)

        myAdapter.onItemClick { position, view -> Toast.makeText(this, "position=" + position, Toast.LENGTH_SHORT).show() }

        myAdapter.header(R.layout.header){
            it.tv_text.text = "My name is Header"
        }
        myAdapter.footer(R.layout.footer){
            it.tv_text.text = "My name is Footer"
        }
        myAdapter into recyclerView
    }
}
