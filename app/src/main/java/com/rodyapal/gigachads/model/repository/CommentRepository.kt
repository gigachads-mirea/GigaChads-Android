package com.rodyapal.gigachads.model.repository

import com.rodyapal.gigachads.model.entity.Comment
import com.rodyapal.gigachads.model.entity.User
import com.rodyapal.gigachads.model.local.dao.CommentDao
import com.rodyapal.gigachads.model.local.entity.CommentEntity
import com.rodyapal.gigachads.model.network.api.CommentApi
import com.rodyapal.gigachads.model.network.entity.NetworkUserComment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class CommentRepository(
	private val dao: CommentDao,
	private val api: CommentApi
) {
	suspend fun getCommentsForPost(postId: Long): Flow<List<Comment>> {
		return dao
			.getByPostId(postId)
			.map { cache ->
				cache.map { it.toDomainModel() }
			}
			.onEach { comments ->
				if (comments.isEmpty()) { //TODO: check it works
					refreshComments(postId)
				}
			}
	}

	private suspend fun refreshComments(postId: Long) {
		api.getByPostId(postId).map {
			it.toDomainModel().toEntityModel()
		}.also {
			dao.save(it)
		}
	}

	suspend fun addUserComment(text: String, postId: Long, author: User) {
		val comment = Comment(
			authorName = author.username,
			text = text,
			writtenAt = System.currentTimeMillis(),
			parentPostId = postId,
			id = -1, //indicate that it should be processed
		)
		api.postComment(NetworkUserComment.from(comment)).let {
			dao.save(
				CommentEntity(
					comment.authorName,
					comment.text,
					comment.writtenAt,
					comment.parentPostId,
					id = it
				)
			)
		}
	}
}