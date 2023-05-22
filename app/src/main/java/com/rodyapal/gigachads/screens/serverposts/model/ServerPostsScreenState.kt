package com.rodyapal.gigachads.screens.serverposts.model

sealed class ServerPostsScreenState {
	data class Display(
		val serversWithPosts: List<PostsForServer>
	) : ServerPostsScreenState()

	object Loading : ServerPostsScreenState()
}