package com.rodyapal.gigachads.screens.server.model

import com.rodyapal.gigachads.model.entity.Post
import com.rodyapal.gigachads.model.entity.Server

sealed class ServerScreenState {

	object Loading : ServerScreenState()
	data class Info(
		val server: Server,
		val latestPost: Post,
		val gameName: String
	) : ServerScreenState()

	data class Posts(
		val serverName: String,
		val posts: List<Post>
	) : ServerScreenState()
}

