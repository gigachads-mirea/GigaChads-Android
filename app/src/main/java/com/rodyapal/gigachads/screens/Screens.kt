package com.rodyapal.gigachads.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.rodyapal.gigachads.R

interface BottomNavScreen {
	val iconId: Int
}

sealed class Screen(
	val route: String,
	@StringRes val labelId: Int
) {
	object FavServers : Screen("fav_servers", R.string.screen_fav_servers), BottomNavScreen {
		@DrawableRes override val iconId: Int = R.drawable.bottom_nav_fav_servers_icon
	}
	object Posts : Screen("posts", R.string.screen_posts), BottomNavScreen {
		@DrawableRes override val iconId: Int = R.drawable.bottom_nav_posts_icon
	}
	object Search : Screen("search", R.string.screen_search), BottomNavScreen {
		@DrawableRes override val iconId: Int = R.drawable.bottom_nav_search_icon
	}
	object Login : Screen("login", R.string.screen_login)
	object Register : Screen("register", R.string.screen_register)
	object ServerDetails : Screen("server_details", R.string.screen_server_details)
	object ServerPosts : Screen("server_posts", R.string.screen_servers_posts)

	companion object {
		val allUser = listOf(
			FavServers, Login, Posts, Register, Search, ServerDetails, ServerPosts
		)

		val allClient = listOf<Screen>(/*TODO*/)

		val bottomNavScreens = listOf(
			Search, FavServers, Posts
		)

		val startDestination = Login
		val homeScreen = FavServers
	}
}