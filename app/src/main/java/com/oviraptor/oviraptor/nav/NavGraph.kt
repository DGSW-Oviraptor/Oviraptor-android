package com.oviraptor.oviraptor.nav

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.oviraptor.oviraptor.friend.ui.view.FriendView
import com.oviraptor.oviraptor.main.ui.view.ChatView
import com.oviraptor.oviraptor.main.ui.view.HomeView
import com.oviraptor.oviraptor.user.ui.view.FirstView
import com.oviraptor.oviraptor.user.ui.view.login.LoginView
import com.oviraptor.oviraptor.user.ui.view.register.RegisterEmailView
import com.oviraptor.oviraptor.user.ui.view.register.RegisterNameView
import com.oviraptor.oviraptor.user.ui.view.register.RegisterPasswordView
import com.oviraptor.oviraptor.user.ui.view.register.RegisterViewModel

@Composable
fun NavGraph(navController: NavHostController) {
    val startDestination =  NavGroup.FIRST
    val registerViewModel: RegisterViewModel = hiltViewModel()
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = NavGroup.FIRST) {
            FirstView(navController = navController)
        }
        composable(route = NavGroup.LOGIN) {
            LoginView(navController = navController)
        }
        composable(route = NavGroup.REGISTER_NAME) {
            RegisterNameView(navController = navController, viewModel = registerViewModel)
        }
        composable(route = NavGroup.REGISTER_PASSWORD) {
            RegisterPasswordView(navController = navController, viewModel = registerViewModel)
        }
        composable(route = NavGroup.REGISTER_EMAIL) {
            RegisterEmailView(navController = navController, viewModel = registerViewModel)
        }
        composable(route = NavGroup.HOME) {
            HomeView(navController = navController)
        }
        composable(route = NavGroup.FRIEND) {
            FriendView(navController = navController)
        }
        composable(route = NavGroup.CHAT+"/{roomId}") {
            val roomId = it.arguments?.getString("roomId") ?: ""
            ChatView(navController = navController,roomId)
        }
    }
}