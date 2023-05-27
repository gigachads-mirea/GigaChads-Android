package com.rodyapal.gigachads.model.network

import com.rodyapal.gigachads.model.network.entity.NetworkComment
import com.rodyapal.gigachads.model.network.entity.NetworkGame
import com.rodyapal.gigachads.model.network.entity.NetworkPost
import com.rodyapal.gigachads.model.network.entity.NetworkServer
import com.rodyapal.gigachads.model.network.entity.NetworkUser
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockRequestHandleScope
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.HttpResponseData
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.fullPath
import io.ktor.http.headersOf
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ApiMockEngine {
	fun get() = client.engine
	private val client = HttpClient(MockEngine) {
		engine {
			addHandler { request ->
				if (request.url.encodedPath.contains("/comment")) {
					respondComments(request)
				} else if (request.url.encodedPath.contains( "/games")) {
					respondGames()
				} else if (request.url.encodedPath.contains("/posts")) {
					respondPosts(request)
				} else if (request.url.encodedPath.contains("/servers")) {
					respondServers(request)
				} else if (request.url.encodedPath.contains("/users")) {
					respondUsers(request)
				} else {
					error("Unhandled ${request.url.encodedPath}")
				}
			}
		}
	}
}

fun MockRequestHandleScope.respondComments(request: HttpRequestData): HttpResponseData {
	return if (request.method == HttpMethod.Post) { //post comment
		val id = COMMENTS.maxBy { it.id }.id + 1
		respond(
			content = Json.encodeToString(id) ,
			status = HttpStatusCode.Created,
			headers = headersOf(HttpHeaders.ContentType, "application/json")
		)
	} else { //get by post id
		val postId = request.url.fullPath.filter { it.isDigit() }.toLong()
		respond(
			content = Json.encodeToString(
				COMMENTS.filter { it.parentPostId == postId }
			),
			status = HttpStatusCode.Created,
			headers = headersOf(HttpHeaders.ContentType, "application/json")
		)
	}
}

fun MockRequestHandleScope.respondGames(): HttpResponseData {
	return respond(
		content = Json.encodeToString(GAMES),
		status = HttpStatusCode.OK,
		headers = headersOf(HttpHeaders.ContentType, "application/json")
	)
}

fun MockRequestHandleScope.respondPosts(request: HttpRequestData): HttpResponseData {
	val id = request.url.fullPath.filter { it.isDigit() }.toLong()
	return if (request.url.fullPath.contains("byServer")) { //get by server id
		respond(
			content = Json.encodeToString(
				POSTS.filter { it.serverId == id }
			),
			status = HttpStatusCode.Created,
			headers = headersOf(HttpHeaders.ContentType, "application/json")
		)
	} else { // get by id
		respond(
			content = Json.encodeToString(
				POSTS.first { it.id == id }
			),
			status = HttpStatusCode.Created,
			headers = headersOf(HttpHeaders.ContentType, "application/json")
		)
	}
}

fun MockRequestHandleScope.respondServers(request: HttpRequestData): HttpResponseData {
	return when (request.method) {
		HttpMethod.Post -> respond( //add to favorite
			content = "Ok",
			status = HttpStatusCode.Created
		)
		HttpMethod.Patch -> respond( //remove from favorite
			content = "Ok",
			status = HttpStatusCode.OK
		)
		HttpMethod.Get -> {
			if (request.url.fullPath.contains("search")) { //search
				val query = request.url.encodedQuery.substringAfter('=').lowercase()
				val result = if (query.isNotBlank()) {
					SERVERS.filter {
						it.name.lowercase().contains(query) or query.contains(it.name.lowercase()) or it.description.lowercase().contains(query)
					}.ifEmpty { SERVERS }
				} else {
					SERVERS
				}
				respond(
					content = Json.encodeToString(result),
					status = HttpStatusCode.OK,
					headers = headersOf(HttpHeaders.ContentType, "application/json")
				)
			} else { // get servers
				respond(
					content = Json.encodeToString(
						emptyList<NetworkServer>()
					),
					status = HttpStatusCode.OK,
					headers = headersOf(HttpHeaders.ContentType, "application/json")
				)
			}
		}
		else -> {
			error("Invalid method")
		}
	}
}

