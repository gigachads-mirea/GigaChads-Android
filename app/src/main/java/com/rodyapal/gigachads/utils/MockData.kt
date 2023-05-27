package com.rodyapal.gigachads.utils

import com.rodyapal.gigachads.model.entity.Comment
import com.rodyapal.gigachads.model.entity.Post
import com.rodyapal.gigachads.model.entity.Server
import com.rodyapal.gigachads.screens.favservers.model.ServerBasicInfo
import com.rodyapal.gigachads.screens.server.model.ServerScreenState

@Deprecated("Dev only")
val MOCK_SERVER_BASIC_INFO = List(20) { index ->
	ServerBasicInfo(
		gameName = "Game name",
		serverName = "Server ${index + 1}",
		serverId = index.toLong()
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
		serverId = MOCK_SERVER.serverId,
		id = 1L
	),
	Post(
		title = "Second post",
		content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Dignissim suspendisse in est ante in. Sagittis nisl rhoncus mattis rhoncus urna neque viverra justo. Pellentesque elit eget gravida cum sociis natoque. Lectus proin nibh nisl condimentum id. Mattis pellentesque id nibh tortor id aliquet lectus. Molestie at elementum eu facilisis sed.",
		writtenAt = System.currentTimeMillis() + 1000,
		serverId = MOCK_SERVER.serverId,
		id = 2L
	),
	Post(
		title = "Third post",
		content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ",
		writtenAt = System.currentTimeMillis() + 10000,
		serverId = MOCK_SERVER.serverId,
		id = 3L
	),
	Post(
		title = "Fourth post",
		content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do",
		writtenAt = System.currentTimeMillis() + 100000,
		serverId = MOCK_SERVER.serverId,
		id = 4L
	),
	Post(
		title = "Firth post",
		content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Dignissim suspendisse in est ante in. Sagittis nisl rhoncus mattis rhoncus urna neque viverra justo. Pellentesque elit eget gravida cum sociis natoque. Lectus proin nibh nisl condimentum id.",
		writtenAt = System.currentTimeMillis() + 200000,
		serverId = MOCK_SERVER.serverId,
		id = 5L
	),
	Post(
		title = "Sixth post",
		content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Dignissim suspendisse in est ante in. Sagittis nisl rhoncus mattis rhoncus urna neque viverra justo. Pellentesque elit eget gravida cum sociis natoque. Lectus proin nibh nisl condimentum id. Mattis pellentesque id nibh tortor id aliquet lectus. Molestie at elementum eu facilisis sed.",
		writtenAt = System.currentTimeMillis() + 300000,
		serverId = MOCK_SERVER.serverId,
		id = 6L
	),
).sortedByDescending { it.writtenAt }

@Deprecated("Dev only")
const val MOCK_GAME_NAME = "My Awesome Game"

@Deprecated("Dev only")
val MOCK_SERVER_SCREEN_STATE_INFO = ServerScreenState.Info(
	server = MOCK_SERVER,
	latestPost = MOCK_POSTS[0],
	gameName = MOCK_GAME_NAME,
	true
)

@Deprecated("Dev only")
val MOCK_SERVER_SCREEN_STATE_POSTS = ServerScreenState.Posts(
	posts = MOCK_POSTS,
	serverName = "Generic name"
)

@Deprecated("Dev only")
val MOCK_COMMENTS = listOf(
	Comment("John", "Great post!", System.currentTimeMillis(), 1L, 100L),
	Comment("Jane", "I disagree with your opinion.", 1609600000000L, 2L, 100L),
	Comment("Mike", "Thanks for sharing.", 1622505600000L, 3L, 101L),
	Comment("Sarah", "This is so helpful, thanks!", 1630454400000L, 4L, 102L),
	Comment("Tom", "Interesting, I'd like to know more about this topic.", 1633084800000L, 5L, 102L),
	Comment("Emily", "I'm confused, can you please explain it better?", 1640995200000L, 6L, 103L)
)