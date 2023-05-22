package com.rodyapal.gigachads.screens.server

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.rodyapal.gigachads.screens.server.model.ServerScreenEvent
import com.rodyapal.gigachads.screens.server.model.ServerScreenState
import com.rodyapal.gigachads.screens.server.subscreens.ServerScreenInfo
import com.rodyapal.gigachads.screens.server.subscreens.ServerScreenPosts
import org.koin.androidx.compose.koinViewModel

@Composable
fun ServerScreen(
	serverId: Long,
	onViewPost: (Long) -> Unit,
	viewModel: ServerViewModel = koinViewModel()
) {
	val viewState = viewModel.viewState.collectAsState()
	LaunchedEffect(key1 = Unit) {
		viewModel.reduce(
			ServerScreenEvent.EnterScreen(serverId)
		)
	}
	when (val state = viewState.value) {
		is ServerScreenState.Info -> ServerScreenInfo(
			state = state,
			onAddToFavoriteClick = {
				viewModel.reduce(
					ServerScreenEvent.OnAddToFavorite
				)
			},
			onReadPostClick = {
				onViewPost(it)
			},
			onSeeOtherPostsClick = {
				viewModel.reduce(
					ServerScreenEvent.OnSeeOtherPosts
				)
			}
		)
		is ServerScreenState.Posts -> ServerScreenPosts(
			state = state,
			onReadPostClick = {
				onViewPost(it)
			},
			onBackToInfoClick = {
				viewModel.reduce(
					ServerScreenEvent.OnBackToInfo
				)
			}
		)
		is ServerScreenState.Loading -> Box(
			modifier = Modifier.fillMaxSize()
		) {
			CircularProgressIndicator(
				modifier = Modifier.align(Alignment.Center)
			)
		}
	}
}