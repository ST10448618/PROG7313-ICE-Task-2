package com.example.betwise.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.betwise.ui.screens.*
import com.example.betwise.viewmodel.AuthViewModel
import com.example.betwise.auth.SessionManager
import com.example.betwise.ui.screens.LoginScreen
import com.example.betwise.viewmodel.CategoryViewModel
import com.example.betwise.viewmodel.SessionViewModel

@Composable
fun NavGraph(session: SessionManager, authViewModel: AuthViewModel) {

    val navController = rememberNavController()

    NavHost(navController, startDestination = "login") {

        @Composable
        fun NavGraph(
            session: SessionManager,
            authViewModel: AuthViewModel,
            categoryViewModel: CategoryViewModel,
            sessionViewModel: SessionViewModel
        ) {
            val navController = rememberNavController()

            NavHost(navController, startDestination = "login") {

                composable("login") {
                    LoginScreen(
                        authViewModel, session,
                        onLoginSuccess = { navController.navigate("categories") },
                        onNavigateRegister = { navController.navigate("register") }
                    )
                }

                composable("register") {
                    RegisterScreen(
                        authViewModel,
                        onRegisterSuccess = { navController.navigate("login") },
                        onBack = { navController.popBackStack() }
                    )
                }

                composable("categories") {
                    CategoryScreen(categoryViewModel) {
                        navController.navigate("detail/${it.id}")
                    }
                }

                composable("detail/{id}") { backStack ->
                    val id = backStack.arguments?.getString("id")!!.toInt()
                    CategoryDetailScreen(id, sessionViewModel)
                }
            }
        }
    }
}