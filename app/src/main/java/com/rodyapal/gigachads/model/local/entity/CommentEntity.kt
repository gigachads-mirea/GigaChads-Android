package com.rodyapal.gigachads.model.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rodyapal.gigachads.model.entity.Comment

@Entity(tableName = "comments")
data class CommentEntity(
	val authorName: String,
	val text: String,
	val writtenAt: Long,
	val parentPostId: Long,
	@PrimaryKey(autoGenerate = true)
	val id: Long = 0,
) {
	fun toDomainModel() = Comment(
		authorName, text, writtenAt, parentPostId, id
	)
}
