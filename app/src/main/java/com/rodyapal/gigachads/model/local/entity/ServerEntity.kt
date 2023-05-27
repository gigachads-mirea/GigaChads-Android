package com.rodyapal.gigachads.model.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rodyapal.gigachads.model.entity.Server
import com.rodyapal.gigachads.model.entity.ServerWithGameName

@Entity(tableName = "servers")
data class ServerEntity(
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
) {
	fun toDomainModel() = Server(
		name, description, locale, isModded, hasAntiCheat, rating, gameId, ownerId, serverId
	)
}

data class ServerWithGameNameEntity(
	@Embedded val server: ServerEntity,
	val gameName: String
) {
	fun toDomainModel() = ServerWithGameName(
		server = server.toDomainModel(),
		gameName = gameName
	)
}