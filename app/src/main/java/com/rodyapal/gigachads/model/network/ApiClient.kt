package com.rodyapal.gigachads.model.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.compression.ContentEncoding
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json

class ApiClient(clientEngine: HttpClientEngine) {
	private val _client = HttpClient(clientEngine) {
		install(ContentNegotiation) {
			json()
		}
		install(DefaultRequest) {
			header(HttpHeaders.ContentType, ContentType.Application.Json)
		}
		ContentEncoding {
			deflate(1.0F)
			gzip(0.95F)
		}
	}
	val client get() = _client
}