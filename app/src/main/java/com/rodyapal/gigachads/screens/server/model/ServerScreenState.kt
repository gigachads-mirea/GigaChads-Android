package com.rodyapal.gigachads.screens.server.model

import com.rodyapal.gigachads.model.entity.Post
import com.rodyapal.gigachads.model.entity.Server

data class ServerScreenState(
	val server: Server,
	val posts: List<Post>,
	val gameName: String
)