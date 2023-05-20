package com.rodyapal.gigachads.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "comments")
data class Comment(
	val authorName: String,
	val text: String,
	val writtenAt: Long,
	val likes: Int,
	val parentPostId: Long,
	@PrimaryKey(autoGenerate = true)
	val id: Long = 0,
)
