package org.fahimdev.cmpboilerplate.presentation.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.fahimdev.cmpboilerplate.core.components.dialog.LoadingDialog
import org.fahimdev.cmpboilerplate.core.components.navigation.BottomNavigationBar

@Composable
fun BaseScreen(
    title: String,
    viewModel: BaseViewModel = BaseViewModel(),
    showTopBar: Boolean = true,
    showBackArrow: Boolean = true,
    topBar: @Composable () -> Unit = {

    },
    showBottomBar: Boolean = false,
    showBottomNavigation: Boolean = false,
    bottomBar: @Composable () -> Unit = {},
    screenContent: @Composable () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.baseUiEvent.collect { event ->
            when (event) {
                is BaseUiEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(event.message)
                }

                is BaseUiEvent.Navigate -> {
                    //navHostController.navigate(event.route)
                }

                BaseUiEvent.PopBackStack -> {
                    //navHostController.popBackStack()
                }

                is BaseUiEvent.ShowToast -> {

                }
            }
        }
    }

    Scaffold(
        topBar = { if (showTopBar) topBar() },
        bottomBar = {
            when {
                showBottomNavigation -> {
                    BottomNavigationBar()
                }
                showBottomBar -> bottomBar()
            }
        },
        snackbarHost = { SnackbarHost(snackBarHostState) },
        modifier = Modifier.statusBarsPadding()
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            LoadingDialog(
                isVisible = state.isLoading,
                onDismiss = {
                    viewModel.onBaseEvent(BaseEvent.OnDismissLoadingDialog)
                }
            )
            screenContent()
        }

    }
}