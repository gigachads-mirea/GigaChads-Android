package com.rodyapal.gigachads.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.rodyapal.gigachads.model.entity.Post
import com.rodyapal.gigachads.model.entity.PostWithComments

@Dao
interface PostDao {
	@Query("SELECT * FROM posts WHERE serverId = :id")
	suspend fun getByServerId(id: Long): List<Post>

	@Query("SELECT * FROM posts WHERE id = :id")
	suspend fun getById(id: Long): Post

	@Transaction
	@Query("SELECT * FROM posts WHERE serverId = :id")
	suspend fun getByServerIdWithComments(id: Long): PostWithComments

	@Query("SELECT * FROM posts")
	suspend fun getAll(): List<Post>

	@Insert
	suspend fun save(post: Post)

	@Query("SELECT EXISTS(SELECT 1 FROM liked_posts WHERE postId = :postId)")
	suspend fun isLikedByCurrentUser(postId: Long): Boolean
}