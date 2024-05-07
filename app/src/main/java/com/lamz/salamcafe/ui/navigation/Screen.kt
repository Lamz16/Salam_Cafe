package com.lamz.salamcafe.ui.navigation

sealed class Screen(val route : String) {
    object Home : Screen("home")
    object Order : Screen("order")
    object Profile : Screen("profile")
    object DetailCoffe : Screen("home/{menuId}"){
        fun createRoute(menuId : Long) = "home/$menuId"
    }
    object Catalog : Screen("catalog")
}