package com.rodyapal.gigachads.model.entity

import com.rodyapal.gigachads.model.local.entity.PostEntity

data class Post(
	val title: String,
	val content: String,
	val writtenAt: Long,
	val serverId: Long,
	val id: Long
) {
	fun toPostEntity() = PostEntity(
		title, content, writtenAt, serverId, id
	)
}

data class PostWithComments(
	val post: Post,
	val comments: List<Comment>
)