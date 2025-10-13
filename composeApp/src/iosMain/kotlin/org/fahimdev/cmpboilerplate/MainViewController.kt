package org.fahimdev.cmpboilerplate

import App
import androidx.compose.ui.window.ComposeUIViewController
import org.fahimdev.cmpboilerplate.presentation.di.initKoin
import platform.UIKit.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
fun MainViewController() = ComposeUIViewController {
    initKoin()
    App()
}

fun updateStatusBarStyle(isDarkTheme: Boolean) {
    UIApplication.sharedApplication.keyWindow?.rootViewController?.setNeedsStatusBarAppearanceUpdate()
}
