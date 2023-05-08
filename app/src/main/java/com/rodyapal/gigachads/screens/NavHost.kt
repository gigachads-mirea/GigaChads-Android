package com.rodyapal.gigachads.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rodyapal.gigachads.screens.login.LoginScreen

@Composable
fun AppNavHost() {
	val navController = rememberNavController()
	NavHost(
		navController = navController,
		startDestination = Screen.Login.route,
	) {
		composable(Screen.FavServers.route) {

		}
		composable(Screen.Posts.route) {

		}
		composable(Screen.Search.route) {

		}
		composable(Screen.Login.route) {
//			LoginScreen(navController = navController)
		}
		composable(Screen.Register.route) {

		}
		composable(Screen.ServerDetails.route) {

		}
		composable(Screen.ServerPosts.route) {

		}
	}
}