package com.rezyfr.aej.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
}