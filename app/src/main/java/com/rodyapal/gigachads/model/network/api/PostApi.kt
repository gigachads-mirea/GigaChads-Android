package com.rodyapal.gigachads.model.network.api

import com.rodyapal.gigachads.model.network.entity.NetworkPost
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

const val POST_API_URL = "http://mirea-gigachads/api/v1/posts"

class PostApi(
	private val client: HttpClient,
	private val apiPath: String
) {
	suspend fun get(postId: Long): NetworkPost = client.get(apiPath) {
		url {
			parameters.append("postId", postId.toString())
		}
	}.body()

	suspend fun getByServerId(serverId: Long): List<NetworkPost> = client.get("$apiPath/byServer") {
		url {
			parameters.append("serverId", serverId.toString())
		}
	}.body()
}
