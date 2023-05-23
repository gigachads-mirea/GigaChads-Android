package com.rodyapal.gigachads.model.network.entity

import com.rodyapal.gigachads.model.entity.Comment
import kotlinx.serialization.Serializable

@Serializable
data class NetworkComment(
	val authorName: String,
	val text: String,
	val writtenAt: Long,
	val parentPostId: Long,
	val id: Long,
) {
	fun toDomainModel() = Comment(
		authorName, text, writtenAt, parentPostId, id
	)
}

@Serializable
data class NetworkUserComment(
	val authorName: String,
	val text: String,
	val writtenAt: Long,
	val parentPostId: Long,
) {
	companion object {
		fun from(model: Comment) = NetworkUserComment(
			model.authorName, model.text, model.writtenAt, model.parentPostId
		)
	}
}