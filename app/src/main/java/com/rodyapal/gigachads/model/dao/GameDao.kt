package com.rodyapal.gigachads.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rodyapal.gigachads.model.entity.Game

@Dao
interface GameDao {
	@Query("SELECT * FROM games")
	suspend fun getAll(): List<Game>

	@Insert
	suspend fun save(game: Game)
}