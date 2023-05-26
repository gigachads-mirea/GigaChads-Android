package com.rodyapal.gigachads.model.network.api

import com.rodyapal.gigachads.model.network.entity.NetworkServer
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody

const val SERVER_API_URL = "http://mirea-gigachads/api/v1/servers"

class ServerApi(
	private val client: HttpClient,
	private val apiPath: String
) {
	suspend fun searchServer(query: String): List<NetworkServer> = client.get("$apiPath/search") {
		setBody(query)
	}.body()

	suspend fun addToFavorite(serverId: Long, userId: Long) = client.post("$apiPath/favorite") {
		url {
			parameters.append("userId", userId.toString())
			parameters.append("serverId", serverId.toString())
		}
	}

	suspend fun removeFromFavorite(serverId: Long, userId: Long) = client.patch("$apiPath/favorite") {
		url {
			parameters.append("userId", userId.toString())
			parameters.append("serverId", serverId.toString())
		}
	}

	suspend fun getServers(ids: List<Long>): List<NetworkServer> = client.get(apiPath) {
		setBody(ids)
	}.body()
}