package com.rodyapal.gigachads.model.network.api

import com.rodyapal.gigachads.model.network.entity.NetworkUser
import io.ktor.client.HttpClient

const val USER_API_URL = ""
class UserApi(
	private val client: HttpClient,
) {
	suspend fun auth(email: String, password: String): NetworkUser? {
		//TODO: implement
		return null
	}
	suspend fun register(email: String, password: String, username: String): NetworkUser? {
		//TODO: implement
		return null
	}

	suspend fun getFavoriteServerIds(userId: Long): List<Long> {
		//TODO: implement
		return listOf(9876L)
	}
}