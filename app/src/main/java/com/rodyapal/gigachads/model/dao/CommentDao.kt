package com.rodyapal.gigachads.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rodyapal.gigachads.model.entity.Comment
import com.rodyapal.gigachads.model.entity.LikedComment

@Dao
interface CommentDao {
	@Query("SELECT * FROM comments WHERE parentPostId = :postId")
	suspend fun getByPostId(postId: Long): List<Comment>

	@Insert
	suspend fun save(comment: Comment)

	@Query("SELECT EXISTS(SELECT 1 FROM liked_comments WHERE commentId = :commentId)")
	suspend fun isLikedByCurrentUser(commentId: Long): Boolean

	@Insert
	suspend fun setCommentLiked(comment: LikedComment)

	@Delete
	suspend fun setCommentNonLiked(comment: LikedComment)
}