package com.rodyapal.gigachads.model.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "servers")
data class Server(
	val name: String,
	val description: String,
	val locale: String,
	val isModded: Boolean,
	val hasAntiCheat: Boolean,
	val rating: Float,
	val gameId: Long,
	val ownerId: Long,

	@PrimaryKey
	val serverId: Long
)

data class ServerWithPosts(
	@Embedded val server: Server,
	@Relation(
		parentColumn = "serverId",
		entityColumn = "serverId"
	)
	val posts: List<Post>
)