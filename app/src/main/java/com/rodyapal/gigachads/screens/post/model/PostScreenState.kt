package com.rodyapal.gigachads.screens.post.model

import com.rodyapal.gigachads.model.entity.Comment
import com.rodyapal.gigachads.model.entity.Post

sealed class PostScreenState {
	data class Display(
		val post: Post,
		val serverName: String,
		val isLikedByUser: Boolean,
		val isError: Boolean = false
	) : PostScreenState()

	data class Comments(
		val comments: List<Pair<Comment, Boolean>>,
		val userComment: String,
		val postTitle: String
	) : PostScreenState()

	object Loading : PostScreenState()
}