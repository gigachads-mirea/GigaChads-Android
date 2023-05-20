package com.rodyapal.gigachads.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.rodyapal.gigachads.model.entity.Server
import com.rodyapal.gigachads.model.entity.ServerWithPosts

@Dao
interface ServerDao {
	@Query("SELECT * FROM servers")
	suspend fun getAll(): List<Server>

	@Transaction
	@Query("SELECT * FROM servers WHERE serverId = :id")
	suspend fun getById(id: Long): ServerWithPosts

	@Insert
	suspend fun save(server: Server)

	@Query("SELECT * FROM servers WHERE serverId in (:ids)")
	suspend fun get(ids: List<Long>): List<Server>

	//TODO: check query works
	@Query("SELECT * FROM servers INNER JOIN posts WHERE posts.id = :postId AND posts.serverId = servers.serverId")
	suspend fun getParentForPost(postId: Long): Server
}