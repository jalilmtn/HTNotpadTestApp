package com.example.htnotpadtestapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions

@Composable
fun rememberHTAppState(
    navController: NavHostController = rememberNavController()
): HTAppState {
    return remember(navController) {
        HTAppState(navController)
    }
}

@Stable
class HTAppState(
    val navController: NavHostController
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    // Checks if current route starts with any of the top level routes
    private val currentRouteIsTopLevel: Boolean
        @Composable get() = topLevelRouteList.any { topLevelRoute ->
            currentDestination?.route?.startsWith(
                topLevelRoute
            ) ?: true
        }


    val isTopLevelNavigation: Boolean @Composable get() = currentRouteIsTopLevel

    fun navigateToTopLevelDestination(destination: NavigationTopLevelDestination) {
        val topLevelNavOptions = navOptions {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
        navController.navigate(
            destination.route,
            topLevelNavOptions,
        )
    }

    companion object {
        private val topLevelRouteList =
            NavigationTopLevelDestination.entries.map { it.route }
    }

}