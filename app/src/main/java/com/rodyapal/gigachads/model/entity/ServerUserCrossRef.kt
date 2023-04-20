package com.rodyapal.gigachads.model.entity

import androidx.room.Entity

@Entity(tableName = "favorite_servers", primaryKeys = ["userId", "serverId"])
data class ServerUserCrossRef(
	val userId: Long,
	val serverId: Long
)
