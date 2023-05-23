package com.rodyapal.gigachads.model.network.api

import com.rodyapal.gigachads.model.network.entity.NetworkComment
import com.rodyapal.gigachads.model.network.entity.NetworkUserComment
import io.ktor.client.HttpClient
import kotlinx.coroutines.delay
import kotlin.random.Random

const val COMMENT_API_URL = "" //TODO
class CommentApi(
	private val client: HttpClient,
) {
	suspend fun postComment(comment: NetworkUserComment): Long {
		val id = Random.nextLong()
		mock.add(
			NetworkComment(
				id = id,
				authorName = comment.authorName,
				text = comment.text,
				writtenAt = comment.writtenAt,
				parentPostId = comment.parentPostId
			)
		)
		return id
	}

	suspend fun getByPostId(postId: Long): List<NetworkComment> {
		delay(1000)
		return mock.filter { postId == it.parentPostId }

	}

	private val mock = mutableListOf(
		NetworkComment(
			authorName = "John",
			text = "Great post!",
			writtenAt = System.currentTimeMillis(),
			id = 1,
			parentPostId = 123
		),
		NetworkComment(
			authorName = "Emily",
			text = "I completely agree with you.",
			writtenAt = System.currentTimeMillis(),
			id = 2,
			parentPostId = 123
		),
		NetworkComment(
			authorName = "David",
			text = "Interesting perspective.",
			writtenAt = System.currentTimeMillis(),
			id = 3,
			parentPostId = 123
		),
		NetworkComment(
			authorName = "Sarah",
			text = "Well written!",
			writtenAt = System.currentTimeMillis(),
			id = 4,
			parentPostId = 123
		),
		NetworkComment(
			authorName = "Michael",
			text = "Thanks for sharing this information.",
			writtenAt = System.currentTimeMillis(),
			id = 5,
			parentPostId = 123
		),
		NetworkComment(
			authorName = "Sophia",
			text = "I have a question...",
			writtenAt = System.currentTimeMillis(),
			id = 6,
			parentPostId = 123
		),
		NetworkComment(
			authorName = "Daniel",
			text = "Keep up the good work!",
			writtenAt = System.currentTimeMillis(),
			id = 7,
			parentPostId = 123
		),
		NetworkComment(
			authorName = "Olivia",
			text = "I found this very helpful.",
			writtenAt = System.currentTimeMillis(),
			id = 8,
			parentPostId = 123
		),
		NetworkComment(
			authorName = "Aiden",
			text = "Nice post! Thanks for sharing.",
			writtenAt = System.currentTimeMillis(),
			id = 9,
			parentPostId = 123
		),
		NetworkComment(
			authorName = "Emma",
			text = "I learned something new from this.",
			writtenAt = System.currentTimeMillis(),
			id = 10,
			parentPostId = 123
		),
		NetworkComment(
			authorName = "John",
			text = "Great post!",
			writtenAt = System.currentTimeMillis(),
			id = 1,
			parentPostId = 456
		),
		NetworkComment(
			authorName = "Emily",
			text = "I completely agree with you.",
			writtenAt = System.currentTimeMillis(),
			id = 2,
			parentPostId = 456
		),
		NetworkComment(
			authorName = "David",
			text = "Interesting perspective.",
			writtenAt = System.currentTimeMillis(),
			id = 3,
			parentPostId = 456
		),
		NetworkComment(
			authorName = "Sarah",
			text = "Well written!",
			writtenAt = System.currentTimeMillis(),
			id = 4,
			parentPostId = 456
		),
		NetworkComment(
			authorName = "Michael",
			text = "Thanks for sharing this information.",
			writtenAt = System.currentTimeMillis(),
			id = 5,
			parentPostId = 456
		),
		NetworkComment(
			authorName = "Sophia",
			text = "I have a question...",
			writtenAt = System.currentTimeMillis(),
			id = 6,
			parentPostId = 456
		),
		NetworkComment(
			authorName = "Daniel",
			text = "Keep up the good work!",
			writtenAt = System.currentTimeMillis(),
			id = 7,
			parentPostId = 456
		),
		NetworkComment(
			authorName = "Olivia",
			text = "I found this very helpful.",
			writtenAt = System.currentTimeMillis(),
			id = 8,
			parentPostId = 456
		),
		NetworkComment(
			authorName = "Aiden",
			text = "Nice post! Thanks for sharing.",
			writtenAt = System.currentTimeMillis(),
			id = 9,
			parentPostId = 456
		),
		NetworkComment(
			authorName = "Emma",
			text = "I learned something new from this.",
			writtenAt = System.currentTimeMillis(),
			id = 10,
			parentPostId = 456
		)
	)
}

