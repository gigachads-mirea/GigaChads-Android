package com.rodyapal.gigachads.screens.serverposts.model

import com.rodyapal.gigachads.model.entity.Post
import com.rodyapal.gigachads.model.entity.ServerWithPosts

data class PostsForServer(
	val serverName: String,
	val posts: List<Post>
) {
	companion object {
		fun from(item: ServerWithPosts): PostsForServer = PostsForServer(
			serverName = item.server.name,
			posts = item.posts
		)
	}
}