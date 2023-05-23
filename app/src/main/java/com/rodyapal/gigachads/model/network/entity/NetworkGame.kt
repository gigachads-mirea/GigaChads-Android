package com.rodyapal.gigachads.model.network.entity

import com.rodyapal.gigachads.model.entity.Game
import kotlinx.serialization.Serializable

@Serializable
data class NetworkGame(
	val name: String,
	val id: Long
) {
	fun toDomainModel() = Game(
		name, id
	)
}
