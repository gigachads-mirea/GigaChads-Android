package com.rodyapal.gigachads.model.repository

import com.rodyapal.gigachads.model.dao.PostDao
import com.rodyapal.gigachads.model.entity.Post
import com.rodyapal.gigachads.model.network.api.PostApi
import kotlinx.coroutines.delay

class PostRepository(
	private val postDao: PostDao,
	private val postApi: PostApi,
	private val serverRepository: ServerRepository,
	private val userRepository: UserRepository
) {
	suspend fun getPostById(postId: Long): Post {
		delay(2000)
		return postDao.getById(postId)
	}

	suspend fun getPostsForServer(serverId: Long): List<Post> {
		return postDao.getByServerId(serverId).ifEmpty {
			postApi.getByServerId(serverId)
		}.also {
			updateData()
		}
	}

	suspend fun isLikedByCurrentUser(postId: Long): Boolean {
		return postDao.isLikedByCurrentUser(postId)
	}

	private suspend fun updateData() {
		//TODO: implement
	}

	suspend fun getPostsForCurrentUser(): List<Post> {
		return userRepository.getFavoriteServers().let {
			serverRepository.getServers(it)
		}.flatMap { getPostsForServer(it.serverId) }
	}

	suspend fun setLike(postId: Long, like: Boolean): Post? {
		//TODO: implement
		return null
	}
}