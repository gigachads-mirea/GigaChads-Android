package com.rodyapal.gigachads.screens.post

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.rodyapal.gigachads.screens.post.model.PostScreenState
import com.rodyapal.gigachads.screens.post.model.PostScreenEvent
import com.rodyapal.gigachads.screens.post.subscreens.PostScreenComments
import com.rodyapal.gigachads.screens.post.subscreens.PostScreenDisplay
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostScreen(
	postId: Long,
	viewModel: PostViewModel = koinViewModel()
) {
	val viewState = viewModel.viewState.collectAsState()
	LaunchedEffect(key1 = Unit) {
		viewModel.reduce(PostScreenEvent.EnterScreen(postId))
	}
	Scaffold { paddingValues ->
		when (val state = viewState.value) {
			is PostScreenState.Comments -> PostScreenComments(
				modifier = Modifier.padding(paddingValues),
				state = state,
				onCommentLike = {
					viewModel.reduce(PostScreenEvent.OnCommentLike(it))
				},
				onUserCommentInput = {
					viewModel.reduce(PostScreenEvent.OnUserCommentInput(it))
				},
				onUserCommentDone = {
					viewModel.reduce(PostScreenEvent.OnUserCommentDone)
				},
				onBackPressed = {
					viewModel.reduce(PostScreenEvent.OnBackToPost)
				}
			)
			is PostScreenState.Display -> PostScreenDisplay(
				modifier = Modifier.padding(paddingValues),
				state = state,
				onLikeClick = {
					viewModel.reduce(PostScreenEvent.OnLikePost)
				},
				onViewCommentsClick = {
					viewModel.reduce(PostScreenEvent.OnViewCommentsClick)
				}
			)
			is PostScreenState.Loading -> {
				Box(modifier = Modifier.padding(paddingValues)) {
					CircularProgressIndicator(
						modifier = Modifier.align(Alignment.Center)
					)
				}
			}
		}
	}
}