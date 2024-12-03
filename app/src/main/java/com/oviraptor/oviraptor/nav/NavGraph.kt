package com.oviraptor.oviraptor.nav

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.oviraptor.oviraptor.R
import com.oviraptor.oviraptor.main.ui.view.chat.ChatView
import com.oviraptor.oviraptor.main.ui.view.home.HomeView
import com.oviraptor.oviraptor.main.ui.view.MyView
import com.oviraptor.oviraptor.ui.theme.MainColor
import com.oviraptor.oviraptor.ui.theme.dropShadow
import com.oviraptor.oviraptor.ui.theme.pretendard
import com.oviraptor.oviraptor.user.ui.view.FirstView
import com.oviraptor.oviraptor.user.ui.view.login.LoginView
import com.oviraptor.oviraptor.user.ui.view.register.RegisterEmailView
import com.oviraptor.oviraptor.user.ui.view.register.RegisterNameView
import com.oviraptor.oviraptor.user.ui.view.register.RegisterPasswordView
import com.oviraptor.oviraptor.user.ui.view.register.RegisterViewModel

sealed class BottomNavItem(val route: String, val icon: Int, val label: String) {
    data object Home : BottomNavItem("home", R.drawable.home_icon, "홈")
    data object My : BottomNavItem("my", R.drawable.my_icon, "마이")
}
@Composable
fun BottomNavBar(navController: NavHostController) {
    val items = listOf(BottomNavItem.Home, BottomNavItem.My)
    NavigationBar (
        modifier = Modifier
            .height(80.dp)
            .dropShadow(color = Color(0xFF000000).copy(0.1f), blur = 4.dp, offsetY = 0.dp),
        containerColor = Color.White
    ){
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.label) },
                label = {
                    Text(
                        text = item.label,
                        fontFamily = pretendard,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                        },
                selected = navController.currentDestination?.route == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MainColor,
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = MainColor,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Transparent
                )
            )
        }
    }
}
@Composable
fun BottomNavWrapper(navController: NavHostController, content: @Composable () -> Unit) {
    Scaffold(
        bottomBar = { BottomNavBar(navController) },
        content = { paddingValues ->
            Box(
                modifier = Modifier.padding(paddingValues)
            ) {
                content()
            }
        }
    )
}
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
            BottomNavWrapper(navController) {
                HomeView(navController = navController)
            }
        }
        composable(route = NavGroup.MY) {
            BottomNavWrapper(navController) {
                MyView(navController = navController)
            }
        }
        composable(route = NavGroup.CHAT+"/{roomId}") {
            val roomId = it.arguments?.getString("roomId") ?: ""
            ChatView(navController = navController,roomId)
        }
    }
}