package com.rodyapal.gigachads.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
data class Game(
	val name: String,
	@PrimaryKey
	val id: Long
)
