package com.rodyapal.gigachads.screens.favservers.model

sealed class FavServersScreenState {
	data class Display(
		val servers: List<ServerBasicInfo>
	) : FavServersScreenState()

	object Empty : FavServersScreenState()
}
