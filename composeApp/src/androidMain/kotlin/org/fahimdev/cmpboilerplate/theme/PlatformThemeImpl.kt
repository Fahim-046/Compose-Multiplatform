package org.fahimdev.cmpboilerplate.theme

import androidx.appcompat.app.AppCompatDelegate

actual class PlatformThemeImpl actual constructor() : PlatformTheme {
    override fun isDarkTheme(): Boolean? {
        return when (AppCompatDelegate.getDefaultNightMode()) {
            AppCompatDelegate.MODE_NIGHT_YES -> true
            AppCompatDelegate.MODE_NIGHT_NO -> false
            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM -> null
            else -> null
        }
    }

    override fun setTheme(isDark: Boolean) {
        AppCompatDelegate.setDefaultNightMode(
            if (isDark) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )
    }
}
