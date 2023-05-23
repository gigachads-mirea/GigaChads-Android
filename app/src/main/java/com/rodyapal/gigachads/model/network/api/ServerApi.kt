package com.rodyapal.gigachads.model.network.api

import com.rodyapal.gigachads.model.network.entity.NetworkServer
import io.ktor.client.HttpClient
import kotlinx.coroutines.delay

const val SERVER_API_URL = ""

class ServerApi(
	private val client: HttpClient,
) {
	suspend fun searchServer(query: String): List<NetworkServer> {
		return mock.filter { it.name.contains(query) or it.description.contains(query) }
	}

	suspend fun addToFavorite(serverId: Long, userId: Long) {
		//TODO
	}

	suspend fun removeFromFavorite(serverId: Long, userId: Long) {
		//TODO
	}

	suspend fun getServers(ids: List<Long>): List<NetworkServer> {
		//TODO
		delay(3000)
		return mock.filter { it.serverId in ids }
	}

	companion object {
		val mock =  listOf(
			NetworkServer(
				name = "Server 1",
				description = "A highly populated server with active community.",
				locale = "United States",
				isModded = true,
				hasAntiCheat = true,
				rating = 4.5f,
				gameId = 1234L,
				ownerId = 5678L,
				serverId = 9876L
			),
			NetworkServer(
				name = "Server 2",
				description = "Beginner-friendly server with helpful staff.",
				locale = "Canada",
				isModded = false,
				hasAntiCheat = true,
				rating = 4.0f,
				gameId = 1234L,
				ownerId = 5678L,
				serverId = 5432L
			),
			NetworkServer(
				name = "Server 3",
				description = "A competitive server for skilled players.",
				locale = "Germany",
				isModded = true,
				hasAntiCheat = true,
				rating = 4.8f,
				gameId = 5678L,
				ownerId = 9012L,
				serverId = 2468L
			),
			NetworkServer(
				name = "Server 4",
				description = "An international server with diverse player base.",
				locale = "United Kingdom",
				isModded = false,
				hasAntiCheat = true,
				rating = 4.2f,
				gameId = 5678L,
				ownerId = 9012L,
				serverId = 1357L
			),
			NetworkServer(
				name = "Server 5",
				description = "A survival server with custom plugins and events.",
				locale = "Australia",
				isModded = true,
				hasAntiCheat = false,
				rating = 4.6f,
				gameId = 9012L,
				ownerId = 3456L,
				serverId = 8642L
			),
			NetworkServer(
				name = "Server 6",
				description = "A role-playing server with rich lore and quests.",
				locale = "France",
				isModded = false,
				hasAntiCheat = true,
				rating = 4.4f,
				gameId = 9012L,
				ownerId = 3456L,
				serverId = 5791L
			),
			NetworkServer(
				name = "Server 7",
				description = "A creative server for building and showcasing creations.",
				locale = "Brazil",
				isModded = false,
				hasAntiCheat = true,
				rating = 4.1f,
				gameId = 3456L,
				ownerId = 7890L,
				serverId = 3648L
			),
			NetworkServer(
				name = "Server 8",
				description = "An esports server hosting competitive tournaments.",
				locale = "South Korea",
				isModded = false,
				hasAntiCheat = true,
				rating = 4.7f,
				gameId = 3456L,
				ownerId = 7890L,
				serverId = 1029L
			)
		)
	}
}