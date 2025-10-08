package org.fahimdev.cmpboilerplate.data.di

import io.ktor.client.engine.android.Android
import org.koin.dsl.module

actual val engineModule = module {
    single {
        Android.create()
    }
}