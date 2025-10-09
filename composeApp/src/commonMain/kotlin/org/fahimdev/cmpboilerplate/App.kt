import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AppNavGraph(navController, onLanguageSelected = {}, onAppearanceSelected = {
                viewModel.onThemeChanged(it == AppearanceTheme.DARK)
            })
        }
    }
}