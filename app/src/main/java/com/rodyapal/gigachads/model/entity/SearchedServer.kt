package com.rodyapal.gigachads.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("searched_servers")
data class SearchedServer(
	@PrimaryKey
	val serverId: Long
)