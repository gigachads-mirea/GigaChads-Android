package com.rodyapal.gigachads.model.network.entity

import com.rodyapal.gigachads.model.entity.User
import kotlinx.serialization.Serializable

@Serializable
data class NetworkUser(
	val username: String,
	val email: String,
	val password: String,
	val isClient: Boolean,
	val userId: Long,
) {
	fun toDomainModel() = User(
		username, email, password, isClient, userId
	)
}