fun MockRequestHandleScope.respondUsers(request: HttpRequestData): HttpResponseData {
	return when (request.method) {
		HttpMethod.Post -> {
			respond(
				content = Json.encodeToString(
					USERS.maxBy { it.userId }.userId + 1
				),
				status = HttpStatusCode.Created,
				headers = headersOf(HttpHeaders.ContentType, "application/json")
			)
		}
		HttpMethod.Get -> {
			if (request.url.fullPath.contains("favorite")) { //favorite servers
				respond(
					content = Json.encodeToString(
						emptyList<Long>()
					),
					status = HttpStatusCode.OK,
					headers = headersOf(HttpHeaders.ContentType, "application/json")
				)
			} else { //auth
				respond(
					content = Json.encodeToString(
						"username" to (USERS.maxBy { it.userId }.userId + 1)
					),
					status = HttpStatusCode.OK,
					headers = headersOf(HttpHeaders.ContentType, "application/json")
				)
			}
		}
		else -> {
			error("Invalid method")
		}
	}
}

private val GAMES = listOf(
	NetworkGame("World of Warcraft", 1),
	NetworkGame("Minecraft", 2),
	NetworkGame("Grand Theft Auto V", 3),
	NetworkGame("New World", 4),
	NetworkGame("Final Fantasy 14", 5),
)

private val SERVERS = listOf(
	// Servers for "World of Warcraft"
	NetworkServer(
		"Azeroth Realm",
		"Experience the epic battles of Azeroth in this immersive server.",
		"EN",
		isModded = false,
		hasAntiCheat = true,
		rating = 4.5f,
		gameId = 1,
		ownerId = 1001,
		serverId = 2001
	),
	NetworkServer(
		"Stormwind Sanctuary",
		"Seek refuge in this peaceful server located in the heart of Stormwind.",
		"EN",
		isModded = true,
		hasAntiCheat = true,
		rating = 4.2f,
		gameId = 1,
		ownerId = 1002,
		serverId = 2002
	),

	// Servers for "Minecraft"
	NetworkServer(
		"Craftopia",
		"Discover a world of endless possibilities in this modded Minecraft server.",
		"GER",
		isModded = true,
		hasAntiCheat = true,
		rating = 4.7f,
		gameId = 2,
		ownerId = 1003,
		serverId = 2003
	),
	NetworkServer(
		"VanillaVerse",
		"Experience the pure essence of Minecraft in this unmodded server.",
		"EN",
		isModded = false,
		hasAntiCheat = false,
		rating = 3.9f,
		gameId = 2,
		ownerId = 1004,
		serverId = 2004
	),
	NetworkServer(
		"PixelCraft",
		"Immerse yourself in the nostalgic pixel art world of this Minecraft server.",
		"EN",
		isModded = true,
		hasAntiCheat = true,
		rating = 4.3f,
		gameId = 2,
		ownerId = 1005,
		serverId = 2005
	),

	// Servers for "Grand Theft Auto V"
	NetworkServer(
		"Los Santos City",
		"Engage in criminal activities and explore the vibrant city of Los Santos.",
		"United States",
		isModded = true,
		hasAntiCheat = true,
		rating = 4.8f,
		gameId = 3,
		ownerId = 1006,
		serverId = 2006
	),
	NetworkServer(
		"Street Racerz",
		"Challenge your racing skills on the streets of Los Santos.",
		"EN",
		isModded = false,
		hasAntiCheat = true,
		rating = 4.1f,
		gameId = 3,
		ownerId = 1007,
		serverId = 2007
	),

	// Servers for "New World"
	NetworkServer(
		"Aeternum Realm",
		"Conquer the mystical lands of Aeternum on this server.",
		"FR",
		isModded = true,
		hasAntiCheat = true,
		rating = 4.6f,
		gameId = 4,
		ownerId = 1008,
		serverId = 2008
	),
	NetworkServer(
		"Trading Post",
		"Engage in commerce and build your trading empire in this server.",
		"EN",
		isModded = false,
		hasAntiCheat = false,
		rating = 3.8f,
		gameId = 4,
		ownerId = 1009,
		serverId = 2009
	),
	NetworkServer(
		"Windsward Warriors",
		"Join forces with fellow warriors and battle for control in Windsward.",
		"EN",
		isModded = true,
		hasAntiCheat = true,
		rating = 4.4f,
		gameId = 4,
		ownerId = 1010,
		serverId = 2010
	),

	// Servers for "Final Fantasy 14"
	NetworkServer(
		"Eorzea Heroes",
		"Embark on a heroic journey through the realm of Eorzea.",
		"EN",
		isModded = true,
		hasAntiCheat = true,
		rating = 4.7f,
		gameId = 5,
		ownerId = 1011,
		serverId = 2011
	),
	NetworkServer(
		"Crystal Citadel",
		"Experience the grandeur and elegance of this Crystal-themed server.",
		"EN",
		isModded = false,
		hasAntiCheat = true,
		rating = 4.2f,
		gameId = 5,
		ownerId = 1012,
		serverId = 2012
	)
)

