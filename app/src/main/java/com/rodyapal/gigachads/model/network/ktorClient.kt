package com.rodyapal.gigachads.model.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json

private const val TIME_OUT = 60_000

val ktorClient = HttpClient(Android) {
	install(ContentNegotiation) {
		json()
	}
	engine {
		connectTimeout = TIME_OUT
	}
	install(DefaultRequest) {
		header(HttpHeaders.ContentType, ContentType.Application.Json)
	}
}