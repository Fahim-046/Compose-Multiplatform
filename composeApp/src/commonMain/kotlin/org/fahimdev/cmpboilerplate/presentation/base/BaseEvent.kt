package org.fahimdev.cmpboilerplate.presentation.base

sealed class BaseEvent {
    data object OnShowLoadingDialog : BaseEvent()
    data object OnDismissLoadingDialog : BaseEvent()
    data object OnShowErrorDialog : BaseEvent()
    data object OnDismissErrorDialog : BaseEvent()
}