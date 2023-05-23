package com.rodyapal.gigachads.model.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rodyapal.gigachads.model.local.entity.LikedPostEntity
import com.rodyapal.gigachads.model.local.entity.PostEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {
	@Query("SELECT * FROM posts WHERE serverId = :id")
	fun getByServerId(id: Long): Flow<List<PostEntity>>

	@Query("SELECT * FROM posts WHERE id = :id")
	fun getById(id: Long): Flow<PostEntity>

	@Insert
	suspend fun save(post: PostEntity)

	@Insert
	suspend fun save(posts: List<PostEntity>)

	@Query("SELECT EXISTS(SELECT 1 FROM liked_posts WHERE postId = :postId)")
	suspend fun isLikedByCurrentUser(postId: Long): Boolean

	@Insert
	suspend fun setLiked(post: LikedPostEntity)

	@Delete
	suspend fun removeLiked(post: LikedPostEntity)
}