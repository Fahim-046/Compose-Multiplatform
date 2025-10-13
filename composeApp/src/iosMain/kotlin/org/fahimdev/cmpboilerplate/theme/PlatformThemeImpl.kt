package org.fahimdev.cmpboilerplate.theme

import platform.UIKit.*

actual class PlatformThemeImpl actual constructor() : PlatformTheme {
    override fun isDarkTheme(): Boolean? {
        val style = UIScreen.mainScreen.traitCollection.userInterfaceStyle
        return when (style) {
            UIUserInterfaceStyle.UIUserInterfaceStyleDark -> true
            UIUserInterfaceStyle.UIUserInterfaceStyleLight -> false
            else -> null
        }
    }

    override fun setTheme(isDark: Boolean) {
        val style = if (isDark) {
            UIUserInterfaceStyle.UIUserInterfaceStyleDark
        } else {
            UIUserInterfaceStyle.UIUserInterfaceStyleLight
        }
        UIApplication.sharedApplication.keyWindow?.overrideUserInterfaceStyle = style
    }
}
