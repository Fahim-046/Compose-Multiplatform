package org.fahimdev.cmpboilerplate.core.components.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import cmpboilerplate.composeapp.generated.resources.Res
import cmpboilerplate.composeapp.generated.resources.home_tab_label
import cmpboilerplate.composeapp.generated.resources.settings_tab_label
import org.fahimdev.cmpboilerplate.presentation.navigation.Screen
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import kotlin.reflect.KClass

data class BottomNavItem(
    val icon: ImageVector,
    val selectedIcon: ImageVector,
    val screen: Any,
    val screenClass: KClass<*>,
    val resource: StringResource
)

@Composable
fun bottomNavItems(): List<BottomNavItem> {
    return listOf(
        BottomNavItem(
            icon = Icons.Outlined.Home,
            selectedIcon = Icons.Filled.Home,
            screen = Screen.MovieList,
            screenClass = Screen.MovieList::class,
            resource = Res.string.home_tab_label
        ),
        BottomNavItem(
            icon = Icons.Outlined.Settings,
            selectedIcon = Icons.Filled.Settings,
            screen = Screen.Settings,
            screenClass = Screen.Settings::class,
            resource = Res.string.settings_tab_label
        )
    )
}

@Composable
fun BottomNavigationBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(modifier = modifier) {
        val bottomNavItems = bottomNavItems()
        bottomNavItems.forEach { item ->
            val isSelected = currentRoute?.startsWith(item.screenClass.qualifiedName ?: "") == true
            val label = stringResource(item.resource)

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = if (isSelected) item.selectedIcon else item.icon,
                        contentDescription = label
                    )
                },
                label = { Text(label) },
                selected = isSelected,
                onClick = {
                    if (!isSelected) {
                        navController.navigate(item.screen) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}