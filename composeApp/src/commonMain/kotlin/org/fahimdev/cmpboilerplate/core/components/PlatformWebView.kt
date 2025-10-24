package org.fahimdev.cmpboilerplate.core.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
expect fun PlatformWebView(
    navController: NavController,
    url: String,
    modifier: Modifier = Modifier,
    enableJavaScript: Boolean,
)