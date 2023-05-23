package com.rodyapal.gigachads.model.repository

import com.rodyapal.gigachads.model.entity.Game
import com.rodyapal.gigachads.model.local.dao.GameDao
import com.rodyapal.gigachads.model.network.api.GameApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GameRepository(
	private val dao: GameDao,
	private val api: GameApi
) {
	//Game list does not update
	init {
		MainScope().launch {
			withContext(Dispatchers.Default) {
				val data = dao.getAll()
				if (data.isEmpty()) {
					api.getAll().let { games ->
						dao.save(
							games.map { it.toDomainModel().toGameEntity() }
						)
					}
				}
			}
		}
	}

	suspend fun getById(gameId: Long): Game {
		return dao.getById(gameId).toDomainModel()
	}
}