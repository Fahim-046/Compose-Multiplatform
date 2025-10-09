package org.fahimdev.cmpboilerplate

import androidx.compose.ui.window.ComposeUIViewController
import org.fahimdev.cmpboilerplate.presentation.di.initKoin

fun MainViewController() = ComposeUIViewController(configure = {
    initKoin()
}) {
    App()
}