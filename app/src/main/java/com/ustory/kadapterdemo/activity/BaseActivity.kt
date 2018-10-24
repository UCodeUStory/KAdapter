package com.ustory.kadapterdemo.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import kotlin.reflect.KClass

abstract class  BaseActivity: AppCompatActivity() {

    fun launcher(clazz:Class<*>){
        startActivity(Intent(this,clazz))
    }
}