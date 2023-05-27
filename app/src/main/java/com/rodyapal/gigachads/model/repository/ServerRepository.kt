package com.rodyapal.gigachads.model.repository

import com.rodyapal.gigachads.model.entity.Server
import com.rodyapal.gigachads.model.entity.ServerWithGameName
import com.rodyapal.gigachads.model.local.dao.ServerDao
import com.rodyapal.gigachads.model.local.entity.SearchedServerEntity
import com.rodyapal.gigachads.model.local.entity.FavoriteServerEntity
import com.rodyapal.gigachads.model.network.api.ServerApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEmpty

class ServerRepository(
	private val dao: ServerDao,
	private val api: ServerApi
) {
	suspend fun getServer(id: Long): Flow<Server> = getServers(listOf(id)).map { it.first() }
	suspend fun getServers(ids: List<Long>): Flow<List<Server>> {
		return if (ids.isEmpty()) emptyFlow() else dao.get(ids).onEmpty {
			refreshServers(ids)
		}.map { servers ->
			servers.map { it.toDomainModel() }
		}
	}

	private suspend fun refreshServers(ids: List<Long>) = ids.isNotEmpty().let {
		api.getServers(ids).let { servers ->
			dao.save(servers.map { it.toDomainModel().toServerEntity() })
		}
	}

	suspend fun getById(id: Long): Flow<Server> {
		return dao.getById(id).onEmpty {
			refreshServer(id)
		}.map { serverWithPosts ->
			serverWithPosts.toDomainModel()
		}
	}

	private suspend fun refreshServer(serverId: Long) = refreshServers(listOf(serverId))

	suspend fun getServerSearchHistory(): List<Server> =
		dao.getSearchedServers().map { it.toDomainModel() }

	suspend fun getServerSearchSuggestions(query: String): List<ServerWithGameName> =
		dao.getSearchSuggestions("%$query%").map {
			it.toDomainModel()
		}

	suspend fun searchServer(query: String): List<Server> {
		return api.searchServer(query).map {
			it.toDomainModel()
		}.also { servers ->
			dao.save(servers.map { it.toServerEntity() })
		}
	}

	suspend fun isFavorite(serverId: Long, userId: Long): Boolean {
		return dao.isFavorite(serverId, userId)
	}

	suspend fun setFavorite(serverId: Long, userId: Long) {
		dao.setFavorite(
			FavoriteServerEntity(userId, serverId)
		)
		api.addToFavorite(serverId, userId)
	}

	suspend fun removeFavorite(serverId: Long, userId: Long) {
		dao.removeFavorite(
			FavoriteServerEntity(userId, serverId)
		)
		api.removeFromFavorite(serverId, userId)
	}

	suspend fun setWasSearched(serverId: Long) {
		val searched = getServerSearchHistory()
		if (searched.size == 10) {
			dao.removeFromSearchHistory(SearchedServerEntity(searched.last().serverId))
		}
		dao.setWasSearched(SearchedServerEntity(serverId))
	}
}