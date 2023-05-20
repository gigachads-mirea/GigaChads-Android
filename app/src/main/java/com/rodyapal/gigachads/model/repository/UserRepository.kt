package com.rodyapal.gigachads.model.repository

import com.rodyapal.gigachads.model.dao.UserDao
import com.rodyapal.gigachads.model.entity.User
import kotlinx.coroutines.delay

class UserRepository(
	private val dao: UserDao,
//	private val api: UserApi
) {
	suspend fun getCurrent(): User = dao.getLoggedUser()
	suspend fun auth(login: String, password: String): Boolean {
//		return api.auth(login, password).also { userForServer ->
//			userForServer?.let {
//				with(it) {
//					dao.save(User(username, email, password, isClient, userId))
//				}
//			}
//		} != null
		delay(3000)
		return true
	}
	suspend fun register(login: String, password: String, username: String): Boolean {
//		return api.register(login, password, username).also { userForServer ->
//			userForServer?.let {
//				with(it) {
//					dao.save(User(username, email, password, isClient, userId))
//				}
//			}
//		} != null
		delay(3000)
		return true
	}

	suspend fun getFavoriteServers() = getCurrent().let { dao.getFavoriteServerIds(it.userId) }
}