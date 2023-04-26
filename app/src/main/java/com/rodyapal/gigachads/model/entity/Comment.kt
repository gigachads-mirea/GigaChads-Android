package com.rodyapal.gigachads.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
data class Comment(
	val authorName: String,
	val text: String,
	val writtenAt: Long,
	val likes: Int,
	@PrimaryKey
	val id: Long,
	val parentPostId: Long
)
