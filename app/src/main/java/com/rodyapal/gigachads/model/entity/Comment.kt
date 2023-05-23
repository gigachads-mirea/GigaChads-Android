package com.rodyapal.gigachads.model.entity

import com.rodyapal.gigachads.model.local.entity.CommentEntity

data class Comment(
	val authorName: String,
	val text: String,
	val writtenAt: Long,
	val parentPostId: Long,
	val id: Long,
) {
	fun toEntityModel() = CommentEntity(
		authorName, text, writtenAt, parentPostId, id
	)
}
