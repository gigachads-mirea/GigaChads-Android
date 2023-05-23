package com.rodyapal.gigachads.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rodyapal.gigachads.R
import com.rodyapal.gigachads.model.repository.UserRepository
import com.rodyapal.gigachads.screens.favservers.FavoriteServersScreen
import com.rodyapal.gigachads.screens.login.LoginScreen
import com.rodyapal.gigachads.screens.post.PostScreen
import com.rodyapal.gigachads.screens.register.RegisterScreen
import com.rodyapal.gigachads.screens.search.SearchScreen
import com.rodyapal.gigachads.screens.server.ServerScreen
import com.rodyapal.gigachads.screens.serverposts.ServerPostsScreen
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavHost(
	userRepository: UserRepository = koinInject()
) {
	val navController = rememberNavController()
	val scope = rememberCoroutineScope()
	val snackbarHostState = remember { SnackbarHostState() }
	val authState = userRepository.authState.collectAsState()
	Scaffold(
		snackbarHost = {
			SnackbarHost(hostState = snackbarHostState)
		},
		bottomBar = {
			if (authState.value != UserRepository.AuthState.NotAuthenticated) {
				BottomNav(
					navController = navController
				)
			}
		}
	) { paddingValues ->
		NavHost(
			modifier = Modifier.padding(paddingValues),
			navController = navController,
			startDestination = when (authState.value) {
				is UserRepository.AuthState.Authenticated -> Screen.homeScreen.route
				is UserRepository.AuthState.NotAuthenticated -> Screen.Login.route
			},
		) {
			composable(Screen.FavServers.route) {
				FavoriteServersScreen(
					onNavigateToServerScreen = { serverId ->
						navController.navigate("${Screen.ServerDetails.baseRoute}/$serverId")
					}
				)
			}
			composable(Screen.Login.route) {
				LoginScreen(
					onMoveToRegister = {
						navController.navigate(Screen.Register.route)
					},
					onSuccessfulLogin = {
						navController.navigate(Screen.homeScreen.route)
					},
					onLoginError = {
						val message = stringResource(R.string.text_login_error_message)
						SideEffect {
							scope.launch {
								snackbarHostState.showSnackbar(message)
							}
						}
					}
				)
			}
			composable(
				route = Screen.Post.route,
				arguments = listOf(navArgument(Screen.Post.navArgName) {
					type = NavType.LongType
				})
			) {
				PostScreen(
					postId = it.arguments?.getLong(Screen.Post.navArgName) ?: 0 //Maybe do something more here later
				)
			}
			composable(Screen.Register.route) {
				RegisterScreen(
					onRegistered = {
						navController.navigate(Screen.homeScreen.route)
					},
					onAbortRegistration = {
						navController.navigate(Screen.Login.route)
					},
					onError = {}
				)
			}
			composable(Screen.Search.route) {
				SearchScreen(
					onNavigateToServer = { serverId ->
						navController.navigate("${Screen.ServerDetails.baseRoute}/$serverId")
					}
				)
			}
			composable(
				route = Screen.ServerDetails.route,
				arguments = listOf(navArgument(Screen.ServerDetails.navArgName) {
					type = NavType.LongType
				})
			) {
				ServerScreen(
					serverId = it.arguments?.getLong(Screen.ServerDetails.navArgName) ?: 0, //Maybe do something more here later
					onViewPost = { postId ->
						navController.navigate("${Screen.Post.baseRoute}/$postId")
					}
				)
			}
			composable(Screen.ServerPosts.route) {
				ServerPostsScreen(
					onViewPost = { postId ->
						navController.navigate("${Screen.Post.baseRoute}/$postId")
					}
				)
			}
		}
	}
}