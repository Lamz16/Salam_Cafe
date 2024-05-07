package com.lamz.salamcafe

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lamz.salamcafe.ui.components.BottomBar
import com.lamz.salamcafe.ui.navigation.Screen
import com.lamz.salamcafe.ui.screen.catalog.CatalogScreen
import com.lamz.salamcafe.ui.screen.detail.DetailScreen
import com.lamz.salamcafe.ui.screen.home.HomeScreen
import com.lamz.salamcafe.ui.screen.order.OrderScreen
import com.lamz.salamcafe.ui.screen.profile.ProfileScreen
import com.lamz.salamcafe.utils.Utils

@Composable
fun CoffeShopApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStack?.destination?.route

    Scaffold(
        bottomBar = {
            for (i in Utils.listScreenWithoutBottomBar.indices) {
                if (currentRoute != Utils.listScreenWithoutBottomBar[i]) {
                    BottomBar(navController)
                }
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { menuId ->
                        navController.navigate(Screen.DetailCoffe.createRoute(menuId))
                    }
                )
            }
            composable(
                route = Screen.DetailCoffe.route,
                arguments = listOf(navArgument("menuId") { type = NavType.LongType }),

                ) {
                val id = it.arguments?.getLong("menuId") ?: -1L
                DetailScreen(menuId = id, navigateBack = { navController.navigateUp() },
                    navigateToCart = {
                        navController.popBackStack()
                        navController.navigate(Screen.Order.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    })

            }
            composable(Screen.Catalog.route){
                CatalogScreen(navigateToDetail = {menuId ->
                    navController.navigate(Screen.DetailCoffe.createRoute(menuId))
                })
            }
            composable(Screen.Order.route) {
                val context = LocalContext.current
                OrderScreen(
                    onOrderButtonClicked = { message ->
                        Utils.shareOrder(context, message)
                    }
                )
            }

            composable(Screen.Profile.route) {
                ProfileScreen()
            }

        }
    }

}