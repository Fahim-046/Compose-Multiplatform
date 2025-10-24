package org.fahimdev.cmpboilerplate.presentation.settings.components

import androidx.compose.runtime.Composable
import cmpboilerplate.composeapp.generated.resources.Res
import cmpboilerplate.composeapp.generated.resources.dark_label
import cmpboilerplate.composeapp.generated.resources.light_label
import org.jetbrains.compose.resources.stringResource

enum class AppearanceTheme{
    LIGHT,
    DARK,
    SYSTEM
}

fun AppearanceTheme.getName(): String{
    return when(this){
        AppearanceTheme.LIGHT -> "Light"
        AppearanceTheme.DARK -> "Dark"
        AppearanceTheme.SYSTEM -> "System"
    }
}

@Composable
fun AppearanceTheme.getLocalizedName(): String{
    return when(this){
        AppearanceTheme.LIGHT -> stringResource(Res.string.light_label)
        AppearanceTheme.DARK -> stringResource(Res.string.dark_label)
        AppearanceTheme.SYSTEM -> "System"
    }
}