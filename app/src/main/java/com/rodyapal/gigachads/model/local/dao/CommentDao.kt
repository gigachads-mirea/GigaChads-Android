package com.rodyapal.gigachads.model.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rodyapal.gigachads.model.local.entity.CommentEntity
import com.rodyapal.gigachads.model.local.entity.LikedCommentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentDao {
	@Query("SELECT * FROM comments WHERE id = :commentId")
	fun getById(commentId: Long): Flow<CommentEntity>

	@Query("SELECT * FROM comments WHERE parentPostId = :postId")
	fun getByPostId(postId: Long): Flow<List<CommentEntity>>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun save(comments: List<CommentEntity>)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun save(comment: CommentEntity)

	@Query("SELECT EXISTS(SELECT 1 FROM liked_comments WHERE commentId = :commentId)")
	suspend fun isLikedByCurrentUser(commentId: Long): Boolean

	@Insert
	suspend fun setCommentLiked(comment: LikedCommentEntity)

	@Delete
	suspend fun setCommentNonLiked(comment: LikedCommentEntity)
}