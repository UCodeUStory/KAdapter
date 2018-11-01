package com.ustory.kadapterdemo.animator

import android.support.v4.view.ViewPager
import android.view.View

class DepthPageTransformer : ViewPager.PageTransformer {

    override fun transformPage(view: View, position: Float) {
        val pageWidth = view.width

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.alpha = 0f

        } else if (position <= 0) { // [-1,0]
            // Use the default slide transition when moving to the left page
            view.alpha = 1f
            view.translationX = 0f
            view.scaleX = 1f
            view.scaleY = 1f

        } else if (position <= 1) { // (0,1]
            // Fade the page out.
            view.alpha = 1 - position

            // Counteract the default slide transition
            view.translationX = pageWidth * -position

            // Scale the page down (between MIN_SCALE and 1)
            val scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position))
            view.scaleX = scaleFactor
            view.scaleY = scaleFactor

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.alpha = 0f
        }
    }

    companion object {
        private val MIN_SCALE = 0.15f
    }
}