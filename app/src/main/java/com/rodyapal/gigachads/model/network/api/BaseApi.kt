package com.rodyapal.gigachads.model.network.api

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType

abstract class BaseApi <E : Any> (
	private val client: HttpClient,
	private val baseURL: String
) {
	suspend fun get(
		id: Long
	): HttpResponse = client.get("$baseURL$id")

	suspend fun getAll(): HttpResponse = client.get(baseURL)

	suspend fun post(
		entity: E
	) = client.post(baseURL) {
		contentType(ContentType.Application.Json)
		setBody(entity as Any)
	}
}