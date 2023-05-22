package com.rodyapal.gigachads.model.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "users")
data class User(
	val username: String,
	val email: String,
	val password: String,
	val isClient: Boolean,
	val userId: Long,
	@PrimaryKey
	val _localId: Int = 0
)

data class UserWithFavoriteServers(
	@Embedded val user: User,
	@Relation(
		parentColumn = "userId",
		entityColumn = "serverId",
		associateBy = Junction(ServerUserCrossRef::class)
	)
	val favoriteServers: List<Server>
)

data class ClientWithOwnedServer(
	@Embedded val user: User,
	@Relation(
		parentColumn = "userId",
		entityColumn = "ownerId"
	)
	val server: Server
)