package com.rodyapal.gigachads.model.entity

import com.rodyapal.gigachads.model.local.entity.UserEntity

data class User(
	val username: String,
	val email: String,
	val password: String,
	val isClient: Boolean,
	val userId: Long,
) {
	fun toUserEntity() = UserEntity(
		username, email, password, isClient, userId
	)
}