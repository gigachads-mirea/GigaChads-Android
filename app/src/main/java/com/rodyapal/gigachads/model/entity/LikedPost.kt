package com.rodyapal.gigachads.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("liked_posts")
data class LikedPost(
	@PrimaryKey
	val postId: Long
)