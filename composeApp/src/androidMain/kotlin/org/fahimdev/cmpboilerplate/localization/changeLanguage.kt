package org.fahimdev.cmpboilerplate.localization

import java.util.Locale

actual fun changeLanguage(language: String) {
    val locale = Locale(language)
    Locale.setDefault(locale)
}