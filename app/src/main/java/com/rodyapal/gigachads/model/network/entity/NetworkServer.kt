package com.rodyapal.gigachads.model.network.entity

import com.rodyapal.gigachads.model.entity.Server
import kotlinx.serialization.Serializable

@Serializable
data class NetworkServer(
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
	fun toDomainModel() = Server(
		name, description, locale, isModded, hasAntiCheat, rating, gameId, ownerId, serverId
	)
}