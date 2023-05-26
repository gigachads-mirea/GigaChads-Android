package com.rodyapal.gigachads.model.network.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BasicAuthCredentials
import io.ktor.client.plugins.auth.providers.basic
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.serialization.Serializable

const val USER_API_URL = "http://mirea-gigachads/users"
class UserApi(
	private val client: HttpClient,
	private val apiPath: String
) {
	suspend fun auth(email: String, password: String): Pair<String, Long>? = client.config {
		Auth {
			basic {
				credentials {
					BasicAuthCredentials(
						username = email,
						password = password
					)
				}
				sendWithoutRequest {
					it.url.host == USER_API_URL
				}
			}
		}
	}.let {
		client.get(apiPath) {
			url {
				parameters.append("email", email)
			}
		}.body()
	}
	suspend fun register(email: String, password: String, username: String): Long? = client.post("$USER_API_URL/new") {
		setBody(UserRegisterDto(email, password, username))
	}.body() //safe as fuck

	suspend fun getFavoriteServerIds(userId: Long): List<Long> = client.get("$apiPath/favorite") {
		url {
			parameters.append("userId", userId.toString())
		}
	}.body()

	@Serializable
	private data class UserRegisterDto(
		val email: String,
		val password: String,
		val username: String
	)
}