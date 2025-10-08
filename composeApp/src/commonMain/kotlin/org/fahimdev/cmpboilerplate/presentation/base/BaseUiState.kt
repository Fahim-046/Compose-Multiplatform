package org.fahimdev.cmpboilerplate.presentation.base

import org.fahimdev.cmpboilerplate.core.Event

data class BaseUiState(
    val isLoading: Boolean = false,
    val isErrorDialogVisible: Boolean = false,
    val message: Event<String>? = null,
)