package org.fahimdev.cmpboilerplate

import App
import androidx.compose.foundation.ComposeFoundationFlags
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.window.ComposeUIViewController
import org.fahimdev.cmpboilerplate.presentation.di.initKoin
import platform.UIKit.UIApplication

@OptIn(ExperimentalFoundationApi::class)
fun MainViewController() = ComposeUIViewController {
    ComposeFoundationFlags.isNonComposedClickableEnabled = false
    initKoin()
    App()
}

fun updateStatusBarStyle(isDarkTheme: Boolean) {
    UIApplication.sharedApplication.keyWindow?.rootViewController?.setNeedsStatusBarAppearanceUpdate()
}
