package com.oviraptor.oviraptor.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.oviraptor.oviraptor.main.ui.view.HomeView
import com.oviraptor.oviraptor.user.ui.view.FirstView
import com.oviraptor.oviraptor.user.ui.view.LoginView
import com.oviraptor.oviraptor.user.ui.view.RegisterView

@Composable
fun NavGraph(navController: NavHostController) {
    val startDestination =  NavGroup.FIRST

    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = NavGroup.FIRST) {
            FirstView(navController = navController)
        }
        composable(route = NavGroup.LOGIN) {
            LoginView(navController = navController)
        }
        composable(route = NavGroup.REGISTER) {
            RegisterView(navController = navController)
        }
        composable(route = NavGroup.HOME) {
            HomeView(navController = navController)
        }
    }
}