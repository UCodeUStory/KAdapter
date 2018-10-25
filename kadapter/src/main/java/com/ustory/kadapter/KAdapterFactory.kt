package com.ustory.koinsample.Adapter

import android.app.Person
import android.util.Log
import android.view.ViewGroup

object KAdapterFactory {

    fun <T>KAdapter(body: KotlinAdapter<T>.() -> Unit): KotlinAdapter<T> {
        val adapter = object : KotlinAdapter<T>(){}
        var resultAdapter = adapter as KotlinAdapter<T>
        resultAdapter.body()
        return resultAdapter
    }

    inline fun <reified T> KAdapter(layoutId:Int,body: KotlinAdapter<T>.() -> Unit): KotlinAdapter<T> {
        val adapter = object : KotlinAdapter<T>(){}
        var resultAdapter = adapter as KotlinAdapter<T>
        resultAdapter.layout { layoutId }
        resultAdapter.body()
        return resultAdapter
    }

}