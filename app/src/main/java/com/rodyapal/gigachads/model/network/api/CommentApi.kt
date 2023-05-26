package com.rodyapal.gigachads.model.network.api

import com.rodyapal.gigachads.model.network.entity.NetworkComment
import com.rodyapal.gigachads.model.network.entity.NetworkUserComment
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody

const val COMMENT_API_URL = "http://mirea-gigachads/api/v1/comments"
class CommentApi(
	private val client: HttpClient,
	private val apiPath: String
) {
	suspend fun postComment(comment: NetworkUserComment): Long {
		val response = client.post(apiPath) {
			setBody(comment)
		}
		return response.body()
	}

	suspend fun getByPostId(postId: Long): List<NetworkComment> {
		return client.get("$apiPath/byPost") {
			url {
				parameters.append("postId", postId.toString())
			}
		}.body()
	}
}

