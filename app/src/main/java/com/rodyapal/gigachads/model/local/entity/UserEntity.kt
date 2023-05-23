package com.rodyapal.gigachads.model.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rodyapal.gigachads.model.entity.User
@Entity(tableName = "users")
data class UserEntity(
	val username: String,
	val email: String,
	val password: String,
	val isClient: Boolean,
	val userId: Long,
	@PrimaryKey
	val _localId: Int = 0
) {
	fun toDomainModel() = User(
		username, email, password, isClient, userId
	)
}