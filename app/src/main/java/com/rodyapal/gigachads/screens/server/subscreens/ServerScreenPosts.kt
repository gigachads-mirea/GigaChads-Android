package com.rodyapal.gigachads.screens.server.subscreens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rodyapal.gigachads.screens.server.model.ServerScreenState
import com.rodyapal.gigachads.screens.server.view.PostCard
import com.rodyapal.gigachads.utils.MOCK_SERVER_SCREEN_STATE_POSTS

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ServerScreenPosts(
	state: ServerScreenState.Posts,
	onReadPostClick: (Int) -> Unit
) {
	LazyVerticalStaggeredGrid(
		columns = StaggeredGridCells.Fixed(2)
	) {
		itemsIndexed(state.posts) { index, post ->
			PostCard(
				modifier = Modifier.padding(6.dp),
				post = post,
				serverName = state.serverName,
				onReadClick = {
					onReadPostClick(index)
				}
			)
		}
	}
}

@Preview
@Composable
fun ServerScreenPostsPreview() {
	ServerScreenPosts(
		state = MOCK_SERVER_SCREEN_STATE_POSTS,
		onReadPostClick = {}
	)
}