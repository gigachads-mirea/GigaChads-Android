package com.rodyapal.gigachads.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.rodyapal.gigachads.R

interface BottomNavScreen {
	val iconId: Int
	val contentDescription: Int
}

sealed class Screen(
	val route: String,
	@StringRes val labelId: Int
) {
	object FavServers : Screen("fav_servers", R.string.screen_fav_servers), BottomNavScreen {
		@DrawableRes override val iconId: Int = R.drawable.bottom_nav_fav_servers_icon
		@StringRes override val contentDescription: Int = R.string.description_favorite_servers_screen
	}
	object Post : Screen("post/{postId}", R.string.screen_posts) {
		const val baseRoute = "post"
		const val navArgName = "postId"
	}
	object Search : Screen("search", R.string.screen_search), BottomNavScreen {
		@DrawableRes override val iconId: Int = R.drawable.bottom_nav_search_icon
		@StringRes override val contentDescription: Int = R.string.description_search_screen
	}
	object Login : Screen("login", R.string.screen_login)
	object Register : Screen("register", R.string.screen_register)
	object ServerDetails : Screen("server_details/{serverId}", R.string.screen_server_details) {
		const val baseRoute = "server_details"
		const val navArgName = "serverId"
	}
	object ServerPosts : Screen("server_posts", R.string.screen_servers_posts), BottomNavScreen {
		@DrawableRes override val iconId: Int = R.drawable.bottom_nav_posts_icon
		@StringRes override val contentDescription: Int = R.string.description_server_posts_screen
	}

	companion object {
		val allUser = listOf(
			FavServers, Login, Post, Register, Search, ServerDetails, ServerPosts
		)

		val allClient = listOf<Screen>(/*TODO*/)

		val bottomNavScreens = listOf<Screen>(
			Search, FavServers, ServerPosts
		)

		val startDestination = Login
		val homeScreen = FavServers
	}
}