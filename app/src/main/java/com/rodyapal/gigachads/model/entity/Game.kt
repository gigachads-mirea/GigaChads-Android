package com.rodyapal.gigachads.model.entity

import com.rodyapal.gigachads.model.local.entity.GameEntity

data class Game(
	val name: String,
	val id: Long
) {
	fun toGameEntity() = GameEntity(
		name, id
	)
}
