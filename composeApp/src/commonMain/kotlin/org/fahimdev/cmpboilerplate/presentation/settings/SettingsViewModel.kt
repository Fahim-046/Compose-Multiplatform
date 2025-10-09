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
    init {
        isDarkModeEnable()
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
}