package com.rodyapal.gigachads.screens.server.model

sealed class ServerScreenEvent {
	object OnAddToFavorite : ServerScreenEvent()
	object OnBackToInfo : ServerScreenEvent()
	object OnSeeOtherPosts : ServerScreenEvent()
	data class EnterScreen(
		val serverId: Long
	) : ServerScreenEvent()
}