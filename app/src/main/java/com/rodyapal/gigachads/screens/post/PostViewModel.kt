package com.rodyapal.gigachads.screens.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodyapal.gigachads.model.Reducer
import com.rodyapal.gigachads.model.repository.CommentRepository
import com.rodyapal.gigachads.model.repository.PostRepository
import com.rodyapal.gigachads.model.repository.ServerRepository
import com.rodyapal.gigachads.model.repository.UserRepository
import com.rodyapal.gigachads.screens.post.model.PostScreenState
import com.rodyapal.gigachads.screens.post.model.PostScreenEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostViewModel(
	private val postRepository: PostRepository,
	private val commentsRepository: CommentRepository,
	private val userRepository: UserRepository,
	private val serverRepository: ServerRepository
) : ViewModel(), Reducer<PostScreenEvent> {

	private val _viewState = MutableStateFlow<PostScreenState>(PostScreenState.Loading)
	val viewState get() = _viewState as StateFlow<PostScreenState>

	override fun reduce(event: PostScreenEvent) {
		when (val state = _viewState.value) {
			is PostScreenState.Display -> reduce(state, event)
			is PostScreenState.Comments -> reduce(state, event)
			is PostScreenState.Loading -> reduce(event as PostScreenEvent.EnterScreen)
		}
	}

	private fun reduce(state: PostScreenState.Display, event: PostScreenEvent) {
		when (event) {
			is PostScreenEvent.OnViewCommentsClick -> viewModelScope.launch {
				commentsRepository.getCommentsForPost(state.post.id).collect { comments ->
					_viewState.update {
						PostScreenState.Comments(
							comments = comments,
							userComment = "",
							postTitle = state.post.title
						)
					}
				}
			}
			else -> {throw Exception("Invalid state ($state) for event ($event)")}
		}
	}

	private fun reduce(state: PostScreenState.Comments, event: PostScreenEvent) {
		when (event) {
			is PostScreenEvent.OnUserCommentInput -> viewModelScope.launch {
				_viewState.update {
					state.copy(
						userComment = event.input
					)
				}
			}
			is PostScreenEvent.OnUserCommentDone -> viewModelScope.launch {
				commentsRepository.addUserComment(
					text = state.userComment,
					postId = state.comments[0].parentPostId,
					author = (userRepository.authState.value as UserRepository.AuthState.Authenticated).user
				)
			}
			is PostScreenEvent.OnBackToPost -> viewModelScope.launch {
				postRepository.getPostById(state.comments[0].parentPostId).collect { post ->
					serverRepository.getServer(post.serverId).collect { server ->
						_viewState.update {
							PostScreenState.Display(
								post = post,
								serverName = server.name
							)
						}
					}
				}
			}
			else -> {throw Exception("Invalid state ($state) for event ($event)")}
		}
	}

	private fun reduce(event: PostScreenEvent.EnterScreen) = viewModelScope.launch {
		postRepository.getPostById(event.postId).collect { post ->
			serverRepository.getServer(post.serverId).collect { server ->
				_viewState.update {
					PostScreenState.Display(
						post = post,
						serverName = server.name
					)
				}
			}
		}
	}
}