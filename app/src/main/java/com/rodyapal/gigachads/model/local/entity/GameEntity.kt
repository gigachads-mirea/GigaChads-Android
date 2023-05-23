package com.rodyapal.gigachads.model.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rodyapal.gigachads.model.entity.Game

@Entity(tableName = "games")
data class GameEntity(
	val name: String,
	@PrimaryKey
	val id: Long
) {
	fun toDomainModel() = Game(
		name, id
	)
}
