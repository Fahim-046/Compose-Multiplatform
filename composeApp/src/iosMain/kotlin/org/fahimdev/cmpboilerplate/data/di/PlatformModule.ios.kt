package org.fahimdev.cmpboilerplate.data.di

import io.ktor.client.engine.darwin.Darwin
import org.koin.dsl.module

actual val engineModule = module {
    single {
        Darwin.create()
    }
}