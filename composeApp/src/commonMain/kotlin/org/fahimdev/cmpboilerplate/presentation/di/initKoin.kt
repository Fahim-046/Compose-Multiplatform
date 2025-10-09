package org.fahimdev.cmpboilerplate.presentation.di

import org.fahimdev.cmpboilerplate.data.di.dataStoreModule
import org.fahimdev.cmpboilerplate.data.di.engineModule
import org.fahimdev.cmpboilerplate.data.di.networkModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            appModule,
            networkModule,
            engineModule,
            dataStoreModule
        )
    }
}