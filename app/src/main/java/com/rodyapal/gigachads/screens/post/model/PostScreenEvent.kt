package com.rodyapal.gigachads.screens.post.model

sealed class PostScreenEvent {
	data class EnterScreen(
		val postId: Long
	) : PostScreenEvent()
	data class OnCommentLike(
		val commentId: Long
	) : PostScreenEvent()
	data class OnUserCommentInput(
		val input: String
	) : PostScreenEvent()
	object OnUserCommentDone : PostScreenEvent()
	object OnBackToPost : PostScreenEvent()
	object OnLikePost : PostScreenEvent()
	object OnViewCommentsClick : PostScreenEvent()
}
