package com.rodyapal.gigachads.model.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "posts")
data class Post(
	val title: String,
	val content: String,
	val writtenAt: Long,
	val likes: Int,
	val serverId: Long,
	@PrimaryKey
	val id: Long
)

data class PostWithComments(
	@Embedded val post: Post,
	@Relation(
		parentColumn = "id",
		entityColumn = "parentPostId"
	)
	val comments: List<Comment>
)