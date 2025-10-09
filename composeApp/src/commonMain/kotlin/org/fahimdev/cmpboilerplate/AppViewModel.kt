package org.fahimdev.cmpboilerplate

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.fahimdev.cmpboilerplate.core.managers.DataStoreManager
import org.fahimdev.cmpboilerplate.presentation.base.BaseViewModel

class AppViewModel(private val dataStoreManager: DataStoreManager) : BaseViewModel() {
    private val _isDarkModeEnabled = MutableStateFlow<Boolean?>(null)
    val isDarkModeEnabled = _isDarkModeEnabled.asStateFlow()

    init {
        runBlocking {
            _isDarkModeEnabled.value = dataStoreManager.getBoolean("dark_mode")
        }
    }

    fun onThemeChanged(enabled: Boolean) = viewModelScope.launch {
        _isDarkModeEnabled.value = enabled
        dataStoreManager.saveBoolean("dark_mode", enabled)
    }
}