private val POSTS = listOf(
	// Posts for "Azeroth Realm"
	NetworkPost(
		"New Raid Announcement",
		"Attention adventurers! We are excited to announce a new raid coming to Azeroth Realm. Get ready to face powerful bosses and claim epic rewards. Mark your calendars and gather your allies for this challenging encounter!",
		System.currentTimeMillis() - 86400000, // 1 day ago
		2001,
		3001
	),
	NetworkPost(
		"Recruiting for PvP Team",
		"Are you a skilled PvP player looking for a dedicated team? Look no further! We are recruiting experienced players for our PvP squad in Azeroth Realm. Join us and dominate the battlegrounds!",
		System.currentTimeMillis() - 172800000, // 2 days ago
		2001,
		3002
	),

	// Posts for "Stormwind Sanctuary"
	NetworkPost(
		"Guild Recruitment",
		"Looking for a friendly and active guild? Join us at Stormwind Sanctuary! We offer a supportive community, weekly events, and help with all aspects of the game. All classes and levels are welcome!",
		System.currentTimeMillis() - 259200000, // 3 days ago
		2002,
		3003
	),

	// Posts for "Craftopia"
	NetworkPost(
		"Mod Showcase: Magic Expansion",
		"Calling all wizards and sorcerers! We have a new mod called Magic Expansion in Craftopia. This mod introduces new spells, magical creatures, and enchantments. Dive into the world of magic and experience the power at your fingertips!",
		System.currentTimeMillis() - 432000000, // 5 days ago
		2003,
		3004
	),
	NetworkPost(
		"Community Build Contest",
		"Attention builders! We are hosting a community build contest in Craftopia. Show off your creative skills by constructing impressive structures and landscapes. Prizes await the winners! Don't miss this chance to shine!",
		System.currentTimeMillis() - 604800000, // 7 days ago
		2003,
		3005
	),

	// Posts for "VanillaVerse"
	NetworkPost(
		"Minecraft Survival Tips",
		"New to VanillaVerse? Here are some survival tips to get you started: always carry a bed, mine strategically, and beware of creepers at night. Survive and thrive in the purest form of Minecraft!",
		System.currentTimeMillis() - 259200000, // 3 days ago
		2004,
		3006
	),
	NetworkPost(
		"Building Competition",
		"Calling all architects! We are hosting a building competition in VanillaVerse. Show off your architectural prowess by creating stunning structures using only vanilla resources. Let your creativity shine!",
		System.currentTimeMillis() - 518400000, // 6 days ago
		2004,
		3007
	),
	NetworkPost(
		"Exploration Expedition",
		"Join us on an exciting exploration expedition in VanillaVerse! Together, we'll embark on a journey to uncover rare biomes and hidden treasures. Don't miss out on this epic adventure!",
		System.currentTimeMillis() - 777600000, // 9 days ago
		2004,
		3008
	),

	// Posts for "Los Santos City"
	NetworkPost(
		"New Heist Update",
		"Attention all criminals! Los Santos City is getting a new heist update. Prepare for high-stakes missions, daring escapes, and massive rewards. Gather your crew and get ready to pull off the ultimate heist!",
		System.currentTimeMillis() - 172800000, // 2 days ago
		2006,
		3009
	),

	// Posts for "Aeternum Realm"
	NetworkPost(
		"Server Maintenance",
		"Dear players, we will be conducting server maintenance in Aeternum Realm to improve performance and address minor issues. The maintenance is scheduled for tomorrow, so please plan your gaming sessions accordingly. Thank you for your understanding!",
		System.currentTimeMillis() - 86400000, // 1 day ago
		2008,
		3010
	),
	NetworkPost(
		"Community Event: Battle Royale",
		"Attention warriors! We are organizing a thrilling Battle Royale event in Aeternum Realm. Test your skills against other players in a fight for survival. Only the strongest will emerge victorious!",
		System.currentTimeMillis() - 345600000, // 4 days ago
		2008,
		3011
	),
	NetworkPost(
		"Trading Post Expansion",
		"Exciting news! The Trading Post in Aeternum Realm is expanding its inventory. Find new and rare items, engage in prosperous trading, and build your wealth. Visit the Trading Post today!",
		System.currentTimeMillis() - 604800000, // 7 days ago
		2008,
		3012
	)
)

