package com.rodyapal.gigachads.screens.favservers.model

sealed class FavoriteServersScreenState {
	data class Display(
		val servers: List<ServerBasicInfo>
	) : FavoriteServersScreenState()

	object Empty : FavoriteServersScreenState()
}
