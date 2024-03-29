package com.rodyapal.gigachads.screens.server

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodyapal.gigachads.model.Reducer
import com.rodyapal.gigachads.model.repository.GameRepository
import com.rodyapal.gigachads.model.repository.PostRepository
import com.rodyapal.gigachads.model.repository.ServerRepository
import com.rodyapal.gigachads.model.repository.UserRepository
import com.rodyapal.gigachads.screens.server.model.ServerScreenEvent
import com.rodyapal.gigachads.screens.server.model.ServerScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ServerViewModel(
	private val serverRepository: ServerRepository,
	private val postRepository: PostRepository,
	private val userRepository: UserRepository,
	private val gameRepository: GameRepository
) : ViewModel(), Reducer<ServerScreenEvent> {

	private val _viewState = MutableStateFlow<ServerScreenState>(ServerScreenState.Loading)
	val viewState get() = _viewState as StateFlow<ServerScreenState>

	override fun reduce(event: ServerScreenEvent) {
		when (val state = viewState.value) {
			is ServerScreenState.Info -> reduce(state, event)
			is ServerScreenState.Posts -> reduce(state, event)
			is ServerScreenState.Loading -> {
				fetchData((event as ServerScreenEvent.EnterScreen).serverId)
			}
		}
	}

	private fun reduce(state: ServerScreenState.Info, event: ServerScreenEvent) {
		when (event) {
			is ServerScreenEvent.OnAddToFavorite -> viewModelScope.launch {
				val userId = (userRepository.authState.value as UserRepository.AuthState.Authenticated).user.userId
				if (!serverRepository.isFavorite(state.server.serverId, userId)) {
					serverRepository.setFavorite(state.server.serverId, userId)
				} else {
					serverRepository.removeFavorite(state.server.serverId, userId)
				}
				_viewState.update {
					state.copy(
						isFavorite = !state.isFavorite
					)
				}
			}
			is ServerScreenEvent.OnSeeOtherPosts -> viewModelScope.launch {
				postRepository.getPostsForServer(state.server.serverId).collect { posts ->
					_viewState.update {
						ServerScreenState.Posts(
							serverName = state.server.name,
							posts = posts
						)
					}
				}
			}
			is ServerScreenEvent.EnterScreen -> {}
			else -> throw Exception("Invalid state ($state) for event ($event)")
		}
	}

	private fun reduce(state: ServerScreenState.Posts, event: ServerScreenEvent) {
		when (event) {
			is ServerScreenEvent.OnBackToInfo -> fetchData(state.posts.first().serverId)
			is ServerScreenEvent.EnterScreen -> {}
			else -> throw Exception("Invalid state ($state) for event ($event)")
		}
	}

	private fun fetchData(serverId: Long) = viewModelScope.launch {
		serverRepository.getById(serverId).collect { server ->
			postRepository.getPostsForServer(server.serverId).collect { posts ->
				_viewState.update {
					ServerScreenState.Info(
						server = server,
						latestPost = posts.maxByOrNull { it.writtenAt },
						gameName = gameRepository.getById(server.gameId).name,
						isFavorite = serverRepository.isFavorite(serverId, (userRepository.authState.value as UserRepository.AuthState.Authenticated).user.userId)
					)
				}
			}
		}
	}
}