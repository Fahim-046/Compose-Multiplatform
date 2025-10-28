package org.fahimdev.cmpboilerplate.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import org.fahimdev.cmpboilerplate.updateStatusBarStyle
import platform.CoreGraphics.CGFloat
import platform.UIKit.UIColor
import platform.UIKit.UIApplication

@Composable
actual fun SystemBarColors(
    statusBarColor: Color,
    navigationBarColor: Color,
    isDarkTheme: Boolean
) {
    SideEffect {
        UIApplication.sharedApplication.keyWindow?.rootViewController?.view?.backgroundColor =
            statusBarColor.toUIColor()
        updateStatusBarStyle(isDarkTheme)
        // Force update
        UIApplication.sharedApplication.keyWindow?.rootViewController?.setNeedsStatusBarAppearanceUpdate()
    }
}

private fun Color.toUIColor(): UIColor {
    val color = this.toArgb()
    return UIColor(
        red = ((color shr 16) and 0xFF) / 255.0,
        green = ((color shr 8) and 0xFF) / 255.0,
        blue = (color and 0xFF) / 255.0,
        alpha = ((color shr 24) and 0xFF) / 255.0
    )
}