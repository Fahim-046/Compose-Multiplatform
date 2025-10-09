package org.fahimdev.cmpboilerplate.presentation.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.fahimdev.cmpboilerplate.core.components.topbar.PrimaryTopBar
import org.fahimdev.cmpboilerplate.presentation.base.BaseScreen
import org.fahimdev.cmpboilerplate.presentation.settings.components.AppearanceTheme
import org.fahimdev.cmpboilerplate.presentation.settings.components.LanguageAndAppearance
import org.fahimdev.cmpboilerplate.presentation.settings.components.Languages
import org.fahimdev.cmpboilerplate.presentation.settings.components.ProfileInformation
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SettingsScreen(
    onLanguageSelected: (Languages) -> Unit = {},
    onAppearanceSelected: (AppearanceTheme) -> Unit = {},
    viewModel: SettingsViewModel = koinViewModel()
) {
    var selectedLanguage by remember { mutableStateOf(Languages.ENGLISH) }
    val selectedAppearance by viewModel.isDarkModeEnabled.collectAsState()
    SettingsScreenSkeleton(
        isDarkTheme = selectedAppearance == AppearanceTheme.DARK,
        selectedLanguage = selectedLanguage,
        selectedAppearance = selectedAppearance,
        onLanguageSelected = {
            selectedLanguage = it
        },
        onAppearanceSelected = { theme ->
            onAppearanceSelected(theme)
            viewModel.onThemeChanged(theme == AppearanceTheme.DARK)
        })
}

@Composable
fun SettingsScreenSkeleton(
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean,
    selectedLanguage: Languages,
    selectedAppearance: AppearanceTheme,
    onLanguageSelected: (Languages) -> Unit = {},
    onAppearanceSelected: (AppearanceTheme) -> Unit = {}
) {
    BaseScreen(
        title = "Settings",
        showTopBar = true,
        showBackArrow = false,
        topBar = {
            PrimaryTopBar(
                title = "Settings",
                description = "Manage your personal information"
            )
        }) {
        Column(
            modifier = modifier
                .padding(8.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            ProfileInformation()
            Spacer(modifier = Modifier.height(16.dp))
            LanguageAndAppearance(
                isDarkTheme = isDarkTheme,
                selectedLanguage = selectedLanguage,
                selectedAppearance = selectedAppearance,
                onLanguageSelected = { language ->
                    onLanguageSelected(language)
                },
                onAppearanceSelected = { appearanceTheme ->
                    onAppearanceSelected(appearanceTheme)
                }
            )

        }
    }
}