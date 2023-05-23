package com.rodyapal.gigachads.model.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rodyapal.gigachads.model.local.entity.GameEntity

@Dao
interface GameDao {
	@Query("SELECT * FROM games")
	suspend fun getAll(): List<GameEntity>

	@Insert
	suspend fun save(games: List<GameEntity>)

	@Query("SELECT * FROM games WHERE id = :id")
	suspend fun getById(id: Long): GameEntity
}