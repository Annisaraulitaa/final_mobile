package com.D121211090.edition

import android.app.Application
import com.D121211090.edition.data.AppContainer
import com.D121211090.edition.data.DefaultAppContainer

class MyApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}