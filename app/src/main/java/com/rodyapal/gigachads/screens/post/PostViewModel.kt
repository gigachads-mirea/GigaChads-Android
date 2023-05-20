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
			is PostScreenEvent.OnLikePost -> viewModelScope.launch {
				postRepository.setLike(state.post.id, !state.isLikedByUser)?.let {
					_viewState.update { current ->
						(current as PostScreenState.Display).copy(
							post = it,
							isLikedByUser = !current.isLikedByUser
						)
					}
				} ?: _viewState.update { current ->
					(current as PostScreenState.Display).copy(
						isError = true
					)
				}
			}
			is PostScreenEvent.OnViewCommentsClick -> viewModelScope.launch {
				_viewState.update {
					PostScreenState.Comments(
						comments = commentsRepository.getCommentsForPostWithLikeStatus((it as PostScreenState.Display).post.id),
						userComment = "",
						postTitle = it.post.title
					)
				}
			}
			else -> {throw Exception("Invalid state ($state) for event ($event)")}
		}
	}

	private fun reduce(state: PostScreenState.Comments, event: PostScreenEvent) {
		when (event) {
			is PostScreenEvent.OnCommentLike -> viewModelScope.launch {
				val status = commentsRepository.setLikeStatus(event.commentId)
				_viewState.update {
					state.copy(
						comments = state.comments.map { if (it.first.id == event.commentId) it.first to status else it}
					)
				}
			}
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
					postId = state.comments[0].first.parentPostId,
					author = userRepository.getCurrent()
				)
			}
			is PostScreenEvent.OnBackToPost -> viewModelScope.launch {
				val post = postRepository.getPostById(state.comments[0].first.parentPostId)
				_viewState.update {
					PostScreenState.Display(
						post = post,
						serverName = serverRepository.getParentServerForPost(post.id).name,
						isLikedByUser = postRepository.isLikedByCurrentUser(post.id)
					)
				}
			}
			else -> {throw Exception("Invalid state ($state) for event ($event)")}
		}
	}

	private fun reduce(event: PostScreenEvent.EnterScreen) = viewModelScope.launch {
		val post = postRepository.getPostById(event.postId)
		_viewState.update {
			PostScreenState.Display(
				post = post,
				serverName = serverRepository.getParentServerForPost(post.id).name,
				isLikedByUser = postRepository.isLikedByCurrentUser(post.id)
			)
		}
	}
}