package com.rodyapal.gigachads.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "liked_comments")
data class LikedComment(
	@PrimaryKey
	val commentId: Long
)