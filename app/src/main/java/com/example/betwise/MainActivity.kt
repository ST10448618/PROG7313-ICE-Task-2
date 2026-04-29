package com.example.betwise.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.betwise.auth.SessionManager
import com.example.betwise.viewmodel.*
import com.example.betwise.ui.screens.*

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
                viewModel = authViewModel,
                session = session,
                onLoginSuccess = {
                    navController.navigate("categories")
                },
                onNavigateRegister = {
                    navController.navigate("register")
                }
            )
        }

        composable("register") {
            RegisterScreen(
                viewModel = authViewModel,
                onRegisterSuccess = {
                    navController.navigate("login")
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable("categories") {
            CategoryScreen(
                viewModel = categoryViewModel,
                onClick = {
                    navController.navigate("detail/${it.id}")
                }
            )
        }

        composable("detail/{id}") { backStack ->
            val id = backStack.arguments?.getString("id")!!.toInt()

            CategoryDetailScreen(
                categoryId = id,
                sessionViewModel = sessionViewModel
            )
        }
    }
}