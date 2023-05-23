package com.rodyapal.gigachads.screens.post

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
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

@Composable
fun PostScreen(
	postId: Long,
	viewModel: PostViewModel = koinViewModel()
) {
	val viewState = viewModel.viewState.collectAsState()
	LaunchedEffect(key1 = Unit) {
		viewModel.reduce(PostScreenEvent.EnterScreen(postId))
	}
	when (val state = viewState.value) {
		is PostScreenState.Comments -> PostScreenComments(
			state = state,
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
			state = state,
			onViewCommentsClick = {
				viewModel.reduce(PostScreenEvent.OnViewCommentsClick)
			}
		)
		is PostScreenState.Loading -> {
			Box(modifier = Modifier.fillMaxSize()) {
				CircularProgressIndicator(
					modifier = Modifier.align(Alignment.Center)
				)
			}
		}
	}
}