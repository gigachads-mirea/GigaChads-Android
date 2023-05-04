package com.rodyapal.gigachads.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rodyapal.gigachads.model.entity.Comment

@Dao
interface CommentDao {
	@Query("SELECT * FROM comments WHERE parentPostId = :postId")
	suspend fun getByPostId(postId: Long): List<Comment>

	@Insert
	suspend fun save(comment: Comment)
}