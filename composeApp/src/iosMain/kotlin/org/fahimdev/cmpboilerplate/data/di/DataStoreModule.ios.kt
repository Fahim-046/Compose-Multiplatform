package org.fahimdev.cmpboilerplate.data.di

import org.fahimdev.cmpboilerplate.createDataStore
import org.koin.core.module.Module
import org.koin.dsl.module

actual val dataStoreModule: Module = module{
    single {
        createDataStore()
    }
}
