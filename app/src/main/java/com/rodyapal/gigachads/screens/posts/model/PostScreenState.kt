package com.rodyapal.gigachads.screens.posts.model

import com.rodyapal.gigachads.model.entity.Comment
import com.rodyapal.gigachads.model.entity.Post

sealed class PostScreenState {
	data class Display(
		val post: Post,
		val serverName: String,
		val isLikedByUser: Boolean,
	) : PostScreenState()

	data class Comments(
		val comments: List<Pair<Comment, Boolean>>,
		val userComment: String,
	) : PostScreenState()
}