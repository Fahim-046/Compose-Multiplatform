package org.fahimdev.cmpboilerplate.presentation.settings.components

import androidx.compose.runtime.Composable

enum class Languages(val iso: String, val nativeName: String, val englishName: String) {
    ENGLISH("en", "English", "English"),
    BANGLA("bn", "বাংলা", "Bengali"),
    NORWEGIAN("no", "Norsk", "Norwegian");

    companion object {
        fun getAvailableLanguages(): List<Languages> = entries

        fun fromISO(iso: String?): Languages {
            return entries.find { it.iso == iso } ?: ENGLISH
        }

        fun getSystemLanguage(): Languages {
            // This would typically get the system locale, but for now return default
            return ENGLISH
        }
    }
}

@Composable
fun Languages.getDisplayName(): String {
    return nativeName
}

fun Languages.getLanguageISO(): String = iso

fun Languages.getLanguageName(): String = nativeName

fun String?.getLanguageByISO(): Languages = Languages.fromISO(this)

fun getLanguages(): List<Languages> = Languages.getAvailableLanguages()

// Language validation helper
fun isValidLanguageCode(code: String?): Boolean {
    return Languages.entries.any { it.iso == code }
}

// Get language direction (useful for RTL languages)
fun Languages.isRTL(): Boolean {
    return when (this) {
        Languages.ENGLISH, Languages.BANGLA, Languages.NORWEGIAN -> false
        // Add RTL languages here if needed
    }
}