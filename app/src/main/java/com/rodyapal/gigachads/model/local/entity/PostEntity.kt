package com.rodyapal.gigachads.model.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.rodyapal.gigachads.model.entity.Post
import com.rodyapal.gigachads.model.entity.PostWithComments

@Entity(tableName = "posts")
data class PostEntity(
	val title: String,
	val content: String,
	val writtenAt: Long,
	val serverId: Long,
	@PrimaryKey
	val id: Long
) {
	fun toDomainModel() = Post(
		title, content, writtenAt, serverId, id
	)
}