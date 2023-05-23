package com.rodyapal.gigachads.model.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("liked_posts")
data class LikedPostEntity(
	@PrimaryKey
	val postId: Long
)