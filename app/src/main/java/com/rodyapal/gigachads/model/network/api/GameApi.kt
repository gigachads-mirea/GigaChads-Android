package com.rodyapal.gigachads.model.network.api

import com.rodyapal.gigachads.model.network.entity.NetworkGame
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

const val GAME_API_URL = "http://mirea-gigachads/api/v1/games"

class GameApi(
	private val client: HttpClient,
	private val apiPath: String
) {
	suspend fun getAll(): List<NetworkGame> {
		return client.get(apiPath).body()
	}
}
