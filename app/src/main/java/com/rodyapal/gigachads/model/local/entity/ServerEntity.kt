package com.rodyapal.gigachads.model.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.rodyapal.gigachads.model.entity.Server
import com.rodyapal.gigachads.model.entity.ServerWithPosts

@Entity(tableName = "servers")
data class ServerEntity(
	val name: String,
	val description: String,
	val locale: String,
	val isModded: Boolean,
	val hasAntiCheat: Boolean,
	val rating: Float,
	val gameId: Long,
	val ownerId: Long,

	@PrimaryKey
	val serverId: Long
) {
	fun toDomainModel() = Server(
		name, description, locale, isModded, hasAntiCheat, rating, gameId, ownerId, serverId
	)
}

//data class ServerWithPostsEntity(
//	@Embedded val server: ServerEntity,
//	@Relation(
//		parentColumn = "serverId",
//		entityColumn = "serverId"
//	)
//	val posts: List<PostEntity>
//) {
//	fun toDomainModel() = ServerWithPosts(
//		server.toDomainModel(), posts.map { it.toDomainModel() }
//	)
//}