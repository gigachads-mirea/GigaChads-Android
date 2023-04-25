package com.rodyapal.gigachads.utils

import com.rodyapal.gigachads.model.entity.Post
import com.rodyapal.gigachads.model.entity.Server
import com.rodyapal.gigachads.model.entity.ServerBasicInfo
import com.rodyapal.gigachads.screens.server.model.ServerScreenState

@Deprecated("Dev only")
val MOCK_SERVER_BASIC_INFO = List(20) { index ->
	ServerBasicInfo(
		name = "Server ${index + 1}",
		description = "This is a description for server ${index + 1}"
	)
}

@Deprecated("Dev only")
val MOCK_SERVER = Server(
	name = "My Server",
	description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Dignissim suspendisse in est ante in. Sagittis nisl rhoncus mattis rhoncus urna neque viverra justo.",
	locale = "en_US",
	isModded = true,
	hasAntiCheat = false,
	rating = 4.5f,
	gameId = 12345L,
	ownerId = 6789L,
	serverId = 987654321L
)

@Deprecated("Dev only")
val MOCK_POSTS = listOf(
	Post(
		title = "First post",
		content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Dignissim suspendisse in est ante in. Sagittis nisl rhoncus mattis rhoncus urna neque viverra justo. Pellentesque elit eget gravida cum sociis natoque. Lectus proin nibh nisl condimentum id. Mattis pellentesque id nibh tortor id aliquet lectus. Molestie at elementum eu facilisis sed.",
		writtenAt = System.currentTimeMillis(),
		likes = 10,
		serverId = MOCK_SERVER.serverId,
		id = 1L
	),
	Post(
		title = "Second post",
		content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Dignissim suspendisse in est ante in. Sagittis nisl rhoncus mattis rhoncus urna neque viverra justo. Pellentesque elit eget gravida cum sociis natoque. Lectus proin nibh nisl condimentum id. Mattis pellentesque id nibh tortor id aliquet lectus. Molestie at elementum eu facilisis sed.",
		writtenAt = System.currentTimeMillis() + 1000,
		likes = 5,
		serverId = MOCK_SERVER.serverId,
		id = 2L
	)
)

@Deprecated("Dev only")
const val MOCK_GAME_NAME = "My Awesome Game"

@Deprecated("Dev only")
val MOCK_SERVER_SCREEN_STATE = ServerScreenState(
	server = MOCK_SERVER,
	posts = MOCK_POSTS,
	gameName = MOCK_GAME_NAME
)
