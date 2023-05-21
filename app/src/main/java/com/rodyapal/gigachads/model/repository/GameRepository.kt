package com.rodyapal.gigachads.model.repository

import com.rodyapal.gigachads.model.dao.GameDao
import com.rodyapal.gigachads.model.entity.Game
import com.rodyapal.gigachads.model.network.api.GameApi
import kotlinx.coroutines.delay

class GameRepository(
	private val dao: GameDao,
	private val api: GameApi
) {
	suspend fun getById(gameId: Long): Game {
		delay(100)
		return dao.getById(gameId)
	}
}