private val COMMENTS = mutableListOf(
	// Comments for "New Raid Announcement"
	NetworkComment(
		"Adventurer123",
		"This raid sounds epic! Can't wait to join the battle and claim those rewards.",
		System.currentTimeMillis() - 3600000, // 1 hour ago
		3001,
		4001
	),
	NetworkComment(
		"RaidMaster99",
		"I've been preparing my guild for this raid for weeks. We're ready to conquer!",
		System.currentTimeMillis() - 7200000, // 2 hours ago
		3001,
		4002
	),
	NetworkComment(
		"CasualPlayer",
		"Sounds exciting, but I hope there are lower difficulty options too. I'm still gearing up.",
		System.currentTimeMillis() - 10800000, // 3 hours ago
		3001,
		4003
	),

	// Comments for "Recruiting for PvP Team"
	NetworkComment(
		"PvPJunkie",
		"I'm an experienced PvP player looking for a team. Can you provide more details on the requirements?",
		System.currentTimeMillis() - 21600000, // 6 hours ago
		3002,
		4004
	),

	// Comments for "Guild Recruitment"
	NetworkComment(
		"NewbiePlayer",
		"I'm new to the game but eager to learn. Can I join the guild as a beginner?",
		System.currentTimeMillis() - 32400000, // 9 hours ago
		3003,
		4005
	),
	NetworkComment(
		"GuildMember2021",
		"Our guild has a friendly and supportive environment. Don't hesitate to join, regardless of your level!",
		System.currentTimeMillis() - 43200000, // 12 hours ago
		3003,
		4006
	),

	// Comments for "Mod Showcase: Magic Expansion"
	NetworkComment(
		"MagicEnthusiast",
		"This mod looks incredible! Can't wait to try out the new spells and encounter magical creatures.",
		System.currentTimeMillis() - 54000000, // 15 hours ago
		3004,
		4007
	),
	NetworkComment(
		"VanillaPlayer",
		"I prefer playing without mods, but I'm glad you all are enjoying the magic expansion!",
		System.currentTimeMillis() - 64800000, // 18 hours ago
		3004,
		4008
	),

	// Comments for "Community Build Contest"
	NetworkComment(
		"MasterBuilder",
		"I'm already brainstorming ideas for the build contest. Can't wait to see everyone's creations!",
		System.currentTimeMillis() - 75600000, // 21 hours ago
		3005,
		4009
	),

	// Comments for "Minecraft Survival Tips"
	NetworkComment(
		"SurvivalGuru",
		"Great tips! I especially agree with always carrying a bed. It's a real lifesaver.",
		System.currentTimeMillis() - 86400000, // 1 day ago
		3006,
		4010
	),

	// Comments for "Building Competition"
	NetworkComment(
		"CreativeMind",
		"I'm excited to participate in the building competition. It's a great opportunity to showcase my building skills!",
		System.currentTimeMillis() - 97200000, // 1 day ago
		3007,
		4011
	),
	NetworkComment(
		"Observer",
		"I'll make sure to visit the competition area and appreciate the hard work put into the builds.",
		System.currentTimeMillis() - 108000000, // 1 day ago
		3007,
		4012
	),

	// Comments for "Exploration Expedition"
	NetworkComment(
		"AdventureSeeker",
		"Count me in for the exploration expedition! I'm always up for discovering new places.",
		System.currentTimeMillis() - 118800000, // 1 day ago
		3008,
		4013
	),

	// Comments for "New Heist Update"
	NetworkComment(
		"CriminalMastermind",
		"I've been waiting for new heists! Time to assemble my crew and plan the ultimate score.",
		System.currentTimeMillis() - 129600000, // 1 day ago
		3009,
		4014
	),

	// Comments for "Server Maintenance"
	NetworkComment(
		"Player123",
		"I hope the maintenance goes smoothly. Looking forward to improved performance!",
		System.currentTimeMillis() - 140400000, // 1 day ago
		3010,
		4015
	),

	// Comments for "Community Event: Battle Royale"
	NetworkComment(
		"BrawlerX",
		"I'm sharpening my combat skills for the Battle Royale event. It's going to be intense!",
		System.currentTimeMillis() - 151200000, // 1 day ago
		3011,
		4016
	),

	// Comments for "Trading Post Expansion"
	NetworkComment(
		"MerchantGuild",
		"We're excited for the Trading Post expansion. It means more business opportunities for our guild!",
		System.currentTimeMillis() - 162000000, // 1 day ago
		3012,
		4017
	)
)

