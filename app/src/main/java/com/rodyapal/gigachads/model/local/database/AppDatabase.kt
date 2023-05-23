package com.rodyapal.gigachads.model.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rodyapal.gigachads.model.local.dao.CommentDao
import com.rodyapal.gigachads.model.local.dao.GameDao
import com.rodyapal.gigachads.model.local.dao.PostDao
import com.rodyapal.gigachads.model.local.dao.ServerDao
import com.rodyapal.gigachads.model.local.dao.UserDao
import com.rodyapal.gigachads.model.local.entity.CommentEntity
import com.rodyapal.gigachads.model.local.entity.GameEntity
import com.rodyapal.gigachads.model.local.entity.LikedCommentEntity
import com.rodyapal.gigachads.model.local.entity.LikedPostEntity
import com.rodyapal.gigachads.model.local.entity.PostEntity
import com.rodyapal.gigachads.model.local.entity.SearchedServerEntity
import com.rodyapal.gigachads.model.local.entity.ServerEntity
import com.rodyapal.gigachads.model.local.entity.FavoriteServerEntity
import com.rodyapal.gigachads.model.local.entity.UserEntity

@Database(
	entities = [
		CommentEntity::class,
		GameEntity::class,
		PostEntity::class,
		ServerEntity::class,
		FavoriteServerEntity::class,
		UserEntity::class,
		LikedCommentEntity::class,
		LikedPostEntity::class,
		SearchedServerEntity::class
	],
	version = 1
)
abstract class AppDatabase : RoomDatabase() {
	abstract fun commentDao(): CommentDao
	abstract fun gameDao(): GameDao
	abstract fun postDao(): PostDao
	abstract fun serverDao(): ServerDao
	abstract fun userDao(): UserDao
}