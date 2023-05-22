package com.rodyapal.gigachads.model.repository

import com.rodyapal.gigachads.model.dao.ServerDao
import com.rodyapal.gigachads.model.entity.SearchedServer
import com.rodyapal.gigachads.model.entity.Server
import com.rodyapal.gigachads.model.entity.ServerUserCrossRef
import com.rodyapal.gigachads.model.entity.ServerWithPosts
import com.rodyapal.gigachads.model.network.api.ServerApi

class ServerRepository(
	private val dao: ServerDao,
	private val api: ServerApi
) {
	suspend fun getServers(ids: List<Long>): List<Server> {
		return dao.get(ids)
	}

	suspend fun getById(id: Long): ServerWithPosts {
		//TODO: implement
		return dao.getById(id)
	}

	suspend fun getParentServerForPost(postId: Long): Server {
		return dao.getParentForPost(postId)
	}

	suspend fun getServerSearchHistory(): List<Server> = dao.getSearchedServers()

	suspend fun getServerSearchSuggestions(query: String): List<Server> {
		//TODO: implement
		return dao.getSearchSuggestions("%$query%")
	}

	suspend fun isFavorite(serverId: Long, userId: Long): Boolean {
		//TODO: implement
		return dao.isFavorite(serverId, userId)
	}

	suspend fun setFavorite(serverId: Long, userId: Long) {
		//TODO: implement
		dao.setFavorite(
			ServerUserCrossRef(
				userId, serverId
			)
		)
	}

	suspend fun removeFavorite(serverId: Long, userId: Long) {
		//TODO: implement
		dao.removeFavorite(
			ServerUserCrossRef(
				userId, serverId
			)
		)
	}

	suspend fun setWasSearched(serverId: Long) {
		val searched = getServerSearchHistory()
		if (searched.size == 10) {
			dao.removeFromSearchHistory(SearchedServer(searched.last().serverId))
		}
		dao.setWasSearched(SearchedServer(serverId))
	}
}