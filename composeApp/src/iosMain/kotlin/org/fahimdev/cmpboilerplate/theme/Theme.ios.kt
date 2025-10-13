package org.fahimdev.cmpboilerplate.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import org.fahimdev.cmpboilerplate.updateStatusBarStyle
import platform.UIKit.UIApplication

@Composable
actual fun SystemBarColors(
    statusBarColor: Color,
    navigationBarColor: Color,
    isDarkTheme: Boolean
) {
    SideEffect {
        updateStatusBarStyle(isDarkTheme)
        // Force update
        UIApplication.sharedApplication.keyWindow?.rootViewController?.setNeedsStatusBarAppearanceUpdate()
    }
}