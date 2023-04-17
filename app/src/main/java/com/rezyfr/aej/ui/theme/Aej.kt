package com.rezyfr.aej.ui.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rezyfr.aej.ui.navigation.Screen
import com.rezyfr.aej.ui.screen.home.Home

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Aej(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(modifier = modifier) {
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(it)
        ) {
            composable(Screen.Home.route) {
                Home()
            }
        }
    }
}
