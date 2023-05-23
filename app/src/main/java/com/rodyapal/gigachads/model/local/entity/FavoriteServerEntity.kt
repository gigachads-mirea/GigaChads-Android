package com.rodyapal.gigachads.model.local.entity

import androidx.room.Entity

@Entity(tableName = "favorite_servers", primaryKeys = ["userId", "serverId"])
data class FavoriteServerEntity(
	val userId: Long,
	val serverId: Long
)
