package org.fahimdev.cmpboilerplate.localization

import platform.Foundation.NSUserDefaults

actual fun changeLanguage(language: String) {
    NSUserDefaults.standardUserDefaults.setObject(arrayListOf(language), "AppleLanguages")
}