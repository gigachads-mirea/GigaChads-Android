package com.rodyapal.gigachads.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.rodyapal.gigachads.model.entity.SearchedServer
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

	@Query("SELECT * FROM servers LEFT JOIN games WHERE servers.name LIKE :query OR servers.description LIKE :query OR games.name LIKE :query")
	suspend fun getSearchSuggestions(query: String): List<Server>

	//TODO: check query works
	@Query("SELECT * FROM servers LEFT JOIN searched_servers WHERE EXISTS(SELECT * FROM searched_servers WHERE searched_servers.serverId = servers.serverId)")
	suspend fun getSearchedServers(): List<Server>

	@Insert
	suspend fun setWasSearched(server: SearchedServer)

	@Delete
	suspend fun removeFromSearchHistory(server: SearchedServer)
}