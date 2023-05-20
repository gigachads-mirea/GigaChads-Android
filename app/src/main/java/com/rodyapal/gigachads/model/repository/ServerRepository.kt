package com.rodyapal.gigachads.model.repository

import com.rodyapal.gigachads.model.dao.ServerDao
import com.rodyapal.gigachads.model.entity.Server
import com.rodyapal.gigachads.model.network.api.ServerApi

class ServerRepository(
	private val dao: ServerDao,
	private val api: ServerApi
) {
	suspend fun getServers(ids: List<Long>): List<Server> {
		return dao.get(ids)
	}

	suspend fun getParentServerForPost(postId: Long): Server {
		return dao.getParentForPost(postId)
	}
}