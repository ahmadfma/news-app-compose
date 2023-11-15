package com.makassar.newsappcompose

import android.app.Application
import com.makassar.newsappcompose.data.AppContainer
import com.makassar.newsappcompose.data.DefaultAppContainer

class MyApplication: Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }

}