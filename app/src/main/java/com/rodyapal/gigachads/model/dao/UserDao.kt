package com.rodyapal.gigachads.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.rodyapal.gigachads.model.entity.User
import com.rodyapal.gigachads.model.entity.UserWithFavoriteServers

@Dao
interface UserDao {
	@Query("SELECT * FROM users WHERE userId = 0")
	suspend fun getLoggedUser(): User

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun save(user: User)

	@Transaction
	@Query("SELECT * FROM users WHERE userId = 0")
	suspend fun getUserWithFavoriteServers(): UserWithFavoriteServers

	@Query("SELECT serverId FROM favorite_servers WHERE userId = :userId")
	suspend fun getFavoriteServerIds(userId: Int): List<Long>
}