package com.rodyapal.gigachads.model.repository

import com.rodyapal.gigachads.model.dao.CommentDao
import com.rodyapal.gigachads.model.entity.Comment
import com.rodyapal.gigachads.model.entity.LikedComment
import com.rodyapal.gigachads.model.entity.User
import com.rodyapal.gigachads.model.network.api.CommentApi

class CommentRepository(
	private val dao: CommentDao,
	private val api: CommentApi
) {
	suspend fun getCommentsForPost(postId: Long): List<Comment> {
		//TODO: implement
		return dao.getByPostId(postId).ifEmpty { api.getByPostId(postId) }
	}

	suspend fun getCommentsForPostWithLikeStatus(postId: Long): List<Pair<Comment, Boolean>> {
		return getCommentsForPost(postId).map { it to isCommentLikedByUser(it.id) }
	}

	suspend fun isCommentLikedByUser(commentId: Long): Boolean = dao.isLikedByCurrentUser(commentId)

	suspend fun setLikeStatus(commentId: Long): Boolean {
		return if (isCommentLikedByUser(commentId)) {
			dao.setCommentLiked(LikedComment(commentId))
			false
		} else {
			dao.setCommentLiked(LikedComment(commentId))
			true
		}
		//TODO: Network
	}

	suspend fun addUserComment(text: String, postId: Long, author: User) {
		val comment = Comment(
			authorName = author.username,
			text = text,
			writtenAt = System.currentTimeMillis(),
			likes = 0,
			parentPostId = postId,
			id = 0,
		)
		api.post(comment) //TODO: return value
		dao.save(comment)
	}
}