package com.rodyapal.gigachads.model.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rodyapal.gigachads.model.local.entity.SearchedServerEntity
import com.rodyapal.gigachads.model.local.entity.ServerEntity
import com.rodyapal.gigachads.model.local.entity.FavoriteServerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ServerDao {
	@Query("SELECT * FROM servers WHERE serverId = :id")
	fun getById(id: Long): Flow<ServerEntity>

	@Insert
	suspend fun save(server: ServerEntity)

	@Insert
	suspend fun save(servers: List<ServerEntity>)

	@Query("SELECT * FROM servers WHERE serverId in (:ids)")
	fun get(ids: List<Long>): Flow<List<ServerEntity>>

	//TODO: check query works
//	@Query("SELECT * FROM servers INNER JOIN posts WHERE posts.id = :postId AND posts.serverId = servers.serverId")
//	fun getParentForPost(postId: Long): Flow<ServerEntity>

	@Query("SELECT * FROM servers LEFT JOIN games WHERE servers.name LIKE :query OR servers.description LIKE :query OR games.name LIKE :query")
	suspend fun getSearchSuggestions(query: String): List<ServerEntity>

	//TODO: check query works
	@Query("SELECT * FROM servers LEFT JOIN searched_servers WHERE EXISTS(SELECT * FROM searched_servers WHERE searched_servers.serverId = servers.serverId)")
	suspend fun getSearchedServers(): List<ServerEntity>

	@Insert
	suspend fun setWasSearched(server: SearchedServerEntity)

	@Delete
	suspend fun removeFromSearchHistory(server: SearchedServerEntity)

	@Query("SELECT EXISTS(SELECT 1 FROM favorite_servers WHERE serverId = :serverId AND userId = :userId)")
	suspend fun isFavorite(serverId: Long, userId: Long): Boolean

	@Insert
	suspend fun setFavorite(ref: FavoriteServerEntity)

	@Delete
	suspend fun removeFavorite(ref: FavoriteServerEntity)
}