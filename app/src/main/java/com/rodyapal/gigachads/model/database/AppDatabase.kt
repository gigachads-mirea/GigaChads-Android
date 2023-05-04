package com.rodyapal.gigachads.model.database

import androidx.room.Database
import com.rodyapal.gigachads.model.dao.CommentDao
import com.rodyapal.gigachads.model.dao.GameDao
import com.rodyapal.gigachads.model.dao.PostDao
import com.rodyapal.gigachads.model.dao.ServerDao
import com.rodyapal.gigachads.model.dao.UserDao
import com.rodyapal.gigachads.model.entity.Comment
import com.rodyapal.gigachads.model.entity.Game
import com.rodyapal.gigachads.model.entity.Post
import com.rodyapal.gigachads.model.entity.Server
import com.rodyapal.gigachads.model.entity.ServerUserCrossRef
import com.rodyapal.gigachads.model.entity.User

@Database(
	entities = [Comment::class, Game::class, Post::class, Server::class, ServerUserCrossRef::class, User::class],
	version = 1
)
abstract class AppDatabase {
	abstract fun commentDao(): CommentDao
	abstract fun gameDao(): GameDao
	abstract fun postDao(): PostDao
	abstract fun serverDao(): ServerDao
	abstract fun userDao(): UserDao
}