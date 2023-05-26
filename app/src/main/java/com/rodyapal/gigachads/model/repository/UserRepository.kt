package com.rodyapal.gigachads.model.repository

import com.rodyapal.gigachads.model.entity.User
import com.rodyapal.gigachads.model.local.dao.UserDao
import com.rodyapal.gigachads.model.local.entity.FavoriteServerEntity
import com.rodyapal.gigachads.model.network.api.UserApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserRepository(
	private val dao: UserDao,
	private val api: UserApi,
) {
	private val _authState = MutableStateFlow<AuthState>(AuthState.NotAuthenticated)
	val authState get() = _authState as StateFlow<AuthState>

	init {
		MainScope().launch {
			withContext(Dispatchers.Default) {
				if (dao.isAuthenticatedLocally()) {
					_authState.update {
						AuthState.Authenticated(
							user = dao.getLoggedUser().toDomainModel()
						)
					}
				}
			}
		}
	}
	suspend fun auth(
		email: String, password: String
	): Boolean = api.auth(email, password)?.also { (username, id) ->
		onAuth(User(
			username, email, password, false, id
		))
	} != null

	suspend fun register(
		email: String, password: String, username: String
	): Boolean = api.register(email, password, username)?.also { id ->
		onAuth(User(
			username, email, password, false, id
		))
	} != null

	suspend fun getFavoriteServerIds(): Flow<List<Long>> {
		val userId = (authState.value as AuthState.Authenticated).user.userId
		return dao.getFavoriteServerIds(userId).onEmpty {
			api.getFavoriteServerIds(userId).let { ids ->
				dao.setFavorite(
					ids.map {
						FavoriteServerEntity(userId, it)
					}
				)
			}
		}
	}

	private suspend fun onAuth(user: User) {
		dao.save(
			user.toUserEntity()
		)
		_authState.update {
			AuthState.Authenticated(
				user
			)
		}
	}

	suspend fun logout() {
		dao.logout()
		_authState.update {
			AuthState.NotAuthenticated
		}
	}

	sealed class AuthState {
		data class Authenticated(
			val user: User
		) : AuthState()

		object NotAuthenticated : AuthState()
	}
}