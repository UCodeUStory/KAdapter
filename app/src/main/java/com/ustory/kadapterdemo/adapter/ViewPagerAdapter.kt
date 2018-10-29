package com.ustory.kadapterdemo.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.ustory.kadapterdemo.R
import com.ustory.kadapterdemo.fragment.OneFragment

class ViewPagerAdapter(var fm:FragmentManager) : FragmentPagerAdapter(fm) {
    var images = arrayListOf<Int>(R.drawable.d1,R.drawable.d2,R.drawable.d3)
    var fragment =  OneFragment()
    override fun getItem(positoin: Int): Fragment {
        fragment.arguments?.putInt("imageUrl",images[positoin])
        return OneFragment()
    }

    override fun getCount(): Int {
        return images.size
    }
}