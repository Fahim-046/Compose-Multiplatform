package org.fahimdev.cmpboilerplate.theme

interface PlatformTheme {
    fun isDarkTheme(): Boolean?
    fun setTheme(isDark: Boolean)
}

expect class PlatformThemeImpl() : PlatformTheme
