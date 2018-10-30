package com.ustory.kadapterdemo.adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import com.ustory.kadapterdemo.R
import com.ustory.kadapterdemo.fragment.OneFragment

class ViewPagerAdapter(var fm:FragmentManager) : FragmentStatePagerAdapter(fm) {
    var images = arrayListOf<Int>(R.drawable.shaonv1,R.drawable.d2,R.drawable.d3)

    override fun getItem(positoin: Int): Fragment {
        var fragment = OneFragment()
        var bundles = Bundle()
        bundles.putInt("imageUrl",images[positoin])
        fragment.arguments = bundles
        return fragment
    }

    override fun getCount(): Int {
        return images.size
    }
}