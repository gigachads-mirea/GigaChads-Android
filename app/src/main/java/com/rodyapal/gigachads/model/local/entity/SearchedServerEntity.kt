package com.rodyapal.gigachads.model.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("searched_servers")
data class SearchedServerEntity(
	@PrimaryKey
	val serverId: Long
)