package com.rodyapal.gigachads.model.repository

import com.rodyapal.gigachads.model.entity.Post
import com.rodyapal.gigachads.model.local.dao.PostDao
import com.rodyapal.gigachads.model.network.api.PostApi
import com.rodyapal.gigachads.utils.instantCombine
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEmpty

class PostRepository(
	private val dao: PostDao,
	private val api: PostApi,
) {
	suspend fun getPostById(postId: Long): Flow<Post> {
		return dao
			.getById(postId)
			.onEmpty {
				refreshPost(postId)
			}.map {
				it.toDomainModel()
			}
	}

	private suspend fun refreshPost(postId: Long) {
		api.get(postId).let { post ->
			dao.save(
				post.toDomainModel().toPostEntity()
			)
		}
	}

	suspend fun getPostsForServer(serverId: Long): Flow<List<Post>> {
		return dao.getByServerId(serverId).onEmpty {
			refreshPostsForServer(serverId)
		}.map { posts ->
			if (posts.isEmpty()) {
				refreshPostsForServer(serverId)
			}
			posts.map { it.toDomainModel() }
		}
	}

	suspend fun getPostsForServers(ids: List<Long>): Flow<List<List<Post>?>> =
		if (ids.isEmpty()) emptyFlow() else instantCombine(
			ids.map {
				getPostsForServer(it)
			}
		)

	private suspend fun refreshPostsForServer(serverId: Long) {
		api.getByServerId(serverId).let { posts ->
			dao.save(posts.map { it.toDomainModel().toPostEntity() })
		}
	}
}