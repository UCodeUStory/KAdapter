package com.ustory.kadapterdemo.activity

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.insert
import android.provider.SyncStateContract.Helpers.update
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.widget.ImageButton
import com.ustory.kadapterdemo.Menu
import com.ustory.kadapterdemo.R
import com.ustory.kadapterdemo.R.id.recyclerView
import com.ustory.kadapterdemo.adapter.*
import com.ustory.kadapterdemo.animator.DepthPageTransformer
import com.ustory.kadapterdemo.bean.HorizationBean
import com.ustory.kadapterdemo.bean.ImageBean
import com.ustory.kadapterdemo.fragment.OneFragment
import com.ustory.koinsample.Adapter.KotlinAdapter
import com.ustory.koinsample.Adapter.menuAdapter
import kotlinx.android.synthetic.main.activity_mutil_layout_demo3.*
import kotlinx.android.synthetic.main.horization_list.view.*

class MutilLayoutDemo3 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mutil_layout_demo3)

        var datas = initDatas()
        var horizationDatas = arrayListOf<Int>(R.drawable.meizi4, R.drawable.meizi5, R.drawable.meizi6,
                R.drawable.meizi4, R.drawable.meizi5, R.drawable.meizi6)

        recyclerView.layoutManager = LinearLayoutManager(this)

        MISCAdapter.header(R.layout.misc_header) {
            var viewPager = it.findViewById<ViewPager>(R.id.viewPager)
            viewPager.setPageTransformer(false, DepthPageTransformer())
            viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
        }

        //根据数据中某一字段来判断选择布局
        MISCAdapter.data(datas) {
            if (it.type == 1) {
                addData(R.layout.content_item1, it)
            } else {
                addData(R.layout.content_item, it)
            }
        }

        MISCAdapter.addOtherData(0,R.layout.first_menu,Menu())
        //添加其他类型数据
        MISCAdapter.addOtherData(1,R.layout.horization_list,HorizationBean(horizationDatas))

        //给新数据赋值
        MISCAdapter.bindData(R.layout.horization_list) { type, vh, data, backupData ->
            bindHorizationList(vh, backupData)
        }

        MISCAdapter into recyclerView

        /**
         * 添加新数据方式
         */
        MISCAdapter.addData(R.layout.content_item, ImageBean("Image6"))

        MISCAdapter.notifyDataSetChanged()


    }

    private fun bindHorizationList(vh: KotlinAdapter.ViewHolder, backupData: Any?) {
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        vh.itemView.horizationRecyclerView.layoutManager = linearLayoutManager
        horizationAdapter.data {
            var bean = backupData as HorizationBean
            bean.datas
        }
        vh.itemView.horizationRecyclerView.adapter = horizationAdapter
    }


    private fun initDatas(): MutableList<ImageBean> {
        var datas = mutableListOf<ImageBean>()
        datas.add(ImageBean(title = "image1",type = 1))
        datas.add(ImageBean("image2"))
        datas.add(ImageBean("image3"))
        datas.add(ImageBean("image4"))
        datas.add(ImageBean("image5"))
        return datas
    }
}
