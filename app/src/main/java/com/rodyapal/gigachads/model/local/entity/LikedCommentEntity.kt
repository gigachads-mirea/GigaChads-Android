package com.rodyapal.gigachads.model.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "liked_comments")
data class LikedCommentEntity(
	@PrimaryKey
	val commentId: Long
)