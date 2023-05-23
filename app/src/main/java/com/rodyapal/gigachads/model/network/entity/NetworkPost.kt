package com.rodyapal.gigachads.model.network.entity

import com.rodyapal.gigachads.model.entity.Post
import kotlinx.serialization.Serializable

@Serializable
data class NetworkPost(
	val title: String,
	val content: String,
	val writtenAt: Long,
	val serverId: Long,
	val id: Long
) {
	fun toDomainModel() = Post(
		title, content, writtenAt, serverId, id
	)
}