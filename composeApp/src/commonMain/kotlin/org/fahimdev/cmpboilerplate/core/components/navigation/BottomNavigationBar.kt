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
import org.fahimdev.cmpboilerplate.presentation.navigation.Screen

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector,
    val screen: Screen
)

val bottomNavItems = listOf(
    BottomNavItem(
        label = "Home",
        icon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home,
        screen = Screen.MovieList
    ),
    BottomNavItem(
        label = "Settings",
        icon = Icons.Outlined.Settings,
        selectedIcon = Icons.Filled.Settings,
        screen = Screen.Settings
    )
)

@Composable
fun BottomNavigationBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination

    NavigationBar(modifier = modifier) {
        bottomNavItems.forEach { item ->
            val isSelected = item.screen == currentRoute

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = if (isSelected) item.selectedIcon else item.icon,
                        contentDescription = item.label
                    )
                },
                label = { Text(item.label) },
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