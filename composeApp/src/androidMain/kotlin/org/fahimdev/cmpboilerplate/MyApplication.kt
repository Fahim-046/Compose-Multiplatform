package org.fahimdev.cmpboilerplate

import android.app.Application
import org.fahimdev.cmpboilerplate.presentation.di.initKoin
import org.koin.android.ext.koin.androidContext

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin{
            androidContext(this@MyApplication)
        }
    }
}