private val USERS = listOf(
	NetworkUser(
		"Adventurer123",
		"adventurer123@example.com",
		"password123",
		false,
		5001
	),
	NetworkUser(
		"RaidMaster99",
		"raidmaster99@example.com",
		"password456",
		false,
		5002
	),
	NetworkUser(
		"CasualPlayer",
		"casualplayer@example.com",
		"password789",
		false,
		5003
	),
	NetworkUser(
		"PvPJunkie",
		"pvpjunkie@example.com",
		"password987",
		false,
		5004
	),
	NetworkUser(
		"NewbiePlayer",
		"newbieplayer@example.com",
		"password654",
		false,
		5005
	),
	NetworkUser(
		"GuildMember2021",
		"guildmember2021@example.com",
		"password321",
		false,
		5006
	),
	NetworkUser(
		"MagicEnthusiast",
		"magicenthusiast@example.com",
		"password012",
		false,
		5007
	),
	NetworkUser(
		"VanillaPlayer",
		"vanillaplayer@example.com",
		"password345",
		false,
		5008
	),
	NetworkUser(
		"MasterBuilder",
		"masterbuilder@example.com",
		"password678",
		false,
		5009
	),
	NetworkUser(
		"SurvivalGuru",
		"survivalguru@example.com",
		"password901",
		false,
		5010
	),
	NetworkUser(
		"CreativeMind",
		"creativemind@example.com",
		"password234",
		false,
		5011
	),
	NetworkUser(
		"Observer",
		"observer@example.com",
		"password567",
		false,
		5012
	),
	NetworkUser(
		"AdventureSeeker",
		"adventureseeker@example.com",
		"password890",
		false,
		5013
	),
	NetworkUser(
		"CriminalMastermind",
		"criminalmastermind@example.com",
		"password432",
		false,
		5014
	),
	NetworkUser(
		"Player123",
		"player123@example.com",
		"password765",
		false,
		5015
	),
	NetworkUser(
		"BrawlerX",
		"brawlerx@example.com",
		"password098",
		false,
		5016
	),
	NetworkUser(
		"MerchantGuild",
		"merchantguild@example.com",
		"password321",
		false,
		5017
	)
)