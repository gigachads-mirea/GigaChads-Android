package com.rodyapal.gigachads.model.network.api

import com.rodyapal.gigachads.model.entity.Comment
import io.ktor.client.HttpClient
import kotlinx.coroutines.delay

class CommentApi(
	private val client: HttpClient,
	private val baseURL: String
) : BaseApi<Comment>(client, baseURL) {
	suspend fun getByPostId(postId: Long): List<Comment> {
		delay(1000)
		return listOf(
			Comment(
				authorName = "John",
				text = "Great post!",
				writtenAt = System.currentTimeMillis(),
				likes = 5,
				id = 1,
				parentPostId = 1
			),
			Comment(
				authorName = "Emily",
				text = "I completely agree with you.",
				writtenAt = System.currentTimeMillis(),
				likes = 3,
				id = 2,
				parentPostId = 1
			),
			Comment(
				authorName = "David",
				text = "Interesting perspective.",
				writtenAt = System.currentTimeMillis(),
				likes = 2,
				id = 3,
				parentPostId = 1
			),
			Comment(
				authorName = "Sarah",
				text = "Well written!",
				writtenAt = System.currentTimeMillis(),
				likes = 4,
				id = 4,
				parentPostId = 2
			),
			Comment(
				authorName = "Michael",
				text = "Thanks for sharing this information.",
				writtenAt = System.currentTimeMillis(),
				likes = 6,
				id = 5,
				parentPostId = 2
			),
			Comment(
				authorName = "Sophia",
				text = "I have a question...",
				writtenAt = System.currentTimeMillis(),
				likes = 1,
				id = 6,
				parentPostId = 3
			),
			Comment(
				authorName = "Daniel",
				text = "Keep up the good work!",
				writtenAt = System.currentTimeMillis(),
				likes = 7,
				id = 7,
				parentPostId = 4
			),
			Comment(
				authorName = "Olivia",
				text = "I found this very helpful.",
				writtenAt = System.currentTimeMillis(),
				likes = 9,
				id = 8,
				parentPostId = 4
			),
			Comment(
				authorName = "Aiden",
				text = "Nice post! Thanks for sharing.",
				writtenAt = System.currentTimeMillis(),
				likes = 8,
				id = 9,
				parentPostId = 5
			),
			Comment(
				authorName = "Emma",
				text = "I learned something new from this.",
				writtenAt = System.currentTimeMillis(),
				likes = 3,
				id = 10,
				parentPostId = 5
			)
		)

	}
}

