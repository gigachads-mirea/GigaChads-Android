package com.rodyapal.gigachads.screens.server.subscreens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rodyapal.gigachads.R
import com.rodyapal.gigachads.screens.server.model.ServerScreenState
import com.rodyapal.gigachads.ui.composable.PostCard
import com.rodyapal.gigachads.utils.MOCK_SERVER_SCREEN_STATE_POSTS

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ServerScreenPosts(
	modifier: Modifier = Modifier,
	state: ServerScreenState.Posts,
	onReadPostClick: (Long) -> Unit,
	onBackToInfoClick: () -> Unit
) {
	Column(
		modifier = modifier.fillMaxSize()
	) {
		Row(
			modifier = Modifier.fillMaxWidth(),
			verticalAlignment = Alignment.CenterVertically
		) {
			IconButton(
				onClick = onBackToInfoClick
			) {
				Icon(
					imageVector = Icons.Default.ArrowBack,
					contentDescription = stringResource(R.string.description_back_to_server_info)
				)
			}

			Spacer(modifier = Modifier.width(16.dp))

			Text(
				text = state.serverName,
				style = MaterialTheme.typography.titleLarge
			)
		}

		Divider()

		LazyVerticalStaggeredGrid(
			columns = StaggeredGridCells.Fixed(2)
		) {
			items(state.posts) { post ->
				PostCard(
					modifier = Modifier.padding(6.dp),
					post = post,
					serverName = "",
					onReadClick = {
						onReadPostClick(post.id)
					}
				)
			}
		}
	}
}

@Preview
@Composable
fun ServerScreenPostsPreview() {
	ServerScreenPosts(
		state = MOCK_SERVER_SCREEN_STATE_POSTS,
		onReadPostClick = {},
		onBackToInfoClick = {}
	)
}