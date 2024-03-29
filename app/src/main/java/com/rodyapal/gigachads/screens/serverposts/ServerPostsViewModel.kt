package com.rodyapal.gigachads.screens.serverposts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodyapal.gigachads.model.Reducer
import com.rodyapal.gigachads.model.repository.PostRepository
import com.rodyapal.gigachads.model.repository.ServerRepository
import com.rodyapal.gigachads.model.repository.UserRepository
import com.rodyapal.gigachads.screens.serverposts.model.PostsForServer
import com.rodyapal.gigachads.screens.serverposts.model.ServerPostsScreenEvent
import com.rodyapal.gigachads.screens.serverposts.model.ServerPostsScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ServerPostsViewModel(
	private val serverRepository: ServerRepository,
	private val postRepository: PostRepository,
	private val userRepository: UserRepository
) : ViewModel(), Reducer<ServerPostsScreenEvent> {

	private val _viewState =
		MutableStateFlow<ServerPostsScreenState>(ServerPostsScreenState.Loading)
	val viewState get() = _viewState as StateFlow<ServerPostsScreenState>

	override fun reduce(event: ServerPostsScreenEvent) {
		when (val state = viewState.value) {
			is ServerPostsScreenState.Display -> {}
			is ServerPostsScreenState.Loading -> viewModelScope.launch {
				userRepository.getFavoriteServerIds().collect { ids ->
					if (ids.isEmpty()) {
						_viewState.update {
							ServerPostsScreenState.Display(
								serversWithPosts = emptyList()
							)
						}
					} else {
						val servers = serverRepository.getServers(ids).first { true }
						val posts = postRepository.getPostsForServers(ids).first { true }
						_viewState.update {
							ServerPostsScreenState.Display(
								serversWithPosts = servers.map { server ->
									PostsForServer(
										serverName = server.name,
										posts = posts.filter { it.serverId == server.serverId }
									)
								}
							)
						}
					}
				}
			}
		}
	}
}