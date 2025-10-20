package org.fahimdev.cmpboilerplate.presentation.settings

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.fahimdev.cmpboilerplate.core.components.topbar.PrimaryTopBar
import org.fahimdev.cmpboilerplate.presentation.base.BaseScreen
import org.fahimdev.cmpboilerplate.presentation.settings.components.AppearanceTheme
import org.fahimdev.cmpboilerplate.presentation.settings.components.LanguageAndAppearance
import org.fahimdev.cmpboilerplate.presentation.settings.components.Languages
import org.fahimdev.cmpboilerplate.presentation.settings.components.ProfileInformation
import org.fahimdev.cmpboilerplate.presentation.settings.components.getLanguageISO
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SettingsScreen(
    navController: NavController,
    onLanguageSelected: (Languages) -> Unit = {},
    onAppearanceSelected: (AppearanceTheme) -> Unit = {},
    viewModel: SettingsViewModel = koinViewModel()
) {
    val selectedLanguage by viewModel.languageISO.collectAsState()
    val selectedAppearance by viewModel.isDarkModeEnabled.collectAsState()
    SettingsScreenSkeleton(
        isDarkTheme = selectedAppearance == AppearanceTheme.DARK,
        selectedLanguage = selectedLanguage,
        selectedAppearance = selectedAppearance,
        navController = navController,
        onLanguageSelected = {
            viewModel.onLanguageSelected(it.getLanguageISO())
            onLanguageSelected(it)
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
    selectedLanguage: String?,
    selectedAppearance: AppearanceTheme,
    navController: NavController,
    onLanguageSelected: (Languages) -> Unit = {},
    onAppearanceSelected: (AppearanceTheme) -> Unit = {}
) {
    BaseScreen(
        title = "Settings",
        showTopBar = true,
        showBackArrow = false,
        showBottomNavigation = true,
        navController = navController,
        topBar = {
            PrimaryTopBar(
                title = "Settings",
                description = "Manage your personal information"
            )
        }) {
        LazyColumn(
            modifier = modifier
                .padding(8.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
            item{
                ProfileInformation()
            }
            item{
                Spacer(modifier = Modifier.height(16.dp))
            }
            item{
                LanguageAndAppearance(
                    isDarkTheme = isDarkTheme,
                    selectedLanguage = when(selectedLanguage){
                        "en" -> Languages.ENGLISH
                        "bn" -> Languages.BANGLA
                        "no" -> Languages.NORWEGIAN
                        else -> Languages.ENGLISH
                    },
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
}