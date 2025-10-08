package org.fahimdev.cmpboilerplate.core.components.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
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
    modifier: Modifier = Modifier
) {
//    val currentKey = backStack.lastOrNull()
//
//    NavigationBar(modifier = modifier) {
//        bottomNavItems.forEach { item ->
//            val isSelected = when (currentKey) {
//                is Screen.MovieList -> item.screen is Screen.MovieList
//                is Screen.Settings -> item.screen is Screen.Settings
//                else -> false
//            }
//
//            NavigationBarItem(
//                icon = {
//                    Icon(
//                        imageVector = if (isSelected) item.selectedIcon else item.icon,
//                        contentDescription = item.label,
//                        modifier = Modifier.size(24.dp)
//                    )
//                },
//                label = { Text(item.label) },
//                selected = isSelected,
//                onClick = {
//                    if (!isSelected) {
//                        val targetExists = backStack.toList().any { it == item.screen }
//
//                        if (targetExists) {
//                            while (backStack.lastOrNull() != item.screen) {
//                                backStack.removeLastOrNull()
//                            }
//                        } else {
//                            backStack.add(item.screen)
//                        }
//                    }
//                }
//            )
//        }
//    }
}