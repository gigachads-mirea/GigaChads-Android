package com.rodyapal.gigachads.model.entity

import androidx.room.Embedded
import com.rodyapal.gigachads.model.local.entity.ServerEntity

data class Server(
	val name: String,
	val description: String,
	val locale: String,
	val isModded: Boolean,
	val hasAntiCheat: Boolean,
	val rating: Float,
	val gameId: Long,
	val ownerId: Long,
	val serverId: Long
) {
	fun toServerEntity() = ServerEntity(
		name, description, locale, isModded, hasAntiCheat, rating, gameId, ownerId, serverId
	)
}

data class ServerWithPosts(
	val server: Server,
	val posts: List<Post>
)

data class ServerWithGameName(
	@Embedded val server: Server,
	val gameName: String
)