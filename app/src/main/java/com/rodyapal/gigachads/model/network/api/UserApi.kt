package com.rodyapal.gigachads.model.network.api

import com.rodyapal.gigachads.model.entity.User
import com.rodyapal.gigachads.model.network.ktorClient
import io.ktor.client.HttpClient

class UserApi(
	client: HttpClient,
	baseURL: String
) : BaseApi<User>(client, baseURL) {
	suspend fun auth(login: String, password: String): User? {
		//TODO: implement
		return null
	}
	suspend fun register(login: String, password: String, username: String): User? {
		//TODO: implement
		ktorClient
		return null
	}
}