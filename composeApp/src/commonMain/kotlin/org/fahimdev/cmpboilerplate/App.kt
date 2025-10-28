import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import org.fahimdev.cmpboilerplate.AppViewModel
import org.fahimdev.cmpboilerplate.localization.changeLanguage
import org.fahimdev.cmpboilerplate.presentation.navigation.AppNavGraph
import org.fahimdev.cmpboilerplate.presentation.settings.components.AppearanceTheme
import org.fahimdev.cmpboilerplate.presentation.settings.components.Languages
import org.fahimdev.cmpboilerplate.presentation.settings.components.getLanguageISO
import org.fahimdev.cmpboilerplate.presentation.splash.SplashScreen
import org.fahimdev.cmpboilerplate.theme.CMPBoilerplateTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

val LocalLanguage = compositionLocalOf { "en" }

@Composable
@Preview
fun App() {
    val navController = rememberNavController()
    val viewModel = koinInject<AppViewModel>()
    val isDarkTheme by viewModel.isDarkModeEnabled.collectAsState()
    val languageISO by viewModel.languageISO.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    var currentLanguage by remember { mutableStateOf(Languages.ENGLISH) }

    LaunchedEffect(languageISO) {
        changeLanguage(languageISO ?: "en")
    }

    CMPBoilerplateTheme(darkTheme = isDarkTheme == true) {
        if (isLoading) {
            SplashScreen()
        } else {
            CompositionLocalProvider(LocalLanguage provides (languageISO ?: "en")) {
                key(languageISO) {
                    AppNavGraph(
                        navController = navController,
                        onLanguageSelected = { language ->
                            currentLanguage = language
                            viewModel.onLanguageChanged(language.getLanguageISO())
                        },
                        onAppearanceSelected = { theme ->
                            viewModel.onThemeChanged(theme == AppearanceTheme.DARK)
                        }
                    )
                }
            }
        }
    }
}