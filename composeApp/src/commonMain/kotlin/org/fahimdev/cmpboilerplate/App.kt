import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import org.fahimdev.cmpboilerplate.AppViewModel
import org.fahimdev.cmpboilerplate.presentation.navigation.AppNavGraph
import org.fahimdev.cmpboilerplate.presentation.settings.components.AppearanceTheme
import org.fahimdev.cmpboilerplate.theme.CMPBoilerplateTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
@Preview
fun App() {
    val navController = rememberNavController()
    val viewModel = koinInject<AppViewModel>()
    val isDarkTheme by viewModel.isDarkModeEnabled.collectAsState()

    CMPBoilerplateTheme(darkTheme = isDarkTheme ?: false) {
        AppNavGraph(navController, onLanguageSelected = {}, onAppearanceSelected = {
            viewModel.onThemeChanged(it == AppearanceTheme.DARK)
        })
    }
}