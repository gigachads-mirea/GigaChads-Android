package com.rodyapal.gigachads.model.network.api

import com.rodyapal.gigachads.model.network.entity.NetworkGame
import io.ktor.client.HttpClient

const val GAME_API_URL = "" //TODO

class GameApi(
	private val client: HttpClient
) {
	suspend fun getAll(): List<NetworkGame> {
		return ServerApi.mock.distinctBy { it.gameId }.map {
			NetworkGame(
				name = List((6..12).random()) {
					('a'..'z').random()
				}.joinToString(""),
				id = it.gameId
			)
		}
	}
}
