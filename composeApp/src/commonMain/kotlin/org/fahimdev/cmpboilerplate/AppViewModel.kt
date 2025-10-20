package org.fahimdev.cmpboilerplate

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.fahimdev.cmpboilerplate.core.managers.DataStoreManager
import org.fahimdev.cmpboilerplate.presentation.base.BaseViewModel

class AppViewModel(private val dataStoreManager: DataStoreManager) : BaseViewModel() {
    private val _isDarkModeEnabled = MutableStateFlow<Boolean?>(null)
    val isDarkModeEnabled = _isDarkModeEnabled.asStateFlow()

    private val _languageISO = MutableStateFlow<String?>(null)
    val languageISO = _languageISO.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            _isDarkModeEnabled.value = dataStoreManager.getBoolean("dark_mode")
            _languageISO.value = dataStoreManager.getString("language")
            _isLoading.value = false
        }
    }

    fun onThemeChanged(enabled: Boolean) = viewModelScope.launch {
        _isDarkModeEnabled.value = enabled
        dataStoreManager.saveBoolean("dark_mode", enabled)
    }

    fun onLanguageChanged(iso: String) = viewModelScope.launch {
        _languageISO.value = iso
        dataStoreManager.saveString("language", iso)
    }
}