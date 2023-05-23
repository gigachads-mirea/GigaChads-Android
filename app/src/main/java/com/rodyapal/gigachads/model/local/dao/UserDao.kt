package com.rodyapal.gigachads.model.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rodyapal.gigachads.model.local.entity.FavoriteServerEntity
import com.rodyapal.gigachads.model.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
	@Query("SELECT * FROM users WHERE _localId = 0")
	suspend fun getLoggedUser(): UserEntity

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun save(user: UserEntity)

	@Query("SELECT EXISTS(SELECT 1 FROM users WHERE _localId = 0)")
	suspend fun isAuthenticatedLocally(): Boolean

	@Query("DELETE FROM users")
	suspend fun logout()

	@Insert
	suspend fun setFavorite(servers: List<FavoriteServerEntity>)

	@Delete
	suspend fun removeFavorite(server: FavoriteServerEntity)

	@Query("SELECT serverId FROM favorite_servers WHERE userId = :userId")
	fun getFavoriteServerIds(userId: Long): Flow<List<Long>>
}