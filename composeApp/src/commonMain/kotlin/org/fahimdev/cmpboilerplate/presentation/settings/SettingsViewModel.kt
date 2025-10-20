package org.fahimdev.cmpboilerplate.presentation.settings

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.fahimdev.cmpboilerplate.core.managers.DataStoreManager
import org.fahimdev.cmpboilerplate.presentation.base.BaseViewModel
import org.fahimdev.cmpboilerplate.presentation.settings.components.AppearanceTheme

class SettingsViewModel(private val dataStoreManager: DataStoreManager): BaseViewModel(){
    private val _isDarkModeEnabled = MutableStateFlow(AppearanceTheme.LIGHT)
    val isDarkModeEnabled = _isDarkModeEnabled

    private val _languageISO = MutableStateFlow<String?>(null)
    val languageISO = _languageISO

    init {
        isDarkModeEnable()
        getInitialLanguage()
    }

    fun onThemeChanged(enabled: Boolean) = viewModelScope.launch{
        dataStoreManager.saveBoolean("dark_mode", enabled)
        _isDarkModeEnabled.value = if(enabled) AppearanceTheme.DARK else AppearanceTheme.LIGHT
    }

    private fun isDarkModeEnable() = viewModelScope.launch {
        val isDarkMode = dataStoreManager.getBoolean("dark_mode")
        if(isDarkMode){
            _isDarkModeEnabled.value = AppearanceTheme.DARK
        }else{
            _isDarkModeEnabled.value = AppearanceTheme.LIGHT
        }
    }

    private fun getInitialLanguage() = viewModelScope.launch {
        _languageISO.value = dataStoreManager.getString("language")
    }

    fun onLanguageSelected(iso: String) = viewModelScope.launch {
        _languageISO.value = iso
        dataStoreManager.saveString("language", iso)
    }
}